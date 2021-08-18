package it.univaq.disim.mwt.letsjam.presentation;

import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ErrorPageController implements ErrorController{

    @RequestMapping(value = "error", method = RequestMethod.GET)
    public String handleError(Model model, HttpServletRequest request){
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        
        if(status != null){
            int statusCode = Integer.parseInt(status.toString());
            
            if(statusCode == HttpStatus.NOT_FOUND.value()){
                return "common/404";
            }
            else{
                //Scrivo lo stackTrace dell'eccezione nella pagina di errore
                Exception e = (Exception) request.getAttribute(RequestDispatcher.ERROR_EXCEPTION);
                StringWriter writer = new StringWriter();
                PrintWriter pw = new PrintWriter(writer);
                e.printStackTrace(pw);
                String message = e.getMessage();
                String stackTrace = writer.toString();
                model.addAttribute("stackTrace", stackTrace);
                model.addAttribute("errorMessage", message);
                model.addAttribute("statusCode", statusCode);
                return "common/error";
            }
        }
        model.addAttribute("error", "C'è stato un errore");
        return "common/error";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }

}
