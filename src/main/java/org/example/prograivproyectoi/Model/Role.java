package org.example.prograivproyectoi.Model;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class Role
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(nullable = false)
    private String name;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------
    public Role()
    {
    }

    public Role(String name) {
        this.name = name;
    }

    //-----------------------------------------------------------------------------------------
    //Getters y Setters
    //-----------------------------------------------------------------------------------------

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String role) {
        this.name = role;
    }
}
