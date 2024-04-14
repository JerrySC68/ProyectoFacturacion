package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.*;
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
@Table(name = "proveedores") //Nombre de la tabla en la base de datos
public class Proveedor {
    @Id
    @Pattern(regexp = "^Proveedor-[0-9]+$|Proveedor-[A-Z][0-9]+$")
    private String id;
    private String name;
    private String email;
    private String password;
    private boolean accepted;

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