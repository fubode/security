package com.fubode.NSIS.controller;

import com.fubode.NSIS.model.Contacto;
import com.fubode.NSIS.repository.ContactoRepository;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("contactos")
@AllArgsConstructor
public class ContactoController {
    private final ContactoRepository contactoRepository;

    @GetMapping
    public List<Contacto> contactoList(){return contactoRepository.findAll();}
}

