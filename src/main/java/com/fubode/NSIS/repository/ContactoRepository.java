package com.fubode.NSIS.repository;

import com.fubode.NSIS.model.Contacto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactoRepository extends JpaRepository<Contacto,Integer> {
}
