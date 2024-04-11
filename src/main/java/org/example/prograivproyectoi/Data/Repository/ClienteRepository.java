package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, String>
{

}
