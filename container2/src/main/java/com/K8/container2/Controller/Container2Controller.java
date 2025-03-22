package com.K8.container2.Controller;

import com.K8.container2.Service.Container2Service;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class Container2Controller {
    private Container2Service container2Service;
    public Container2Controller(Container2Service container2Service) {
        this.container2Service = container2Service;
    }
    @PostMapping("/calculate")
    public Map<String,Object> calculateSum(@RequestBody Map<String,String> requestBody){
         Map<String,Object> response = container2Service.calculateSum(requestBody.get("file"),requestBody.get("product"));
         return response;
    }
}
