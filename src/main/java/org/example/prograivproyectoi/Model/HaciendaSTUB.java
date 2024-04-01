package org.example.prograivproyectoi.Model;

import jakarta.persistence.*;

@Entity
public class HaciendaSTUB
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String nombre;
    private String cedula;

    private Boolean activo;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------
    public HaciendaSTUB() {}

    public HaciendaSTUB(String nombre, String cedula, Boolean activo) {
        this.nombre = nombre;
        this.cedula = cedula;
        this.activo = activo;
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

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    private void init()
    {
        System.out.println("HaciendaSTUB.init");
    }
}
