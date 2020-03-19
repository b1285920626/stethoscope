package com.pang.stethoscope.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @RequestMapping("/login")
    public Map<String,Object> doLogin(){
        Map<String,Object>  result = new HashMap<>();
        if(true){
            result.put("user","admin");
        }else{
            result.put("code","600");
        }
        return result;
    }
//    public Map<String,Object> doLogin(@RequestBody Map<String,String> request){
//        Map<String,Object>  result = new HashMap<>();
//        if(request!=null&&true){
//            result.put("user",request.get("user_id"));
//        }else{
//            result.put("code","600");
//        }
//        return result;
//    }
}
