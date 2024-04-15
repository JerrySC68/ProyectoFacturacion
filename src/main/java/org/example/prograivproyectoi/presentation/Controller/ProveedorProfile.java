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
public class ProveedorProfile {

    @Autowired
    private org.example.prograivproyectoi.logic.Service service;

    @Autowired
    public ProveedorProfile(org.example.prograivproyectoi.logic.Service service) {
        this.service = service;
    }

    @GetMapping("/proveedor/profile/{userId}")
    public String profile(@PathVariable String userId, Model model, HttpSession httpSession) {
        String sessionUserId = (String) httpSession.getAttribute("proveedorId");
        System.out.println("llegue");

        if (sessionUserId == null || !sessionUserId.equals(userId)) {
            return "redirect:/login";
        }

        List<ActComercial> actComerciales = service.findAllActComercials();
        model.addAttribute("actComerciales", actComerciales);

        Proveedor proveedor = service.findProveedorById(userId);

        model.addAttribute("proveedor", proveedor);
        return "presentantion/proveedor/proveedor-profile";
    }

    @PostMapping("/proveedor/update")
    public String updateProveedor(HttpSession httpSession,
                                  @RequestParam("name") String name,
                                  @RequestParam("email") String email,
                                  @RequestParam("newPassword") String newPassword,
                                  @RequestParam("confirmPassword") String confirmPassword,
                                  @RequestParam("actComercial") String actComercialId,
                                  RedirectAttributes redirectAttributes ) {

        String userId = (String) httpSession.getAttribute("proveedorId");
        Proveedor proveedor = service.findProveedorById(userId);

        if (!newPassword.equals(confirmPassword)) {
            redirectAttributes.addFlashAttribute("error", "Las contraseñas no coinciden");
            return "redirect:/proveedor/profile/" + proveedor.getId();
        }

        proveedor.setName(name);
        proveedor.setEmail(email);
        proveedor.setPassword(newPassword);
        proveedor.setActComercial(service.findActComercialById(actComercialId));
        service.updateProveedor(proveedor);

        return "redirect:/proveedor/profile/" + proveedor.getId();
    }
}