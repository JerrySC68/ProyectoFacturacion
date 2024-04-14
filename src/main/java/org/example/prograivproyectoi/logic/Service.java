package org.example.prograivproyectoi.logic;

import org.example.prograivproyectoi.Data.Repository.ClienteRepository;
import org.example.prograivproyectoi.Data.Repository.HaciendaSTUBRepository;
import org.example.prograivproyectoi.Data.Repository.ProductoRepository;
import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service("Service")
public class Service {
    //------------------------------------------------------------------------------------------
    // Singleton
    //------------------------------------------------------------------------------------------
    private static Service theInstance;

    public static Service instance() {
        if (theInstance == null) {
            theInstance = new Service();
        }

        return theInstance;
    }

    //------------------------------------------------------------------------------------------
    // Atributos
    //------------------------------------------------------------------------------------------
    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private ClienteRepository clienteRepository;


    private final HaciendaSTUBRepository haciendaSTUBRepository;

    //------------------------------------------------------------------------------------------
    // Constructor
    //------------------------------------------------------------------------------------------
    public Service() {
        haciendaSTUBRepository = new HaciendaSTUBRepository();
        System.out.println("Service created");
        System.out.println("Service num clinetes: " + haciendaSTUBRepository.getNumClientes());
    }

    //------------------------------------------------------------------------------------------
    // Productos
    //------------------------------------------------------------------------------------------
    public void addProducto(Producto pproducto) {
        productoRepository.save(pproducto);
    }

    public List<Producto> getProductoList() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(int id) {
        return productoRepository.findById(id).get();
    }

    public void deleteProducto(int id) {
        productoRepository.deleteById(id);
    }

    public void updateProducto(Producto pproducto) {
        productoRepository.save(pproducto);
    }

    //------------------------------------------------------------------------------------------
    // Clientes
    //------------------------------------------------------------------------------------------
    public void addCliente(Cliente pcliente) {
        clienteRepository.save(pcliente);
    }

    public List<Cliente> getClienteList() {
        return clienteRepository.findAll();
    }

    public Cliente getClienteById(String id) {
        return clienteRepository.findById(id).get();
    }

    public void deleteCliente(String id) {
        clienteRepository.deleteById(id);
    }

    public void updateCliente(Cliente pcliente) {
        clienteRepository.save(pcliente);
    }

    //------------------------------------------------------------------------------------------
    // Hacienda STUB
    //------------------------------------------------------------------------------------------
    public Cliente getClienteHacienda(String id) throws Exception {
        try
        {
            System.out.println("Service getClienteHacienda id: " + id);
            return haciendaSTUBRepository.getClienteById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }
}
