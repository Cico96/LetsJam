package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/")
public class DummyController {
    @GetMapping("/")
    public String home(@RequestParam(name="name", required=false, defaultValue="Jhon Chiccovic") String name, Model model){
        model.addAttribute("name", name);
        return "home/home";
    }
    
    @GetMapping("/error")
    public String error(Model model){
        return "common/error";
    }
    
    @GetMapping("/forbidden")
    public String forbidden(Model model){
        return "common/forbidden";
    }

}
