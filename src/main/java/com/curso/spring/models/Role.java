package com.curso.spring.models;

import jakarta.persistence.*;
import lombok.*;

import java.util.Set;

@Entity
@Table(name = "role")
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Role extends BaseEntity{

    @Column(name = "nombre")
    @Getter @Setter
    private String nombre;

    public Set<Permiso> getPermisos() {
        return permisos;
    }

    // Permisos
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "role")
    @Getter @Setter
    private Set<Permiso> permisos; // Aqui se puede usar un ArrayList
}
