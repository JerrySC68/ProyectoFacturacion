package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Table;

@Table(name = "tipo_cliente")
public enum TipoCliente {
    FISICO,
    JURIDICO
}