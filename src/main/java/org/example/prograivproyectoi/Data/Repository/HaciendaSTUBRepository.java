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

        clientes.add(new Cliente("117010975", TipoCliente.FISICO, "Rodrigo Castro Rojas", "San Jose", "88888888", "rodrigo@gmail.com", null));
        clientes.add(new Cliente("3102088049",TipoCliente.JURIDICO, "Suplidora Electromecanica","Heredia,Costa Ricas", "22601006","heredia@gdiez.com",null));

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
