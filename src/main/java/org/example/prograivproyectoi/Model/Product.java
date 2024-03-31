package org.example.prograivproyectoi.Model;

import jakarta.persistence.*;

@Entity
public class Product {
    //-----------------------------------------------------------------------------------------
    //Atributos
    //-----------------------------------------------------------------------------------------
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private Boolean type;
    private String code;
    private String description;
    private String measure;
    private Double price;
    private Double ivaFee;

    //-----------------------------------------------------------------------------------------
    //Constructores
    //-----------------------------------------------------------------------------------------
    public Product() {
    }

    public Product(Boolean type, String code, String description, String measure, Double price, Double ivaFee) {
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
