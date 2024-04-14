package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AdminRepository extends JpaRepository<Admin, String> {
    Optional<Admin> findByName(String url); // ?
}