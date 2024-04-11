package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.prograivproyectoi.logic.Model.Producto;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/presentantion/productos")
public class ProductoController
{

    @Autowired
    private Service service;

    @Autowired
    LocaleResolver localeResolver;

    //--------------------------------------------------------------------------------
    //Muestra la lista de productos
    //--------------------------------------------------------------------------------
    @GetMapping({"", "/"})
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
        List<Producto> productos = service.getProductoList();

        model.addAttribute("productos", productos);

        return "presentantion/productos/index";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de creación de productos
    //--------------------------------------------------------------------------------
    @GetMapping("/create")
    public String showProductoCreatePage(Model model, HttpServletRequest request, HttpServletResponse response,
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

        return "/presentantion/productos/createProducto";
    }

    @PostMapping("/create")
    public String createProducto(@Valid @ModelAttribute Producto producto, BindingResult result) {

        //--------------------------------------------------------------------------------
        // Si hay errores en el formulario, se regresa  a la pagina de creación
        //--------------------------------------------------------------------------------
        if (result.hasErrors()) {
            return "presentantion/productos/createProducto";
        }
        service.addProducto(producto);

        return "redirect:/presentantion/productos";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de edición de productos
    //--------------------------------------------------------------------------------
    @GetMapping("/edit")
    public String showProductoEditPage(Model model, @RequestParam("id") int id, HttpServletRequest request, HttpServletResponse response,
                                       @RequestParam(name = "lang", required = false) String lang) {

        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        try {
            Producto producto = service.getProductoById(id);
            model.addAttribute("producto", producto);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/presentantion/productos";
        }

        return "/presentantion/productos/editProducto";
    }

    @PostMapping("/edit")
    public String editProducto(Model model, @RequestParam int id, @Valid @ModelAttribute Producto pproducto, BindingResult result) {

        //--------------------------------------------------------------------------------
        // Si hay errores en el formulario, se regresa
        //--------------------------------------------------------------------------------
        if (result.hasErrors()) {
            return "presentantion/productos/editProducto";
        }

        try {
            Producto producto = service.getProductoById(id);
            model.addAttribute("producto", producto);

            producto.setType(pproducto.getType());
            producto.setCode(pproducto.getCode());
            producto.setDescription(pproducto.getDescription());
            producto.setMeasure(pproducto.getMeasure());
            producto.setPrice(pproducto.getPrice());
            producto.setIvaFee(pproducto.getIvaFee());

            service.updateProducto(producto);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/presentantion/productos/edit";
        }
        return "redirect:/presentantion/productos";
    }

    //--------------------------------------------------------------------------------
    //Eliminar un producto
    //--------------------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteProducto(@RequestParam("id") int id) {
        try {
            service.deleteProducto(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/presentantion/productos";
    }

    //--------------------------------------------------------------------------------
    //Ver un producto
    //--------------------------------------------------------------------------------
    @GetMapping("/view")
    public String showProductoViewPage(Model model, @RequestParam("id") int id)
    {
        try
        {
            Producto producto = service.getProductoById(id);
            model.addAttribute("producto", producto);
        }
        catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/presentantion/productos";
        }
        return "/presentantion/productos/viewProducto";
    }
}
