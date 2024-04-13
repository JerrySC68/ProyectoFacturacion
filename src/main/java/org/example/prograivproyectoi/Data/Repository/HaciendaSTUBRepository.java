package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Cliente;

import java.util.ArrayList;
import java.util.List;

public class HaciendaSTUBRepository {
    //------------------------------------------------------------------------------------------
    // Singleton
    //------------------------------------------------------------------------------------------


    //------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------
    List<Cliente> clientes;

    //------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------
    public HaciendaSTUBRepository() {
        clientes = new ArrayList<Cliente>();
        Cliente pcliente;

        clientes.add(new Cliente("117010975", "Rodrigo", "rodrigo@gmail.com"));
        System.out.println("Hacienda num elements: " + clientes.size());
    }

    //------------------------------------------------------------------------------------------
    // Metodos
    //------------------------------------------------------------------------------------------
    public Cliente getClienteById(String id) throws Exception {
        try
        {
            for (Cliente cliente : clientes)
            {
                if (cliente.getId().equals(id))
                {
                    return cliente;
                }
            }
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return null;
    }

    public int getNumClientes() {
        return clientes.size();
    }
}
