package org.example.prograivproyectoi.presentation.Controller;

import org.example.prograivproyectoi.logic.Model.Proveedor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.example.prograivproyectoi.logic.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import jakarta.servlet.http.HttpSession;
import org.example.prograivproyectoi.logic.Model.Admin;

@Controller
public class AdminProfileController {
    @Autowired
    private org.example.prograivproyectoi.logic.Service service;

    @Autowired
    public AdminProfileController(Service service) {
        this.service = service;
    }

    @GetMapping("/admin/profile/{userId}")
    public String profile(@PathVariable String userId, Model model, HttpSession httpSession) {
        String loggedUserType = (String) httpSession.getAttribute("userType");
        String loggedUserId = (String) httpSession.getAttribute("adminId");

        if (!"Admin".equals(loggedUserType) || !userId.equals(loggedUserId)) {
            return "redirect:/admin/profile/" + loggedUserId;
        }

        Admin admin = service.findAdminById(userId);

        model.addAttribute("admin", admin);
        model.addAttribute("proveedores", service.findAllWaitProveedores());
        model.addAttribute("aprobados", service.findAllApprovedProveedores());

        return "presentantion/administrador/admin-profile";
    }

    @GetMapping("/aceptar/{proveedorId}")
    public String aceptarProveedor(@PathVariable String proveedorId ,HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        Proveedor proveedor = service.findProveedorById(proveedorId);
        proveedor.setAccepted(true);
        service.updateProveedor(proveedor);

        return "redirect:/admin/profile/" + adminId;
    }

    @GetMapping("/rechazar/{proveedorId}")
    public String rechazarProveedor(@PathVariable String proveedorId ,HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        Proveedor proveedor = service.findProveedorById(proveedorId);
        proveedor.setAccepted(false);
        service.updateProveedor(proveedor);

        return "redirect:/admin/profile/" + adminId;
    }

    @GetMapping("desactivar/{proveedorId}")
    public String desactivarProveedor(@PathVariable String proveedorId ,HttpSession session) {
        String adminId = (String) session.getAttribute("adminId");
        service.deleteProveedor(proveedorId);
        return "redirect:/admin/profile/" + adminId;
    }
}
