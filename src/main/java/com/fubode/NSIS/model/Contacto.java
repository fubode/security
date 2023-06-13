package com.fubode.NSIS.model;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Data
@Table(name = "contacto")
public class Contacto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idcontacto")
    private Integer id;
    private String nombre;
    @Column(name = "fechanac")
    private LocalDate fechaNacimiento;
    private String celular;
    private String email ;
}
