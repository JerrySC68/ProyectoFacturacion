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

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Proveedor
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @NotEmpty(message = "{error.Empty}")
    @Pattern(regexp = "^Proveedor-[0-9]+$|Proveedor-[A-Z][0-9]+$")
    private String id;
    @NotEmpty(message = "{error.Empty}")
    private String name;
    @NotEmpty(message = "{error.Empty}")
    @Email(message = "{error.Email}")
    private String email;
    @NotEmpty(message = "{error.Empty}")
    private String password;
    @NotNull(message = "{error.Empty}")
    private boolean accepted;

    //-----------------------------------------------------------------------------------------
    //Relaciones
    //-----------------------------------------------------------------------------------------
    @OneToMany(mappedBy = "proveedor") //@OneToMany(mappedBy = "proveedor", cascade = CascadeType.REMOVE)
    private List<Cliente> clientes = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="act_comercial_id", nullable = true)
    private ActComercial actComercial;

    public void setId(String id) {
        this.id = "Proveedor-" + id;
    }
    public boolean getAccepted(){
        return accepted;
    }
}