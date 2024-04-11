package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.example.prograivproyectoi.logic.Model.Cliente;
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
@RequestMapping("/presentantion/clientes")
public class ClienteController {
    @Autowired
    private Service service;

    @Autowired
    LocaleResolver localeResolver;

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

        model.addAttribute("clientes", clientes);

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

        Cliente cliente = new Cliente();
        model.addAttribute("cliente", cliente);

        return "presentantion/clientes/createCliente";
    }

    @PostMapping("/create")
    public String createCliente(@Valid @ModelAttribute Cliente cliente, BindingResult result) {
        //--------------------------------------------------------------------------------
        //Validación
        //--------------------------------------------------------------------------------
        if (result.hasErrors()) {
            return "presentantion/clientes/createCliente";
        }
        service.addCliente(cliente);

        return "redirect:/presentantion/clientes";
    }

    //--------------------------------------------------------------------------------
    // View
    //--------------------------------------------------------------------------------
    @GetMapping("/view")
    public String showClienteViewPage(@RequestParam("id") String id, Model model) {
        try
        {
            Cliente cliente = service.getClienteById(id);
            model.addAttribute("cliente", cliente);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
        }
        return "presentantion/clientes/viewCliente";
    }

    //--------------------------------------------------------------------------------
    // Delete
    //--------------------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteCliente(@RequestParam("id") String id) {
        try
        {
            service.deleteCliente(id);
        } catch (Exception e)
        {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/presentantion/clientes";
    }

    //--------------------------------------------------------------------------------
    //Otros
    //--------------------------------------------------------------------------------
    @GetMapping("/getClienteHacienda")
    public String getClienteHacienda(Model model, @RequestParam("id") String id) {
        System.out.println("ID: " + id);
        Cliente cliente = service.getClienteById(id);
        System.out.println("pCliente: " + cliente.getId());
        model.addAttribute("cliente", cliente);

        return "redirect:/presentantion/createCliente";
    }
}
