package presentation;
import logic.Cliente;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;


@Controller
public class ClienteController {

    // Lista para almacenar los clientes
    private List<Cliente> listaClientes = new ArrayList<>();

    @GetMapping("/registroClientes.jsp")
    public String mostrarFormulario(Model model) {
        model.addAttribute("cliente", new Cliente(0,"","","","","",""));
        return "/registroClientes.jsp";
    }

    @PostMapping("/guardarCliente")
    public String guardarCliente(@ModelAttribute Cliente cliente) {
        // Agregar el nuevo cliente a la lista
        listaClientes.add(cliente);
        // Redirigir al formulario de registro después de guardar
        return "redirect:/registroClientes";
    }

    @PostMapping("/procesarFactura")
    public String procesarFactura(@ModelAttribute Cliente cliente) {
        return "redirect:/registroFactura";
    }

    // Método para mostrar la lista de clientes (solo para fines de demostración)
    @GetMapping("/listaClientes")
    public String mostrarListaClientes(Model model) {
        model.addAttribute("clientes", listaClientes);
        return "listaClientes";
    }
}

