package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.Factura;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/presentantion/factura/listaFactura")
public class FacturaController {
    @Autowired
    private Service service;
    @Autowired
    LocaleResolver localeResolver;

    @GetMapping({"", "/presentantion/factura/listaFactura"})
    public String showProductosList(Model model, HttpServletRequest request, HttpServletResponse response,
                                    @RequestParam(name = "lang", required = false) String lang)
    {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null)
        {
            localeResolver.setLocale(request, response, new Locale(lang));
        }
        List<Factura> facturas = service.findAllFacturas();

        model.addAttribute("facturas", facturas);

        return "presentantion/factura/listaFactura";
    }

    @GetMapping("/FacturaMain")
    public String showFacturaCreatePage(Model model, HttpServletRequest request, HttpServletResponse response,
                                         @RequestParam(name = "lang", required = false) String lang)
    {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        Factura factura = new Factura();
        model.addAttribute("factura", factura);

        return "/presentantion/factura/FacturaMain";
    }

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