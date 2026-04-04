package org.sebas.pos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class PosApplication {

    public static void main(String[] args) {
        SpringApplication.run(PosApplication.class, args);
    }

}

@RestController("/")
 class Controller {

    @GetMapping("/helloWorld")
    public String helloWorld(){
        return "<h1>Hello World from Springboot<h1>";

    }
}
