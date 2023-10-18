package br.com.serratec.ecommerce.security;

import java.io.IOException;
import java.util.Collections;
import java.util.Optional;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
public class JWTAuthenticationFilter extends OncePerRequestFilter {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    // método principal onde toda requisição bate antes de chegar no controller
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {

        // 1ª Pegar o TOKEN da requisição...
        String token = obterToken(request);

        // 2º Pegar o id do TOKEN...
        Optional<Long> id = jwtService.obterIdDoUsuario(token);

        // 3º Saber se o id existe, se veio algum id no TOKEN...
        if (id.isPresent()) {

            // Pego o usuário dono do TOKEN pelo seu id
            UserDetails usuario = customUserDetailsService.obterUsuarioPeloId(id.get());

            // nesse ponto verificamos se o usuário está autenticado ou não
            // aqui também poderia ser validado as permissões dos usuários
            UsernamePasswordAuthenticationToken autenticacao = new UsernamePasswordAuthenticationToken(usuario, null,
                    usuario.getAuthorities());

            // mudo a autenticação para a própria requisição.
            autenticacao.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            // pegando a autenticação e setando no contexto
            // repasso a autenticação para o contexto do SpringSecurity
            // A partir de agora o spring toma conta de tudo pra mim
            SecurityContextHolder.getContext().setAuthentication(autenticacao);
        }

        // 4º Filtrar regras do usuário...
        filterChain.doFilter(request, response);
    }

    private String obterToken(HttpServletRequest request) {

        String token = request.getHeader("Authorization");

        if (token == null || !token.startsWith("Bearer")) {
            return null;
        }

        // Bearer 6sD6sf9s8DfsDF6a5sd4a6s5d4as6d54as65d4as6d5466a5s4d65as4d65
        return token.substring(7);
    }

}
