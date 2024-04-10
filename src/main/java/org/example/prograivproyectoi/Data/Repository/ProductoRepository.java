package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductoRepository extends JpaRepository<Producto, Integer>
{

}
