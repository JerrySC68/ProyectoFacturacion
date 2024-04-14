package org.example.prograivproyectoi.Data.Repository;

import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.TipoCliente;

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

        clientes.add(new Cliente("117010976", TipoCliente.FISICO, "Alonso", "alonso@gmail.com"));
        clientes.add(new Cliente("117010975",  TipoCliente.FISICO, "Rodrigo", "rodrigo@gmail.com"));
        clientes.add(new Cliente("3102088049",TipoCliente.JURIDICO, "Suplidora Electromecanica", "heredia@gdiez.com"));
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
