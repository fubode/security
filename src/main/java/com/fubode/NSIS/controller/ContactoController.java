package com.fubode.NSIS.controller;

import com.fubode.NSIS.model.Contacto;
import com.fubode.NSIS.model.SecUsuarios;
import com.fubode.NSIS.repository.ContactoRepository;
import com.fubode.NSIS.repository.SecUsuarioRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("usuarios")
@AllArgsConstructor
public class ContactoController {
    private final SecUsuarioRepository secUsuarioRepository;

    @GetMapping
    public List<SecUsuarios> contactoList(){return secUsuarioRepository.findAll();}
}

