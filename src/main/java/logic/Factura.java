package logic;

import java.util.ArrayList;
import java.util.List;

import java.util.ArrayList;
import java.util.List;

public class Factura {
    private String codigoFactura;
    private String fechaFactura;
    private String cedulaProveedor;
    private String cedulaCliente;
    private List<Integer> idProductos;
    private List<Integer> cantidadProductos;

    public Factura(String codigoFactura, String fechaFactura, String cedulaProveedor, String cedulaCliente) {
        this.codigoFactura = codigoFactura;
        this.fechaFactura = fechaFactura;
        this.cedulaProveedor = cedulaProveedor;
        this.cedulaCliente = cedulaCliente;
        this.idProductos = new ArrayList<>();
        this.cantidadProductos = new ArrayList<>();
    }

    public String getCodigoFactura() {
        return codigoFactura;
    }

    public void setCodigoFactura(String codigoFactura) {
        this.codigoFactura = codigoFactura;
    }

    public String getFechaFactura() {
        return fechaFactura;
    }

    public void setFechaFactura(String fechaFactura) {
        this.fechaFactura = fechaFactura;
    }

    public String getCedulaProveedor() {
        return cedulaProveedor;
    }

    public void setCedulaProveedor(String cedulaProveedor) {
        this.cedulaProveedor = cedulaProveedor;
    }

    public String getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(String cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public List<Integer> getIdProductos() {
        return idProductos;
    }

    public void setIdProductos(List<Integer> idProductos) {
        this.idProductos = idProductos;
    }

    public List<Integer> getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(List<Integer> cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

    public void agregarProducto(Integer idProducto, int cantidad) {
        this.idProductos.add(idProducto);
        this.cantidadProductos.add(cantidad);
    }
}


