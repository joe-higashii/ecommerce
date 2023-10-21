package br.com.serratec.ecommerce.security;

import java.util.Date;
import java.util.Optional;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import br.com.serratec.ecommerce.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTService {

    // chave secreta utilizada pelo JWT para codificar e decodificar o TOKEN.
    private static final String SECURITY_KEY = "ChaveMuitoSecreta";

    public String gerarToken(Authentication authentication) {

        int tempoExpiracao = 86400000; // 24 horas em milissegundos, e pode variar de acordo com a regra de negócio

        // Isso aqui gera uma data com um dia a mais
        Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao); // criando uma data e passando como
                                                                              // parâmetro os milissegundos da data
                                                                              // atual mais o tempo de expiração;

        // Aqui pego o usuário atual da autenticação
        Usuario usuario = (Usuario) authentication.getPrincipal();

        // retorna o TOKEN jwt.
        return Jwts.builder()
                .setSubject(usuario.getUsuarioId().toString()) // identificador único do usuário
                .setIssuedAt(new Date()) // data da geração do TOKEN
                .setExpiration(dataExpiracao) // data de expiração do token
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY) // algoritmo de criptografia e a chave secreta
                .compact(); // pega tudo e gera o token
    }

    // método para retornar o id do usuário dono do TOKEN
    public Optional<Long> obterIdDoUsuario(String token) {
        try { // Aqui pego a claim do TOKEN para achar o usuário dono dele
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            // se achou o id dentro da claim, ele devolve, senão ele devolve null
            return Optional.ofNullable(Long.parseLong(claims.getSubject()));
        } catch (Exception e) {

            return Optional.empty();
        }
    }
}
