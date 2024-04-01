package presentation;
import logic.Factura;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class FacturaController {

    @GetMapping("/registroFactura.jsp")
    public String mostrarFormulario(Model model) {
        model.addAttribute("factura", new Factura("", "", "", ""));
        return "registroFactura";
    }

    @PostMapping("/listaFactura.jsp")
    public String generarFactura(Factura factura) {
        // Aquí puedes implementar la lógica para guardar la factura en la base de datos o realizar otras operaciones
        return "facturaGenerada"; // Retorna el nombre de la vista de factura generada
    }
}
