package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "facturas")
public class Factura {
    @Id
    private String code;

    private Date date;
    private String cedulaProveedor;
    private String cedulaCliente;
    private String tipoPago;
    private Double finalPrice;

    @OneToMany(mappedBy = "factura")
    private List<Producto> listProducts = new ArrayList<>();
}