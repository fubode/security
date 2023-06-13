package com.fubode.NSIS.repository;

import com.fubode.NSIS.model.SecUsuarios;
import com.fubode.NSIS.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface SecUsuarioRepository extends JpaRepository<SecUsuarios,Integer> {
    Optional<SecUsuarios> findOneByUsuario(String usuario);
}
