package org.example.prograivproyectoi.Controller;

import jakarta.validation.Valid;
import org.example.prograivproyectoi.Data.DTO.ProductDTO;
import org.example.prograivproyectoi.Data.Repository.ProductRepository;
import org.example.prograivproyectoi.Model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.i18n.LocaleChangeInterceptor;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;

import java.util.List;
import java.util.Locale;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private ProductRepository repository;

    //--------------------------------------------------------------------------------
    //Muestra la lista de productos
    //--------------------------------------------------------------------------------
    @GetMapping({"", "/"})
    public String showProductsList(Model model) {
        List<Product> products = repository.findAll(Sort.by(Sort.Direction.ASC, "code"));
        model.addAttribute("products", products);

        //--------------------------------------------------------------------------------
        // Multi lenguaje
        //--------------------------------------------------------------------------------
        Locale currentLocale = LocaleContextHolder.getLocale();


        return "products/index";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de creación de productos
    //--------------------------------------------------------------------------------
    @GetMapping("/create")
    public String showProductCreatePage(Model model) {
        ProductDTO productDTO = new ProductDTO();
        model.addAttribute("productDTO", productDTO);
        return "products/createProduct";
    }

    @PostMapping("/create")
    public String createproduct(@Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {
        //Imprimir el objeto productDTO
        System.out.println("Result" + result.toString());

        // Si hay errores en el formulario, se regresa  a la pagina de creación
        if (result.hasErrors()) {
            return "products/createProduct";
        }

        Product product = new Product();
        product.setType(productDTO.getType());
        product.setCode(productDTO.getCode());
        product.setDescription(productDTO.getDescription());
        product.setMeasure(productDTO.getMeasure());
        product.setPrice(productDTO.getPrice());
        product.setIvaFee(productDTO.getIvaFee());

        repository.save(product);

        return "redirect:/products";
    }

    //--------------------------------------------------------------------------------
    //Muestra la página de edición de productos
    //--------------------------------------------------------------------------------
    @GetMapping("/edit")
    public String showProductEditPage(Model model, @RequestParam("id") int id) {
        try {
            Product product = repository.findById(id).get();
            model.addAttribute("product", product);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setType(product.getType());
            productDTO.setCode(product.getCode());
            productDTO.setDescription(product.getDescription());
            productDTO.setMeasure(product.getMeasure());
            productDTO.setPrice(product.getPrice());
            productDTO.setIvaFee(product.getIvaFee());

            model.addAttribute("productDTO", productDTO);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/products";
        }

        return "products/editProduct";
    }

    @PostMapping("/edit")
    public String editProduct(Model model, @RequestParam int id, @Valid @ModelAttribute ProductDTO productDTO, BindingResult result) {
        try {
            Product product = repository.findById(id).get();
            model.addAttribute("product", product);

            if (result.hasErrors()) {
                return "products/editProduct";
            }

            product.setType(productDTO.getType());
            product.setCode(productDTO.getCode());
            product.setDescription(productDTO.getDescription());
            product.setMeasure(productDTO.getMeasure());
            product.setPrice(productDTO.getPrice());
            product.setIvaFee(productDTO.getIvaFee());

            repository.save(product);

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/products";
        }
        return "redirect:/products";
    }

    //--------------------------------------------------------------------------------
    //Eliminar un producto
    //--------------------------------------------------------------------------------
    @GetMapping("/delete")
    public String deleteProduct(@RequestParam("id") int id) {
        try {
            repository.deleteById(id);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
        return "redirect:/products";
    }

    //--------------------------------------------------------------------------------
    //Ver un producto
    //--------------------------------------------------------------------------------
    @GetMapping("/view")
    public String showProductViewPage(Model model, @RequestParam("id") int id) {
        try {
            Product product = repository.findById(id).get();
            model.addAttribute("product", product);

            ProductDTO productDTO = new ProductDTO();
            productDTO.setType(product.getType());
            productDTO.setCode(product.getCode());
            productDTO.setDescription(product.getDescription());
            productDTO.setMeasure(product.getMeasure());
            productDTO.setPrice(product.getPrice());
            productDTO.setIvaFee(product.getIvaFee());

            model.addAttribute("productDTO", productDTO);
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
            return "redirect:/products";
        }

        return "products/viewProduct";
    }

    //--------------------------------------------------------------------------------
    // Bean
    //--------------------------------------------------------------------------------
    @Bean
    public LocaleResolver localeResolver()
    {
        System.out.println("LocaleResolver");
        SessionLocaleResolver slr = new SessionLocaleResolver();
        slr.setDefaultLocale(Locale.US);
        System.out.println("locale" + slr.toString());

        return slr;
    }

    public LocaleChangeInterceptor localeChangeInterceptor() {
        LocaleChangeInterceptor lci = new LocaleChangeInterceptor();
        lci.setParamName("lang");
        System.out.println("lang" +  lci.toString());
        return lci;
    }

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(localeChangeInterceptor());
    }
}
