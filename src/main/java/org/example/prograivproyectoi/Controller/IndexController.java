package org.example.prograivproyectoi.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

import java.util.Locale;

@Controller
@RequestMapping("/")
public class IndexController
{
    @Autowired
    LocaleResolver localeResolver;
    @GetMapping("/")
    public String showIndex(HttpServletRequest request, HttpServletResponse response,
                            @RequestParam(name = "lang", required = false) String lang)
    {
        if (lang != null) {
            localeResolver.setLocale(request, response, new Locale(lang));
        }
        return "index";
    }
}
