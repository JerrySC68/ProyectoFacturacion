package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.example.prograivproyectoi.logic.Model.Cliente;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.LocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/presentantion/clientes")
public class ClienteController {
    @Autowired
    private Service service;

    @Autowired
    LocaleResolver localeResolver;
    @Qualifier("messageSource")
    @Autowired
    private MessageSource messageSource;
    @Autowired
    private HttpSession httpSession;

    //--------------------------------------------------------------------------------
    //Muestra la lista de clientes
    //--------------------------------------------------------------------------------
    @GetMapping({"", "/"})
    public String showClientesList(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "lang", required = false) String lang) {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        List<Cliente> clientes = service.getClienteList();
        String id = null;

        model.addAttribute("clientes", clientes);
        model.addAttribute("idBuscar", id);

        return "presentantion/clientes/index";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de creación de clientes
    //--------------------------------------------------------------------------------
    @GetMapping("/create")
    public String showClienteCreatePage(Model model, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "lang", required = false) String lang) {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }

        System.out.println("Dentro de Show Create");
        System.out.println("Model: " + model.toString());

        if (model.getAttribute("cliente") == null) {
            System.out.println("Cliente es null");
            Cliente cliente = new Cliente();
            model.addAttribute("cliente", cliente);
        } else {
            System.out.println("Cliente no es null");

        }

        return "presentantion/clientes/createCliente";
    }

    @PostMapping("/create")
    public String createCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result)
    {
        System.out.println("Dentro de Create");
        //--------------------------------------------------------------------------------
        // Si hay errores en el formulario, se regresa  a la pagina de creación
        //--------------------------------------------------------------------------------
        System.out.println("Dentro de Create");

        if (result.hasErrors()) {
            return "presentantion/clientes/createCliente";
        }
        service.addCliente(cliente);

        return "redirect:/presentantion/clientes";
    }

    @PostMapping(value = "/create", params = "add")
    public String addCliente(@RequestParam("id") String id, Model model) throws Exception
    {
        try {
            Cliente pCliente = service.getClienteHacienda(id);

            if (pCliente == null)
            {
                pCliente = new Cliente();
                model.addAttribute("cliente", pCliente);
                throw new Exception("error.NotFind");
            }

            model.addAttribute("cliente", pCliente);
        }
        catch (Exception e)
        {
            String error =  messageSource.getMessage(e.getMessage(), null, Locale.getDefault());

            System.out.println("Error: " + error);
            model.addAttribute("error", error);;
            return "presentantion/clientes/createCliente";
        }

        return "presentantion/clientes/createCliente";
    }

    //--------------------------------------------------------------------------------
    // View
    //--------------------------------------------------------------------------------
    @GetMapping("/view")
    public String showClienteViewPage(Model model, @RequestParam("id") String id, HttpServletRequest request, HttpServletResponse response, @RequestParam(name = "lang", required = false) String lang) {
        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }
        try {
            Cliente cliente = service.getClienteById(id);
            model.addAttribute("cliente", cliente);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/presentantion/clientes";
        }
        return "/presentantion/clientes/viewCliente";
    }

    //--------------------------------------------------------------------------------
    // Delete
    //--------------------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteCliente(@RequestParam("id") String id) {
        try {
            service.deleteCliente(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/presentantion/clientes";
    }

    //--------------------------------------------------------------------------------
    // Otros
    //--------------------------------------------------------------------------------
}
