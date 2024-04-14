package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
    //Relaciones
    //-----------------------------------------------------------------------------------------
    @ManyToOne
    @JoinColumn(name = "factura_code")
    @ToString.Exclude
    private Factura factura;
}