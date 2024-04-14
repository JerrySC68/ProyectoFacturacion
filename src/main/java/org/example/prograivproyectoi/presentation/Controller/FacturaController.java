package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
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

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/presentantion/factura")
public class FacturaController {
    @Autowired
    private Service service;
    @Autowired
    LocaleResolver localeResolver;

    @Autowired
    public FacturaController(Service service) {
        this.service = service;
    }

    @GetMapping({"", "/"})
    public String showClientesList(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "lang", required = false) String lang) {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        List<Factura> facturas = service.findAllFacturas();
        String id = null;

        model.addAttribute("facturas", facturas);
        model.addAttribute("idBuscar", id);

        return "presentantion/factura/index";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de creación de facturas
    //--------------------------------------------------------------------------------

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

        Producto producto = new Producto();
        model.addAttribute("producto", producto);

        return "/presentantion/factura/createFactura";
    }

    @PostMapping("/create")
    public String createFactura(HttpServletRequest request, HttpSession session) {
        Producto producto = (Producto) session.getAttribute("selectedProduct");
        Cliente cliente = (Cliente) session.getAttribute("selectedCliente");
        String fechaString = request.getParameter("fecha");
        String tipoPago = request.getParameter("tipoPago");


        // Convertir la fecha de String a Date
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date fecha = null;
        try {
            fecha = formatter.parse(fechaString);
        } catch (ParseException e) {
            request.setAttribute("error", "Formato de fecha inválido");
            return "presentantion/factura/createFactura";
        }

        if (producto != null && cliente != null) {
            try {
                Factura factura = new Factura();

                //factura.setCode("1");
                factura.setCedulaProveedor("1");
                factura.setCedulaCliente(cliente.getId());
                factura.setTipoPago(tipoPago);
                factura.setFinalPrice(producto.getPrice());
                factura.setDate(fecha);
                factura.getListProducts().add(producto);


                System.out.println("Factura: " + factura);

                service.addFactura(factura);
            } catch (Exception e) {
                request.setAttribute("error", e.getMessage());
                return "presentantion/factura/createFactura";
            }
        } else {
            request.setAttribute("error", "Producto o cliente no seleccionado");
            return "presentantion/factura/createFactura";
        }
        return "redirect:/presentantion/factura";
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
        System.out.println("Producto: " + id);
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