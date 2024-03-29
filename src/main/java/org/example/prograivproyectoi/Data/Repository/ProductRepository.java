package org.example.prograivproyectoi.Data.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.example.prograivproyectoi.Model.Product;

public interface ProductRepository extends JpaRepository<Product, Integer>
{

}
