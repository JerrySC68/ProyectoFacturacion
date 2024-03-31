package org.example.prograivproyectoi.Controller;


import org.example.prograivproyectoi.Data.Repository.HaciendaSTUBRepository;
import org.example.prograivproyectoi.Model.HaciendaSTUB;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.LocaleResolver;

@Controller
@RequestMapping("/haciendaSTUB")
public class HaciendaSTUBController {
    @Autowired
    private HaciendaSTUBRepository repository;

    @Autowired
    LocaleResolver localeResolver;

    public Boolean existeProveedor(Integer id)
    {
        HaciendaSTUB haciendaSTUB = repository.findById(id).orElse(null);

        return haciendaSTUB != null;
    }

    public String prueba()
    {
        return "prueba";
    }
}
