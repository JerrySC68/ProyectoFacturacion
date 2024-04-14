package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "administradores") //Nombre de la tabla en la base de datos
public class Admin {
    @Id
    @Pattern(regexp = "^Admin-[0-9]+$|Proveedor-[A-Z][0-9]+$")
    private String id;
    private String name;
    private String email;
    private String password;

    public void setId(String id) {
        this.id = "Admin-" + id;
    }
}