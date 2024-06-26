package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface FacturaRepository extends JpaRepository<Factura, Long> {
    Optional<Factura> findById(Long id);
}