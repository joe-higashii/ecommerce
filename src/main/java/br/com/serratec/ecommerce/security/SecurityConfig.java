package br.com.serratec.ecommerce.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity // aqui informo que é uma classe de configuração de segurança do SpringSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private CustomUserDetailsService customUserDetailsService;

    @Autowired
    private JWTAuthenticationFilter jwtAuthenticationFilter;

    // Método que devolve uma instância do objeto que sabe codificar e decodificar
    // pelo SpringSecurity
    // Isso não tem nada a ver com JWT, aqui é do springSecurity
    // Aqui será usado para codificar a senha do usuário
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // Método padrão para configurar o nosso customUserDetailServicer com o nosso
    // método de codificar.
    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder
                .userDetailsService(customUserDetailsService)
                .passwordEncoder(passwordEncoder());
    }

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    // Método que tem a configuração global de acessos e permissões por rotas
    @Override
    protected void configure(HttpSecurity http) throws Exception { // é aqui que a MAGIA acontece
        // Parte padrão das configurações, por enquanto ignorar os avisos
        http
                .cors()
                .and()
                .csrf()
                .disable()
                // .exceptionHandling().authenticationEntryPoint((req, res, e) ->
                // rsp.sendError(401));
                .exceptionHandling().authenticationEntryPoint(new RestAuthenticationEntryPoint())
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()

                // Daqui pra baixo é onde vamos futucar e fazer nossas validações dinâmicas
                // Aqui vamos informar as rotas que vão ou não precisar de autenticação e ou
                // autorização
                .antMatchers(HttpMethod.POST, "/api/usuarios", "/api/usuarios/login", "/api/tipos-usuarios")
                .permitAll() // estou informando que todos podem acessar esses endpoints (ROTAS) sem
                             // autenticação
                // .antMatchers("/api/produtos").hasAuthority("admin")
                // .antMatchers("/api/logs").hasRole("admin")
                .anyRequest() // os demais endpoints devem estar autenticados
                .authenticated(); // Digo que qualquer outro endpoint não mapeado acima deve cobrar autenticação

        http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    }
}
