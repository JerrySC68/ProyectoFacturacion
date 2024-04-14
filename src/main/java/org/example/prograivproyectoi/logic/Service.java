package org.example.prograivproyectoi.logic;

import org.example.prograivproyectoi.Data.Repository.*;
import org.example.prograivproyectoi.logic.Model.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

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

    @Autowired
    private ActComercialRepository actComercialRepository;

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private FacturaRepository facturaRepository;

    @Autowired
    private ProveedorRepository proveedorRepository;

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
    public void addProducto(Producto producto) {
        productoRepository.save(producto);
    }

    public List<Producto> getProductoList() {
        return productoRepository.findAll();
    }

    public Producto getProductoById(int id) {
        return productoRepository.findById(id).orElseThrow();
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

    //------------------------------------------------------------------------------------------
    // Actividad Comercial
    //------------------------------------------------------------------------------------------

    public List<ActComercial> findAllActComercials() {
        return actComercialRepository.findAll();
    }

    public ActComercial findActComercialById(String id) {
        return actComercialRepository.findById(id).orElseThrow();
    }

    //------------------------------------------------------------------------------------------
    // Admin
    //------------------------------------------------------------------------------------------

    public List<Admin> findAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin admin) {
        return adminRepository.save(admin);
    }

    public Admin findAdminById(String id) {
        return adminRepository.findById(id).orElseThrow();
    }

    public void updateAdmin(Admin admin) {
        adminRepository.save(admin);
    }

    public void deleteAdmin(String id) {
        adminRepository.deleteById(id);
    }

    public boolean validateAdmin(String id, String password) {
        Admin admin = adminRepository.findById(id).orElse(null);
        if (admin != null) {
            return admin.getPassword().equals(password);
        }
        return false;
    }

    //------------------------------------------------------------------------------------------
    // Proveedor
    //------------------------------------------------------------------------------------------

    public List<Proveedor> findAllProveedores() {
        return proveedorRepository.findAll();
    }

    public List<Proveedor> findAllWaitProveedores() {
        return findAllProveedores().stream()
                .filter(proveedor -> !proveedor.getAccepted())
                .collect(Collectors.toList());
    }

    public List<Proveedor> findAllApprovedProveedores() {
        return findAllProveedores().stream()
                .filter(Proveedor::getAccepted)
                .collect(Collectors.toList());
    }

    public Proveedor createProveedor(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    public Proveedor findProveedorById(String id) {
        return proveedorRepository.findById(id).orElseThrow();
    }

    public void updateProveedor(Proveedor proveedor) {
        proveedorRepository.save(proveedor);
    }

    public void deleteProveedor(String id) {
        proveedorRepository.deleteById(id);
    }

    public boolean validateProveedor(String id, String password) {
        Proveedor proveedor = proveedorRepository.findById(id).orElse(null);
        if (proveedor != null) {
            return proveedor.getPassword().equals(password);
        }
        return false;
    }

    public boolean validateDuplicates(String id) {
        return proveedorRepository.findById(id).isPresent();
    }

    //------------------------------------------------------------------------------------------
    // Factura
    //------------------------------------------------------------------------------------------

    public List<Factura> findAllFacturas() {
        return facturaRepository.findAll();
    }

    public void addFactura(Factura factura) {
        facturaRepository.save(factura);
    }

    public Factura findFacturaById(Long id) {
        return facturaRepository.findById(id).orElseThrow();
    }
}