package org.example.prograivproyectoi.logic.Model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "act_comercial") //Nombre de la tabla en la base de datos
public class ActComercial {
    @Id
    int id;
    String name;
    String description;
}