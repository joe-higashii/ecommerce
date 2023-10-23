package br.com.serratec.ecommerce.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.produto.ProdutoRequestDTO;
import br.com.serratec.ecommerce.model.EmailHtmlConteudo;
import br.com.serratec.ecommerce.model.Log;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.model.Produto;
import br.com.serratec.ecommerce.model.Usuario;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.repository.PedidoRepository;
import br.com.serratec.ecommerce.repository.ProdutoRepository;
import br.com.serratec.ecommerce.repository.UsuarioRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LogService logService;

    @Autowired
    private ProdutoService produtoService;

    @Autowired
    private ModelMapper mapper;


    public List<PedidoResponseDTO> obterTodos() {

        List<Pedido> pedidos = pedidoRepository.findAll();

        return pedidos
                .stream()
                .map(pedido -> mapper.map(pedido, PedidoResponseDTO.class))
                .collect(Collectors.toList());
    }

    public PedidoResponseDTO obterPorId(long id) {

        Optional<Pedido> optPedido = pedidoRepository.findById(id);

        if (optPedido.isEmpty()) {
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optPedido.get(), PedidoResponseDTO.class);
    }

    public PedidoResponseDTO adicionar(PedidoRequestDTO pedidoRequest) {

        // pega os itens
        List<PedidoItem> listaSalvaProdutos = pedidoRequest
                .getItens()
                .stream()
                .map(item -> mapper
                        .map(item, PedidoItem.class))
                .collect(Collectors.toList());

        Pedido pedido = adicionarPedido(pedidoRequest);

        pedido.setItens(listaSalvaProdutos);

        List<PedidoItem> itensCadastrados = itemsPedido(pedido);

        pedido.setItens(itensCadastrados);

        abaterEstoque(pedido);

        PedidoResponseDTO pedidoResponse = mapper.map(pedido, PedidoResponseDTO.class);

        pedidoRequest = mapper.map(pedido, PedidoRequestDTO.class);

        enviarEmailPedido(pedidoRequest);

        try {

            Log log = new Log(
                    "Pedido",
                    "CADASTRO",
                    "",
                    new ObjectMapper().writeValueAsString(pedido), pedido.getUsuario(),
                    new Date());

            logService.registrarLog(log);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }

        return pedidoResponse;
    }

    public Pedido adicionarPedido(PedidoRequestDTO pedidoRequest) {

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setDataPedido(new Date());
        pedido.setPedidoId(0l);
        pedido.setCancelado(false);

        pedido = retornaPedidoValores(pedido);

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public List<PedidoItem> itemsPedido(Pedido pedido) {

        List<PedidoItem> adicionadas = new ArrayList<>();

        for (PedidoItem pedidoItem : pedido.getItens()) {

            pedidoItem.setPedido(pedido);

            calculaValoresItem(pedidoItem);

            pedidoItemService.adicionar(pedidoItem);

            adicionadas.add(pedidoItem);
        }

        return adicionadas;
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setPedidoId(id);

        pedidoRepository.save(pedido);

        return mapper.map(pedido, PedidoResponseDTO.class);
    }

    public PedidoResponseDTO cancelarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow();

        pedido.setCancelado(false);

        pedido = pedidoRepository.save(pedido);

        return mapper.map(pedido, PedidoResponseDTO.class);
    }

    public Pedido ativarPedido(Long id) {

        Pedido pedido = pedidoRepository.findById(id).orElseThrow();

        pedido.setCancelado(true);

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public void deletar(Long id) {

        obterPorId(id);

        pedidoRepository.deleteById(id);
    }

    public void abaterEstoque(Pedido pedido) {

        for (PedidoItem pedidoItem : pedido.getItens()) {

            Long id = pedidoItem.getProduto().getProdutoId();
            Optional<Produto> opProduto = produtoRepository.findById(id);
            int quantidadeItem = pedidoItem.getQuantidade();
            int quantidadeEstoque = opProduto.get().getQuantidadeEstoque();

            if (quantidadeItem < quantidadeEstoque) {

                quantidadeEstoque -= quantidadeItem;

                ProdutoRequestDTO produtoRequest = mapper.map(opProduto.get(), ProdutoRequestDTO.class);

                produtoRequest.setQuantidadeEstoque(quantidadeEstoque);

                produtoService.atualizar(id, produtoRequest);
            } else {
                throw new RuntimeException("quantidade inválida");
            }
        }
    }

    public Pedido retornaPedidoValores(Pedido pedido) {

        Long idFormaPagamento = pedido.getFormaPagamento().getPagamentoId();

        List<PedidoItem> adicionadas = new ArrayList<>();

        for (PedidoItem pedidoItem : pedido.getItens()) {

            calculaValoresItem(pedidoItem);

            adicionadas.add(pedidoItem);
        } 

        pedido.setItens(adicionadas);

        if (idFormaPagamento == 1) {
            
            calcularDesconto(pedido);
        } else if (idFormaPagamento == 2) {

            calcularAcrescimo(pedido);
        } else if (idFormaPagamento == 4 || idFormaPagamento == 5) {

            pedido.setDesconto(0);
            pedido.setAcrescimo(0);
        } else {
            
            throw new RuntimeException("Nenhum registro encontrado para o ID: ");
        }

        return pedido;
    }

    public Pedido calcularDesconto(Pedido pedido) {

        double valorInicial = 0;
        double valorDesconto = 10;

        for (PedidoItem pedidoItem : pedido.getItens()) {

            valorInicial += pedidoItem.getValorTotalItem();
        }

        double desconto = (valorInicial / 100 * valorDesconto);
        double valorFinal = valorInicial - desconto;

        pedido.setAcrescimo(0);
        pedido.setDesconto(desconto);
        pedido.setValorTotal(valorFinal);

        return pedido;
    }

    public Pedido calcularAcrescimo(Pedido pedido) {

        double valorInicial = 0;
        double valorAcrescimo = 10;

        for (PedidoItem pedidoItem : pedido.getItens()) {

            valorInicial += pedidoItem.getValorTotalItem();
        }

        double acrescimo = (valorInicial / 100 * valorAcrescimo);
        double valorFinal = valorInicial + acrescimo;

        pedido.setAcrescimo(acrescimo);
        pedido.setDesconto(0);
        pedido.setValorTotal(valorFinal);

        return pedido;
    }

    public PedidoItem calculaValoresItem(PedidoItem item) {

        Optional<Produto> opProduto = produtoRepository.findById(item.getProduto().getProdutoId());

        item.setProduto(opProduto.get());

        double desconto = 10;
        double valorDesconto = 0;
        double quantidadeParaDesconto = 10;
        double valorFinal = 0;

        if (item.getQuantidade() > quantidadeParaDesconto) {
            valorDesconto =  item.getValorUnitario() / 100 * desconto;
        } else {
            valorDesconto = 0;
        }

        valorFinal = item.getValorUnitario() - valorDesconto;

        item.setValorTotalItem(valorFinal);
        item.setDesconto(valorDesconto);

        return item;
    }

    public String enviarEmailPedido(PedidoRequestDTO pedidoRequest) {
        
        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        Long idUsuario = pedido.getUsuario().getUsuarioId();

        Optional <Usuario> opUsuario = usuarioRepository.findById(idUsuario);

        pedido.setUsuario(opUsuario.get());
        
        String destinatario = "nathanzero14@gmail.com";
        String assunto = "teste as 3 da manha estou ficando louco e minha sanidade ja se foi a tempo " + pedido.getPedidoId();
        String mensagem = construirOConteudoDoEmail(pedido);

        Email email = new Email(assunto, mensagem, "d.conti133@gmail.com", Collections.singletonList(destinatario));

        EmailHtmlConteudo htmlConteudo = new EmailHtmlConteudo();
        htmlConteudo.salvarConteudoHtml(mensagem);

        try {
            emailService.enviar(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return mensagem;
    }

    private String construirOConteudoDoEmail(Pedido pedido) {
        
        StringBuilder htmlConteudo = new StringBuilder();
        
        htmlConteudo.append("<!DOCTYPE html>\r\n" + //);
            "<html lang=\"pt-br\">\r\n" + //
            "<head>\r\n" + //
            "    <meta charset=\"UTF-8\">\r\n" + //
            "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\r\n" + //
            "    <title>G5 ecommerce</title>\r\n" + //
            "</head>\r\n" + //
            "<body style=\"width: 100%; height: 100%; font-family: Verdana,sans-serif;\">\r\n" + //
            "    <div id=\"emailBody\" style=\"background: #f2f2f2; color: #2f2f2f; width: 90%;  border-radius: 20px; box-shadow: 5px 5px 10px #444;\">\r\n"
            + //
            "        <h1 style=\"text-align: center; padding-top: 20px;\">Obrigado.</h1>\r\n" + //
            "        <div id=\"container\" style=\"background-color: white; text-align: center; padding: 10px; border-radius: 10px; margin: 2%;\">\r\n"
            + //
            "            <h2 style=\"text-align: center;\">Olá " + pedido.getUsuario().getNome() + ".</h2>\r\n" + //
            "            <h3>Pedido Efetuado!</h3>\r\n" + //
            "            <p>\r\n" + //
            "                Obrigado por comprar na Zé's Little Shop.\r\n" + //
            "            </p>\r\n" + //
            "            <div box-shadow: 5px 5px 10px #444;>\r\n" + //
            "                <h3 style=\"color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;\">\r\n"
            + //
            "                    Informações do seu pedido: \r\n" + //
            "                </h3>\r\n" + //
            "                <p>\r\n" + //
            "                    <div style=\"text-align: left; margin: 20px; \">\r\n" + //
            "\r\n" + //
            "                        <div style=\"font-weight: bold; background-color: #444; color: white; padding: 10px;\">\r\n"
            + //
            "                            COD do pedido: \r\n" + //
            "                            <span style=\"color: white; font-weight: normal;\">" + pedido.getCodPedido() + "</span>\r\n"
            + //
            "                        </div>\r\n" + //
            "\r\n" + //
            "                        <div style=\"font-weight: bold; background-color: white; color: #444; padding: 10px;\">\r\n"
            + //
            "                            Enviar cobrança para: \r\n" + //
            "                            <span style=\"color: #2f2f2f; font-weight: normal;\\>" + pedido.getUsuario().getEmail() + "</span>\r\n"
            + //
            "                        </div>\r\n" + //
            "                        <div style=\"font-weight: bold; background-color: #444; color: white; padding: 10px;\">\r\n"
            + //
            "                            Data do pedido: \r\n" + //
            "                            <span style=\"color: white; font-weight: normal;\">" + pedido.getDataPedido() + "</span>\r\n"
            + //
            "                        </div>\r\n" + //
            "                    </div>\r\n" + //
            "                    \r\n" + //
            "                    <h3 style=\"color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;\">\r\n"
            + //
            "                        Aqui está o seu pedido: </h3>\r\n" + //
            "                    <div style=\"width: 100%; display: flex; flex-direction: column; align-items: center;\">\r\n"
            + //
            "                        <table style=\"width: 100%; padding: 10px; border-collapse: collapse;\">\r\n" + //
            "                            <thead style=\"background-color: #444; color: white; border: 1px solid #f2f2f2; width: 100%; height: 100%;\">\r\n"
            + //
            "                                <tr >\r\n" + //
            "                                    <th>Descrição</th>\r\n" + //
            "                                    <th>Quantidade</th>\r\n" + //
            "                                    <th>Preço</th>\r\n" + //
            "                                </tr>\r\n" + //
            "                            </thead>\r\n" + //
            "                            <tbody>\r\n");
            for (PedidoItem item : pedido.getItens()) {
            htmlConteudo.append("                <tr style=\"border: 1px solid #f2f2f2; height: 70px;\">\r\n" + //
            "                                    <td >" + item.getProduto().getNomeProduto() + "</td>\r\n" + //
            "                                    <td >" + item.getQuantidade() + "</td>\r\n" + //
            "                                    <td>R$" + item.getValorUnitario() + "</td>\r\n" + // //
            "                                </tr>\r\n");
            }
            htmlConteudo.append("    </tbody>\r\n" + //
            "                        </table><br>\r\n" + //
            "                        <br><div style=\"width: 30%;\"><h4 style=\"border-top: 1px solid #2f2f2f; border-bottom: 1px solid #2f2f2f; padding: 20px; text-align: center;\">\r\n"
            + //
            "                            Total: R$" + pedido.getValorTotal() + "</h4></div>\r\n" + //
            "                    </div>\r\n" + //
            "                </p>\r\n" + //
            "            </div>\r\n" + //
            "        </div>\r\n" + //
            "    </div>\r\n" + //
            "</body>\r\n" + //
            "</html>");

        return htmlConteudo.toString();
    }
}
