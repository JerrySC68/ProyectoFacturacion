package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@Entity
public class Producto
{
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull(message = "{error.Empty}")
    private Boolean type;
    @NotEmpty(message = "{error.Empty}")
    private String code;
    @NotEmpty(message = "{error.Empty}")
    @Size(max = 2000, message = "{error.Max1000}")
    private String description;
    @NotEmpty(message = "{error.Empty}")
    private String measure;
    @NotNull(message = "{error.Empty}")
    @Min(value = 0, message = "{error.Negative}")
    private Double price;
    @NotNull(message = "{error.Empty}")
    @Min(value = 0, message = "{error.Negative}")
    private Double ivaFee;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------
    public Producto() {
    }

    public Producto(Boolean type, String code, String description, String measure, Double price, Double ivaFee) {
        this.type = type;
        this.code = code;
        this.description = description;
        this.measure = measure;
        this.price = price;
        this.ivaFee = ivaFee;
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

    public Boolean getType() {
        return type;
    }

    public void setType(Boolean type) {
        this.type = type;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getMeasure() {
        return measure;
    }

    public void setMeasure(String measure) {
        this.measure = measure;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getIvaFee() {
        return ivaFee;
    }

    public void setIvaFee(Double ivaFee) {
        this.ivaFee = ivaFee;
    }
}
