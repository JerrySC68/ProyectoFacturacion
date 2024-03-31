package org.example.prograivproyectoi.Data.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class HaciendaSTUBDTO
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @NotNull(message = "{error.Empty}")
    private int id;
    @NotEmpty(message = "{error.Empty}")
    private String nombre;
    @NotEmpty(message = "{error.Empty}")
    private String cedula;
    @NotNull(message = "{error.Empty}")
    private Boolean activo;

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
}
