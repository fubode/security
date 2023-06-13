package com.fubode.NSIS.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sec_usuarios")
public class SecUsuarios {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;
    @Column(name = "id_persona")
    private Integer idPersona;
    @Column(name = "cod_usuario")
    private String codUsuario;
    private String password;
    private String usuario;

}
