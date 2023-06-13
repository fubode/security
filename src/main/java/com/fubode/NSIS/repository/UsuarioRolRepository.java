package com.fubode.NSIS.repository;
import com.fubode.NSIS.model.UsuarioRol;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UsuarioRolRepository extends JpaRepository<UsuarioRol, Long> {
    List<UsuarioRol> findAllByIdUser(Long idUser);
}