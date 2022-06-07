package com.ecommerce.commerce.web.controller;

import com.ecommerce.commerce.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class LoginController {
    @PostMapping(value = "/login")
    public ResponseEntity<Object> login(@RequestBody User user){
        Map<String,Object> res = new HashMap<>();
        if(user.getName().compareTo("Celin") == 0){
            if(user.getPassword().compareTo("loda") == 0 ){
                res.put("hasError",false);
                res.put("message","logged successfully") ;
            }else{
                res.put("hasError",true);
                res.put("message","mot de passe incorrect") ;
            }
        }else{
            res.put("hasError",true);
            res.put("message","nom d'utilisateur non réconnu") ;
        }
        return ResponseEntity.ok().body(res);
    }
}
