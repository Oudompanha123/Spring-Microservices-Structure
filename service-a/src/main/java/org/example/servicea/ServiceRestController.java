package org.example.servicea;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class ServiceRestController {

    @GetMapping("/helloWorld")
    public String hello() {return "hello world from service II";}
}