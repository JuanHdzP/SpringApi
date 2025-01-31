package com.curso.spring.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "user")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class User extends BaseEntity {

    // JsonProperty es opcional, solo es para que los roles sean solo lectura y
    // no lo pueda modificar el usuario final por medio de alguna mala practica
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_role")
    @Getter @Setter
    private Role role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password")
    @Getter @Setter
    private String password;

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    @Column(name = "apellido")
    @Getter @Setter
    private String apellido;

    @Column(name = "email")
    @Getter @Setter
    private String email;

    @Column(name = "telefono")
    @Getter @Setter
    private String telefono;

    @Column(name = "fecha_nac")
    @Getter @Setter
    private Date fechaNac;

}

