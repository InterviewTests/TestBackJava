package br.com.testesantanderway.testeSantanderWay.controlle;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class Hello {

    @RequestMapping("/")
    @ResponseBody
    public String helloWorld(){
        return "Hello World!!!";
    }
}
