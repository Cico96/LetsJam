package it.univaq.disim.mwt.letsjam.presentation;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
@RequestMapping("flat")
@Controller
public class FlatController {

    @GetMapping("/")
    public String view(){
        return "create-upload/flat";
    }
}
