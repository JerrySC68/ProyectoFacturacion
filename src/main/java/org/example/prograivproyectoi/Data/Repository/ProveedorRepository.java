package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProveedorRepository extends JpaRepository<Proveedor, String> {
    Optional<Proveedor> findByName(String url);
}
