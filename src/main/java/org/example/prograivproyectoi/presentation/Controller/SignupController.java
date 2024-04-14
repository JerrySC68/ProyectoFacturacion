package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.prograivproyectoi.logic.Model.ActComercial;
import org.example.prograivproyectoi.logic.Model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class SignupController {
    @Autowired
    private org.example.prograivproyectoi.logic.Service service;

    @Autowired
    public SignupController(org.example.prograivproyectoi.logic.Service service) {
        this.service = service;
    }

    @GetMapping("/signup")
    public String signup(Model model) {
        List<ActComercial> actComerciales = service.findAllActComercials();
        model.addAttribute("actComerciales", actComerciales);
        return "signup";
    }

    @PostMapping("/signup")
    public String registerProveedor(@RequestParam("id") String id,
                                    @RequestParam("name") String name,
                                    @RequestParam("email") String email,
                                    @RequestParam("password") String password,
                                    @RequestParam("actComercial") String actComercialId,
                                    RedirectAttributes redirectAttributes,
                                    HttpSession httpSession) {

        if(service.validateDuplicates("Proveedor-" + id)) { // Cambiar a service.validateDuplicates("Proveedor-" + id).isPresent()
            redirectAttributes.addFlashAttribute("error", "El proveedor ya existe");
            return "redirect:/signup";
        }

        Proveedor proveedor = new Proveedor();

        proveedor.setId(id);
        proveedor.setName(name);
        proveedor.setEmail(email);
        proveedor.setPassword(password);

        ActComercial actComercial = null;
        if (!actComercialId.isEmpty()) {
            actComercial = service.findActComercialById(actComercialId);
        }
        proveedor.setActComercial(actComercial);
        Proveedor savedProveedor = service.createProveedor(proveedor);

        httpSession.setAttribute("proveedorId", savedProveedor.getId()); // Guardar el ID del proveedor en la sesión

        return "redirect:/proveedor/profile/" + savedProveedor.getId();
    }

    @GetMapping("/signup/proveedor/profile/{id}")
    public String proveedorProfile(@PathVariable("id") String id, Model model) {
        Proveedor proveedor = service.findProveedorById(id);
        model.addAttribute("proveedor", proveedor);
        return "presentantion/proveedor/proveedor-profile";
    }
}
