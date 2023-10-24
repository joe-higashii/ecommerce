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

    private static final String SECURITY_KEY = "ChaveMuitoSecreta";

    public String gerarToken(Authentication authentication) {

        int tempoExpiracao = 86400000;

        Date dataExpiracao = new Date(new Date().getTime() + tempoExpiracao);
        Usuario usuario = (Usuario) authentication.getPrincipal();

        return Jwts.builder()
                .setSubject(usuario.getUsuarioId().toString())
                .setIssuedAt(new Date())
                .setExpiration(dataExpiracao)
                .signWith(SignatureAlgorithm.HS256, SECURITY_KEY)
                .compact();
    }

    public Optional<Long> obterIdDoUsuario(String token) {
        try {
            Claims claims = Jwts
                    .parser()
                    .setSigningKey(SECURITY_KEY)
                    .parseClaimsJws(token)
                    .getBody();
            return Optional.ofNullable(Long.parseLong(claims.getSubject()));
        } catch (Exception e) {

            return Optional.empty();
        }
    }
}
