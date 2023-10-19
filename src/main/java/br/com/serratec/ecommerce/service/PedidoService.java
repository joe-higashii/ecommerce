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

import br.com.serratec.ecommerce.dto.pedido.PedidoRequestDTO;
import br.com.serratec.ecommerce.dto.pedido.PedidoResponseDTO;
import br.com.serratec.ecommerce.dto.pedidoItem.PedidoItemResponseDTO;
import br.com.serratec.ecommerce.model.EmailHtmlConteudo;
import br.com.serratec.ecommerce.model.Pedido;
import br.com.serratec.ecommerce.model.PedidoItem;
import br.com.serratec.ecommerce.model.email.Email;
import br.com.serratec.ecommerce.repository.PedidoRepository;

@Service
public class PedidoService {

    @Autowired
    private PedidoRepository pedidoRepository;

    
    @Autowired
    private PedidoItemService pedidoItemService;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private EmailService emailService;

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

        //pega os itens
        List<PedidoItem> listaSalvaProdutos = pedidoRequest.getItens().stream()
        .map(item -> mapper.map(item, PedidoItem.class)).collect(Collectors.toList());

        Pedido pedido = adicionarPedido(pedidoRequest);

        //UsuarioRequestDTO usuarioRequest = pedidoRequest.getUsuario();

        pedido.setItens(listaSalvaProdutos);
        
        //pedido.setUsuario(mapper.map(usuarioRequest,Usuario.class));
        

        List<PedidoItem> itensCadastrados = itemsPedido(pedido);

        List<PedidoItemResponseDTO> itensResponse = itensCadastrados.stream().map(item -> 
            mapper.map(item, PedidoItemResponseDTO.class)
        ).collect(Collectors.toList());
        
        PedidoResponseDTO pedidoResponse =  mapper.map(pedido, PedidoResponseDTO.class);
        pedidoResponse.setItens(itensResponse);

        enviarEmailPedido(pedidoResponse);

        return pedidoResponse;
    }


    public Pedido adicionarPedido(PedidoRequestDTO pedidoRequest) {

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedido.setDtPedido(new Date());

        pedido.setPedidoId(0l);

        pedido = pedidoRepository.save(pedido);

        return pedido;
    }

    public List<PedidoItem> itemsPedido(Pedido pedido) {

        List<PedidoItem> adicionadas = new ArrayList<>();

        for (PedidoItem pedidoItem : pedido.getItens()) {

            pedidoItem.setPedido(pedido);

            pedidoItemService.adicionar(pedidoItem);

            adicionadas.add(pedidoItem);
        }
        
        return adicionadas;
    }

    public PedidoResponseDTO atualizar(long id, PedidoRequestDTO pedidoRequest) {

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        pedidoRequest.setId(id);

        Pedido pedido = mapper.map(pedidoRequest, Pedido.class);

        pedidoRepository.save(pedido);

        return mapper.map(pedido, PedidoResponseDTO.class);
    }

    public void deletar(Long id) {

        obterPorId(id);

        pedidoRepository.deleteById(id);
    }

    private void enviarEmailPedido(PedidoResponseDTO pedido) {
        String destinatario = pedido.getUsuario().getEmail();
        String assunto = "Detalhes do seu pedido #" + pedido.getId();
        String mensagem = construirOConteudoDoEmail(pedido);

        Email email = new Email(assunto, mensagem, "d.conti133@gmail.com", Collections.singletonList(destinatario));

        EmailHtmlConteudo htmlConteudo = new EmailHtmlConteudo();
        htmlConteudo.salvarConteudoHtml(mensagem);

        try {
            emailService.enviar(email);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String construirOConteudoDoEmail(PedidoResponseDTO pedido) {
        StringBuilder htmlConteudo = new StringBuilder();
        // htmlConteudo.append("<html><body>");
        htmlConteudo.append("<table align=\"center\" border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"600\">");
        htmlConteudo.append("<tr><td align=\"center\" bgcolor=\"#70bbd9\" style=\"padding: 40px 0 30px 0;\" width=\"300\" height=\"230\">Grupo 5 E-Commerce</td></tr>");
        htmlConteudo.append("<tr><td bgcolor=\"#87CEFA\" style=\"padding: 40px 30px 40px 30px;\">");
        htmlConteudo.append("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
        htmlConteudo.append("<tr><td>Olá " + pedido.getUsuario().getNome() + ",</td></tr>");
        htmlConteudo.append("<tr><td><strong>Detalhes do Pedido #" + pedido.getNrPedido() + "</strong></td></tr>");
        htmlConteudo.append("<tr><td>");
        htmlConteudo.append("<table border=\"1\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
        htmlConteudo.append("<tr><td width=\"260\" valign=\"top\"><strong>Itens do Pedido</strong></td>");
        htmlConteudo.append("<td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>");
        htmlConteudo.append("<td width=\"260\" valign=\"top\"><strong>Valor Unitário</strong></td></tr>");
        
        for (PedidoItemResponseDTO item : pedido.getItens()) {
            htmlConteudo.append("<tr>");
            htmlConteudo.append("<td>" + item.getProduto().getProdNome() + " - Quantidade: " + item.getQtd() + "</td>");
            htmlConteudo.append("<td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>");
            htmlConteudo.append("<td>R$" + item.getVlUn() + "</td>");
            htmlConteudo.append("</tr>");
        }
        
        htmlConteudo.append("</table></td></tr>");
        htmlConteudo.append("<tr><td bgcolor=\"#B0C4DE\" style=\"padding: 30px 30px 30px 30px;\">");
        htmlConteudo.append("<table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\">");
        htmlConteudo.append("<tr><td width=\"75%\">Grupo 5 Enterprises</td>");
        htmlConteudo.append("<td align=\"right\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\">");
        htmlConteudo.append("<tr><td><a href=\"http://www.firjansenai.com.br/\">SENAI</a></td>");
        htmlConteudo.append("<td style=\"font-size: 0; line-height: 0;\" width=\"20\">&nbsp;</td>");
        htmlConteudo.append("<td><a href=\"http://www.serratec.org/\">SERRATEC</a></td></tr>");
        htmlConteudo.append("</table></td></tr></table></td></tr></table></td></tr></table>");
        // </body></html>");

        return htmlConteudo.toString();
    }
}
