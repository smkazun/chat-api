package org.chatapp.infrastructure.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {



    @GetMapping("/test")
    public ResponseEntity<String> getTest2(){
        return ResponseEntity.ok("you are authenticated!");
    }


}
