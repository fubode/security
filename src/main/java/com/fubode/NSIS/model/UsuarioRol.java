package com.fubode.NSIS.model;


import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "sec_usuario_roles")
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user_rol")
    private Long idUserRol;

    @Column(name = "id_rol")
    private Long idRol;

    @Column(name = "id_user")
    private Long idUser;
}