package org.example.prograivproyectoi.presentation.Controller;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Null;
import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Model.Factura;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.example.prograivproyectoi.logic.Model.Proveedor;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.context.Context;
import org.thymeleaf.exceptions.TemplateProcessingException;
import org.thymeleaf.spring6.SpringTemplateEngine;
import org.thymeleaf.spring6.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templatemode.TemplateMode;

import java.io.IOException;
import java.io.PrintWriter;
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
        // Verificar si un proveedor está loggeado
        HttpSession session = request.getSession();
        String userType = (String) session.getAttribute("userType");

        if (!"Proveedor".equals(userType)) {
            // Si el usuario no es un proveedor, redirigir al login
            return "redirect:/login";
        }

        // Multi lenguaje
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
        String userType = (String) session.getAttribute("proveedorId");


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

                factura.setCedulaCliente(cliente.getId());
                factura.setCedulaProveedor(userType);
                factura.setDate(fecha);
                factura.setFinalPrice(producto.getPrice());
                factura.setTipoPago(tipoPago);
                factura.setId(service.getNextFacturaId());
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

    @GetMapping("/pdf/{id}")
    public void pdf(@PathVariable("id") Long facturaID, HttpServletResponse response) {
        try {
            Factura factura = service.findFacturaById(facturaID);
            if (factura == null) {
                throw new IllegalArgumentException("Factura no encontrada");
            }

            String fecha = (factura.getDate() != null) ? factura.getDate().toString() : "Fecha no disponible";
            String proveedor = (factura.getCedulaProveedor() != null) ? factura.getCedulaProveedor() : "Proveedor no disponible";
            String cliente = (factura.getCedulaCliente() != null) ? factura.getCedulaCliente() : "Cliente no disponible";
            String tipoPago = (factura.getTipoPago() != null) ? factura.getTipoPago() : "Tipo de pago no disponible";
            String precioFinal = (factura.getFinalPrice() != null) ? factura.getFinalPrice().toString() : "Precio final no disponible";

            PdfWriter writer = new PdfWriter(response.getOutputStream());
            PdfDocument pdf = new PdfDocument(writer);
            Document document = new Document(pdf, PageSize.A4);

            Paragraph title = new Paragraph("Factura");
            Paragraph content = new Paragraph("Número de factura: " + factura.getId()+ "\n" +
                    "Fecha: " + fecha + "\n" +
                    "Proveedor: " + proveedor + "\n" +
                    "Cliente: " + cliente + "\n" +
                    "Tipo de Pago: " + tipoPago + "\n" +
                    "Precio Final: " + precioFinal + "\n"
            );
            document.add(title);
            document.add(content);
            document.close();
            writer.close();
            pdf.close();
        } catch (IOException e) {
            // Manejar la excepción
        }
    }

    @GetMapping("/xml/{id}")
    public void xml(@PathVariable("id") Long facturaID, HttpServletResponse response) {
        try {

            Factura factura = service.findFacturaById(facturaID);
            if (factura == null) {
                throw new IllegalArgumentException("Factura no encontrada");
            }

            SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
            resolver.setApplicationContext(new AnnotationConfigApplicationContext());
            resolver.setPrefix("classpath:/templates/");
            resolver.setSuffix(".xml");
            resolver.setCharacterEncoding("UTF-8");
            resolver.setTemplateMode(TemplateMode.XML);
            SpringTemplateEngine engine = new SpringTemplateEngine();
            engine.setTemplateResolver(resolver);
            Context ctx = new Context();
            ctx.setVariable("factura", factura);
            String xml = engine.process("presentation/factura/xmlView", ctx);
            response.setContentType("application/xml");
            PrintWriter writer = response.getWriter();
            writer.print(xml);
            writer.close();
        } catch (IOException | TemplateProcessingException e) {
            // Manejar la excepción
        }
    }
}