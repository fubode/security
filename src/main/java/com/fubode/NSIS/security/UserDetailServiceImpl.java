package com.fubode.NSIS.security;

import com.fubode.NSIS.model.SecUsuarios;
import com.fubode.NSIS.model.Usuario;
import com.fubode.NSIS.repository.SecUsuarioRepository;
import com.fubode.NSIS.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    @Autowired
    private SecUsuarioRepository usuarioRepository;

    @Override
    public UserDetails loadUserByUsername(String user) throws UsernameNotFoundException {
        SecUsuarios usuario =usuarioRepository
                .findOneByUsuario(user)
                .orElseThrow(()-> new UsernameNotFoundException("el usuario con user " + user+" no existe"));
        return new UserDetailmpl(usuario);
    }
}
