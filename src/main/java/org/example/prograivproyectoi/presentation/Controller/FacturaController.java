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

    @PostMapping("/clienteID")
    public String obtenerIDCliente(@RequestParam("clienteID") String clienteID, Model model) {
        Cliente cliente = service.getClienteById(clienteID);

        model.addAttribute("clienteID", cliente.getId());
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