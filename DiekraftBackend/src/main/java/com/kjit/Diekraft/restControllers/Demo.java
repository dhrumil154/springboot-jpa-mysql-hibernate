package com.kjit.Diekraft.restControllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/demo")
public class Demo {

    @GetMapping("/ok")
    public ResponseEntity<String> get(){
        return new ResponseEntity<String>("hello", HttpStatus.OK);
    }

}
