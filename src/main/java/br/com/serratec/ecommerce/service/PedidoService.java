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
    private ProdutoService produtoService;

    @Autowired
    private EmailService emailService;

    @Autowired
    private LogService logService;

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

        enviarEmailPedido(pedido);

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

            if (pedidoItem.getQuantidade() <= 0) {
                throw new RuntimeException("Quantidade de itens invalida.");
            } 

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

        if (!pedido.isCancelado()) {

            for (PedidoItem pedidoItem : pedido.getItens()) {

                long produtoId = pedidoItem.getProduto().getProdutoId();
                Produto produto = produtoRepository.findById(produtoId).orElseThrow();
                int quantidadeEstoque = produto.getQuantidadeEstoque();
                int quantidadeItem = pedidoItem.getQuantidade();

                int novoEstoque = quantidadeEstoque + quantidadeItem;

                produto.setQuantidadeEstoque(novoEstoque);
                
                enviarEmailCancelamento(pedido);

                produto = produtoRepository.save(produto);

                pedido.setCancelado(true);

            }

            pedido = pedidoRepository.save(pedido);

        } else {
            throw new RuntimeException("O pedido " + id + " já foi cancelado.");
        }

        return mapper.map(pedido, PedidoResponseDTO.class);
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

        Long id = item.getProduto().getProdutoId();

        Produto produto = produtoRepository.findById(id).orElseThrow();
    
        item.setValorUnitario(produto.getPrecoVenda());

        if (item.getQuantidade() >= quantidadeParaDesconto) {
            valorDesconto = item.getValorUnitario() / 100 * desconto;
        } else {
            valorDesconto = 0;
        }

        valorFinal = item.getValorUnitario() - valorDesconto;

        item.setValorTotalItem(valorFinal);
        item.setDesconto(valorDesconto);

        return item;
    }

    public String enviarEmailPedido(Pedido pedido) {

        Long idUsuario = pedido.getUsuario().getUsuarioId();

        Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);

        pedido.setUsuario(opUsuario.get());


        // String destinatario = pedido.getUsuario().getEmail();
        String destinatario = "nathanzero14@gmail.com";
        String assunto = "Finalização do pedido " + pedido.getPedidoId();

        String mensagem = construirOConteudoDoEmail(pedido);

        Email email = new Email(assunto, mensagem, " grupo5.e.commerceapi@gmail.com",
                Collections.singletonList(destinatario));

        EmailHtmlConteudo htmlConteudo = new EmailHtmlConteudo();
        htmlConteudo.salvarConteudoHtml(mensagem);

        try {
            emailService.enviar(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensagem;
    }

    public String enviarEmailCancelamento(Pedido pedido) {

        Long idUsuario = pedido.getUsuario().getUsuarioId();

        Optional<Usuario> opUsuario = usuarioRepository.findById(idUsuario);

        pedido.setUsuario(opUsuario.get());

        String destinatario = pedido.getUsuario().getEmail();
        String assunto = "Cancelamento do pedido " + pedido.getPedidoId();

        String mensagem = conteudoDoEmailPedidoCancelado(pedido);

        Email email = new Email(assunto, mensagem, " grupo5.e.commerceapi@gmail.com",
                Collections.singletonList(destinatario));

        EmailHtmlConteudo htmlConteudo = new EmailHtmlConteudo();
        htmlConteudo.salvarConteudoHtml(mensagem);

        try {
            emailService.enviar(email);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return mensagem;
    }

    private String conteudoDoEmailPedidoCancelado(Pedido pedido) {

        StringBuilder htmlConteudo = new StringBuilder();

        htmlConteudo.append("<!DOCTYPE html>");
        htmlConteudo.append("<html lang='pt-br'>");
        htmlConteudo.append("<head>");
        htmlConteudo.append("<meta charset='UTF-8'>");
        htmlConteudo.append("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");

        htmlConteudo.append("<title>G5 ecommerce</title>");
        htmlConteudo.append("</head>");
        htmlConteudo.append("<body style='width: 100%; height: 100%; font-family: Verdana,sans-serif;'>");
        htmlConteudo.append(
                "<div id='emailBody' style='background: #f2f2f2; color: #2f2f2f; width: 80%; max-width: 700px; position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); border-radius: 20px; box-shadow: 5px 5px 10px #444;'>");
        htmlConteudo.append("<h1 style='text-align: center;'>Obrigado.</h1>");
        htmlConteudo.append(
                " <div id='container' style='background-color: white; text-align: center; padding: 10px; margin: 5%; border-radius: 10px;'>");
        htmlConteudo.append("<h2 style='text-align: center;'>Olá " + pedido.getUsuario().getNome() + "</h2>");
        htmlConteudo.append("<h1>Cancelamento do Pedido Confirmado</h1>");
        htmlConteudo.append("<div box-shadow: 5px 5px 10px #444;>");
        htmlConteudo.append(
                "<h3 style='color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;'>");
        htmlConteudo.append("Informações do cancelamento: </h3>");
        htmlConteudo.append("<p>");
        htmlConteudo.append("<div style='text-align: left; margin: 20px;'>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: #444; color: white; padding: 10px;'>");
        htmlConteudo.append("ID do pedido: ");
        htmlConteudo.append("<span style='color: white; font-weight: normal;'>" + pedido.getCodPedido()
                + "(id do pedido)</span></div>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: white; color: #444; padding: 10px;'>");
        htmlConteudo.append(" As Informações do cancelamento foram enviadas para: ");
        htmlConteudo.append(" <span style='color: #2f2f2f; font-weight: normal;'>(email do usuario)</span>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: #444; color: white; padding: 10px;'>");
        htmlConteudo.append("Data do cancelamento:");
        htmlConteudo.append("<span style='color: white; font-weight: normal;'>(data do cancelamento)</span>");
        htmlConteudo.append("</div></div>");
        htmlConteudo.append(
                "<h3 style='color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;'>");
        htmlConteudo.append("Este foi o pedido cancelado: </h3>");
        htmlConteudo.append("<div style='width: 100%; display: flex; flex-direction: column; align-items: center;'>");
        htmlConteudo.append("<table style='width: 95%; padding: 10px; border-collapse: collapse;'>");
        htmlConteudo.append(
                "<thead style='background-color: #444; color: white; border: 1px solid #f2f2f2; width: 100%; height: 100%;'>");
        htmlConteudo.append("<tr>");
        htmlConteudo.append("<th>Descrição</th>");
        htmlConteudo.append("<th>Preço</th>");
        htmlConteudo.append("</tr>");
        htmlConteudo.append("</thead>");
        htmlConteudo.append("<tbody>");

        for (PedidoItem item : pedido.getItens()) {

            htmlConteudo.append(
                    "<tr style='border: 1px solid #444; widht: 70%; height: 40px; text-transform: capitalize;'>");
            htmlConteudo.append("<td>" + item.getProduto().getNomeProduto() + "</td>");
            htmlConteudo.append("<td>" + item.getQuantidade() + "</td>");
            htmlConteudo.append("<td> R$:  " + item.getValorUnitario() + "</td>");
            htmlConteudo.append("<td> R$: -" + item.getDesconto() + "</td>");
            htmlConteudo.append("<td> R$:  " + item.getValorTotalItem() + "</td>");
            htmlConteudo.append("</tr>");

            htmlConteudo.append( "<img src= '" + item.getProduto().getProdutoImagem() + "' + alt='foto-produto'>");           
        }

        htmlConteudo.append("</tbody>");
        htmlConteudo.append("</table>");
        htmlConteudo.append(
                "<h5 style='border-top: 1px solid #2f2f2f; border-bottom: 1px solid #2f2f2f; padding: 20px; text-align: right;'>");
        htmlConteudo.append("Lamentamos por qualquer inconveniente que possa ter acontecido.</h5>");
        htmlConteudo.append("</div></p></div></div></div></body></html>");

        return htmlConteudo.toString();
    }

    private String construirOConteudoDoEmail(Pedido pedido) {

        StringBuilder htmlConteudo = new StringBuilder();

        htmlConteudo.append("<!DOCTYPE html>");
        htmlConteudo.append("<html lang=\"pt-br\">");
        htmlConteudo.append("<head>");
        htmlConteudo.append("<meta charset=\"UTF-8\">");
        htmlConteudo.append("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        htmlConteudo.append("<title>Z's Little Shop.</title>");
        htmlConteudo.append("</head>");
        htmlConteudo.append("<body style='font-family: Verdana,sans-serif;'>");
        htmlConteudo.append(
                "<div id='emailBody' style='background: #f2f2f2; color: #2f2f2f; width: 100%; height: 100vh; border-radius: 20px; box-shadow: 5px 5px 10px #444;'>");
        htmlConteudo.append("<h1 style='text-align: center; padding-top: 20px;'>Obrigado.</h1>");
        htmlConteudo.append(
                "<div id='container' style='text-align: center; background-color: #fff; padding: 10px; border-radius: 10px; margin: 2%; height: 80%;'>");
        htmlConteudo.append("<h2 style='text-align: center;'> Olá " + pedido.getUsuario().getNome() + ".</h2>");
        htmlConteudo.append("<h3>Pedido Efetuado!</h3>");
        htmlConteudo.append("<p>Obrigado por comprar na Z's Little Shop.</p>");
        htmlConteudo.append("<div style='box-shadow: 5px 5px 10px #444; width:100%;'>");
        htmlConteudo.append(
                "<h3 style='color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left; font-size: 16px'>");
        htmlConteudo.append("Informações do seu pedido: </h3>");
        htmlConteudo.append("<p>");
        htmlConteudo.append("<div style='text-align: left; margin: 20px;'>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: #444; color: #fff; padding: 10px;'>");
        htmlConteudo.append("COD do pedido: ");
        htmlConteudo.append("<span style='color: #fff; font-weight: normal;'>" + pedido.getCodPedido() + "</span>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: white; color: #444; padding: 10px;'>");
        htmlConteudo.append("Enviar cobrança para: ");
        htmlConteudo.append(
                "<span style='color: #2f2f2f; font-weight: normal;'>" + pedido.getUsuario().getEmail() + "</span>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("<div style='font-weight: bold; background-color: #444; color: white; padding: 10px;'>");
        htmlConteudo.append("Data do pedido: ");
        htmlConteudo.append("<span style='color: #fff; font-weight: normal;'>" + pedido.getDataPedido() + "</span>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("</div>");
        htmlConteudo.append(
                "<h3 style='color: #444; padding-bottom: 10px; border-bottom: 1px solid #2f2f2f; margin: 10px 2%; text-align: left;'>");
        htmlConteudo.append("Aqui está o seu pedido: </h3>");
        htmlConteudo.append("<div style='width: 100%; display: flex; flex-direction: column; align-items: center;'>");
        htmlConteudo.append(
                "<table style='width: 100%; padding: 10px; border-collapse: collapse; margin: 20px; border-radius: 20px; font-size: 16px'>");
        htmlConteudo.append("<thead style='background-color: #444; color: white; width: 100%; height: 30px;'>");
        htmlConteudo.append("<tr >");
        htmlConteudo.append("<th>Descrição</th>");
        htmlConteudo.append("<th>Quantidade</th>");
        htmlConteudo.append("<th>Preço</th>");
        htmlConteudo.append("<th>Descontos</th>");
        htmlConteudo.append("<th>Valor Final</th>");
        htmlConteudo.append("</tr>");
        htmlConteudo.append("</thead>");
        htmlConteudo.append("<tbody style='width:100%;'>");

        for (PedidoItem item : pedido.getItens()) {

            htmlConteudo.append(
                    "<tr style='border: 1px solid #444; widht: 70%; height: 40px; text-transform: capitalize;'>");
            htmlConteudo.append("<td>" + item.getProduto().getNomeProduto() + "</td>");
            htmlConteudo.append("<td>" + item.getQuantidade() + "</td>");
            htmlConteudo.append("<td> R$:  " + item.getValorUnitario() + "</td>");
            htmlConteudo.append("<td> R$: -" + item.getDesconto() + "</td>");
            htmlConteudo.append("<td> R$:  " + item.getValorTotalItem() + "</td>");
            htmlConteudo.append("</tr>");
        }

        htmlConteudo.append("</tbody>");
        htmlConteudo.append("</table>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("</p>");
        htmlConteudo.append("</div>");
        htmlConteudo.append(
                "<table style='width: 97.5%; padding: 10px; border-collapse: collapse; margin: 20px; border-radius: 20px; font-size: 16px'>");
        htmlConteudo.append("<thead style='background-color: #444; color: white; width: 100%; height: 30px;'>");
        htmlConteudo.append("<th>Valor Pedido</th>");
        htmlConteudo.append("<th>Desconto</th>");
        htmlConteudo.append("<th>Valor Final</th>");
        htmlConteudo.append("</thead>");
        htmlConteudo.append("<tbody style='width:100%;'>");
        htmlConteudo.append("<tr style='border: 1px solid #444; widht: 70%; height: 40px;'>");
        htmlConteudo.append("<td> R$: " + (pedido.getDesconto() + pedido.getValorTotal()) + "</td>");
        htmlConteudo.append("<td> R$: " + pedido.getDesconto() + "</td>");
        htmlConteudo.append("<td> R$: " + pedido.getValorTotal() + "</td>");
        htmlConteudo.append("</tr>");
        htmlConteudo.append("</tbody>");
        htmlConteudo.append("</table>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("</div>");
        htmlConteudo.append("</body>");

        return htmlConteudo.toString();
    }
}
