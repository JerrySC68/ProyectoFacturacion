package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Entity
public class Cliente
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @NotEmpty(message = "{error.Empty}")
    private String id;

    @Enumerated(EnumType.STRING)
    @NotEmpty(message = "{error.Empty}")
    private TipoCliente typeId;

    @NotEmpty(message = "{error.Empty}")
    private String name;

    @NotEmpty(message = "{error.Empty}")
    @Email(message = "{error.Email}")
    private String email;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------

    public Cliente() {}

    public Cliente(String id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    public Cliente(String id, TipoCliente typeId, String name, String email) {
        this.id = id;
        this.typeId = typeId;
        this.name = name;
        this.email = email;
    }

    //-----------------------------------------------------------------------------------------
    //Getters y Setters
    //-----------------------------------------------------------------------------------------

    public TipoCliente getTypeId() {
        return typeId;
    }

    public void setTypeId(TipoCliente typeId) {
        this.typeId = typeId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
