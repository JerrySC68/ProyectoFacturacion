package org.example.prograivproyectoi.Model;

import jakarta.persistence.*;
import org.springframework.web.bind.annotation.Mapping;

import java.util.List;

@Entity
public class Proveedor
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    private int id;
    @Column(nullable = false)
    private Boolean tipeId;
    @Column(nullable = false)
    private String comercialName;
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String cellphone;
    @Column(nullable = false)
    private String password;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------
    public Proveedor() {
    }

    public Proveedor(int id, Boolean tipeId, String comercialName, String email, String cellphone, String password) {
        this.id = id;
        this.tipeId = tipeId;
        this.comercialName = comercialName;
        this.email = email;
        this.cellphone = cellphone;
        this.password = password;
    }

    //-----------------------------------------------------------------------------------------
    //Getters y Setters
    //-----------------------------------------------------------------------------------------

    public Boolean getTipeId() {
        return tipeId;
    }

    public void setTipeId(Boolean tipeId) {
        this.tipeId = tipeId;
    }

    public String getComercialName() {
        return comercialName;
    }

    public void setComercialName(String comercialName) {
        this.comercialName = comercialName;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
