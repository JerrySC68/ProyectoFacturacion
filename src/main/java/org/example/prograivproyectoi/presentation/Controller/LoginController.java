package org.example.prograivproyectoi.presentation.Controller;

import jakarta.servlet.http.HttpSession;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class LoginController {
    @Autowired
    private org.example.prograivproyectoi.logic.Service service;

    @Autowired
    public LoginController(Service service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam("userType") String userType,
                        @RequestParam("id") String id,
                        @RequestParam("password") String password,
                        RedirectAttributes redirectAttributes,
                        HttpSession session) {

        String userId = userType + id;

        if (userType.equalsIgnoreCase("Admin-")) {
            // Validate admin credentials
            if (service.validateAdmin(userId, password)) {
                session.setAttribute("userType", "Admin"); // Almacena el tipo de usuario en la sesión
                session.setAttribute("adminId", userId); // Esto almacena el ID del administrador en la sesión
                redirectAttributes.addFlashAttribute("userId", userId);
                return "redirect:/admin/profile/" + userId;
            }
        } else if (userType.equalsIgnoreCase("Proveedor-")) {
            // Validate proveedor credentials
            if (service.validateProveedor(userId, password)) {
                session.setAttribute("userType", "Proveedor"); // Almacena el tipo de usuario en la sesión
                session.setAttribute("proveedorId", userId); // Esto almacena el ID del proveedor en la sesión
                redirectAttributes.addFlashAttribute("userId", userId);
                return "redirect:/proveedor/profile/" + userId;
            }
        }

        // If credentials are not valid, redirect back to login page
        redirectAttributes.addFlashAttribute("error", "Invalid ID or password");
        return "redirect:/login";
    }
}
