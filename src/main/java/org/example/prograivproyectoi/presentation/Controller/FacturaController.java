package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.Factura;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/presentantion/factura")
public class FacturaController
{
    @Autowired
    private Service service;

    @Autowired
    LocaleResolver localeResolver;

    @GetMapping({"", "/"})
    public String showFacturaList(Model model, HttpServletRequest request, HttpServletResponse response,
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

    //--------------------------------------------------------------------------------
    // Crear Factura
    //
    @GetMapping("/create")
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

        return "/presentantion/factura/createFactura";
    }

    @PostMapping("/create")
    public String createFactura(HttpServletRequest request) {
        String productoId = request.getParameter("selectedProduct");
        String clienteId = request.getParameter("selectedProduct");
        String fecha = request.getParameter("fecha");
        String tipoPago = request.getParameter("tipoPago");


        System.out.println("Producto: " + productoId);
        System.out.println("Cliente: " + clienteId);


        try {
            Producto producto = service.getProductoById(Integer.parseInt(productoId));
            //Cliente cliente = service.getClienteById(clienteId);

            Factura factura = new Factura();
            //factura.setProducto(producto);
            // factura.setCedulaCliente(clienteId);
            // factura.setFecha(fecha);
            // factura.setTipoPago(tipoPago);

            service.addFactura(factura);
        } catch (Exception e) {
            request.setAttribute("error", e.getMessage());
            return "presentantion/factura/createFactura";
        }

        return "redirect:/presentantion/factura";
    }
    //--------------------------------------------------------------------------------
    // Otros
    //--------------------------------------------------------------------------------
    @Autowired
    public FacturaController(Service service) {
        this.service = service;
    }

    @GetMapping("/searchProduct")
    public String searchProducto(@RequestParam("id") int id, Model model) {
        try{
            Producto producto = service.getProductoById(id);
            model.addAttribute("producto", producto);
            return "presentantion/factura/createFactura";
        }
        catch (Exception e){
            model.addAttribute("error", "Dato no encontrado");
            return "presentantion/factura/createFactura";
        }
    }

    @PostMapping("/selectProduct")
    public String selectProduct(@RequestParam("id") int id, HttpSession session) {
        try {
            Producto producto = service.getProductoById(id);
            session.setAttribute("selectedProduct", producto);
        } catch (Exception e) {
            // Aquí puedes manejar cualquier error que ocurra durante la selección del producto
        }
        return "redirect:/presentantion/factura/create";
    }

    @GetMapping("/searchCliente")
    public String searchCliente(@RequestParam("id") String id, Model model) {
        try{
            Cliente cliente = service.getClienteById(id);
            model.addAttribute("cliente", cliente);
            return "presentantion/factura/createFactura";
        }
        catch (Exception e){
            model.addAttribute("error", "Dato no encontrado");
            return "presentantion/factura/createFactura";
        }
    }

    @PostMapping("/selectCliente")
    public String selectCliente(@RequestParam("id") String id, HttpSession session) {
        try {
            Cliente cliente = service.getClienteById(id);
            session.setAttribute("selectedCliente", cliente);
        } catch (Exception e) {
            // Aquí puedes manejar cualquier error que ocurra durante la selección del cliente
        }
        return "redirect:/presentantion/factura/create";
    }
}