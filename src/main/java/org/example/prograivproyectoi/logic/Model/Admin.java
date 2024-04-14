package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
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
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @NotEmpty(message = "{error.Empty}")
    @Pattern(regexp = "^Admin-[0-9]+$|Proveedor-[A-Z][0-9]+$")
    private String id;
    @NotEmpty(message = "{error.Empty}")
    private String name;
    @NotEmpty(message = "{error.Empty}")
    @Email(message = "{error.Email}")
    private String email;
    @NotEmpty(message = "{error.Empty}")
    private String password;

    //-----------------------------------------------------------------------------------------
    //Setters y Getters
    //-----------------------------------------------------------------------------------------
    public void setId(String id) {
        this.id = "Admin-" + id;
    }
}