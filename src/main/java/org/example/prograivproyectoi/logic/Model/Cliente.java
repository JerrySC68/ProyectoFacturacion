package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Cliente
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @NotEmpty(message = "{error.Empty}")
    @Pattern(regexp = "^[0-9]+$|^[A-Z][0-9]+$")
    private String id;
    @Enumerated(EnumType.STRING)
    @NotNull(message = "{error.Empty}")
    private TipoCliente typeId;
    @NotEmpty(message = "{error.Empty}")
    private String name;
    @NotEmpty(message = "{error.Empty}")
    private String direccion;
    @NotEmpty(message = "{error.Empty}")
    private String telefono;
    @NotEmpty(message = "{error.Empty}")
    @Email(message = "{error.Email}")
    private String email;

    //-----------------------------------------------------------------------------------------
    //Relaciones
    //-----------------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "proveedor_id")
    private Proveedor proveedor;
}