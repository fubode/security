package com.fubode.NSIS.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fubode.NSIS.model.UsuarioRol;
import com.fubode.NSIS.repository.UsuarioRolRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    @Autowired
    private UsuarioRolRepository usuarioRolRepository;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        AuthCredentials authCredentials = new AuthCredentials();
        try {
            authCredentials = new ObjectMapper().readValue(request.getReader(),AuthCredentials.class);

        }catch (Exception e){
        }
        UsernamePasswordAuthenticationToken usernamePAT = new UsernamePasswordAuthenticationToken(
                authCredentials.getUsuario(),
                authCredentials.getPassword(),
                Collections.emptyList()
        );
        return getAuthenticationManager().authenticate(usernamePAT);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        UserDetailmpl userDetailmpl = (UserDetailmpl)authResult.getPrincipal();
        Long userId = Long.parseLong(String.valueOf(userDetailmpl.getIdUsuario()));
        List<UsuarioRol> findAllByIdUser = new ArrayList<>();
        try {
            findAllByIdUser = findAllByIdUser(userId);
        } catch (Exception e) {
        }
        String token = TokenUtils.createToken(
                userDetailmpl.getNombre(),
                userDetailmpl.getUsername(),
                userDetailmpl.getIdPersona(),
                userDetailmpl.getIdUsuario(),
                null);

        response.addHeader("Authorization","Bearer "+token);
        response.getWriter().flush();
        super.successfulAuthentication(request, response, chain, authResult);
    }

    public List<UsuarioRol> findAllByIdUser(Long idUser) throws Exception {
        List<UsuarioRol> usuarioRoles = usuarioRolRepository.findAllByIdUser(idUser);

        return usuarioRoles;
    }
}
