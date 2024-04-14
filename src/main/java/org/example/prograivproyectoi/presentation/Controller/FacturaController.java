package org.example.prograivproyectoi.presentation.Controller;

import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class FacturaController {
    @Autowired
    private Service service;

    @Autowired
    public FacturaController(Service service) {
        this.service = service;
    }

    @PostMapping("/nombreCliente")
    public String obtenerNombreCliente(@RequestParam String nombreCliente, Model model) {
        List<Cliente> clientes = service.getClienteList();

        for(Cliente cliente : clientes){
            if(cliente.getName().equals(nombreCliente)){
                model.addAttribute("nombreC", cliente.getName());
            }else{
                model.addAttribute("nombreC","...");
            }
        }

        return "redirect:/";
    }

    @PostMapping("/codigoProducto")
    public String obtenerCodigoProducto(@RequestParam("codigoProducto") String codigoProducto, Model model) {
        List<Producto> productos = service.getProductoList();

        for(Producto product : productos){
            if(product.getCode().equals(codigoProducto)){
                model.addAttribute("codigoProducto", product.getCode());
            }else{
                model.addAttribute("codigoProducto","...");
            }
        }

        return "redirect:/";
    }
}