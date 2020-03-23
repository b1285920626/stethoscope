package com.pang.stethoscope.controller;

import com.pang.stethoscope.service.intf.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

@Controller
public class HelloWorldController {

    @Autowired
    private UserService userService;

    @RequestMapping("/hello")
    public String sayHello(){
        return "hello";
    }

//    @RequestMapping("/background.png")
//    @ResponseBody
//    public byte[] background() throws IOException {
//        File file = new File("classpath:resource/static/background.png");
//        FileInputStream inputStream = new FileInputStream(file);
//        byte[] bytes = new byte[inputStream.available()];
//        inputStream.read(bytes, 0, inputStream.available());
//        return bytes;
//    }

    @RequestMapping("findUserList")
    @ResponseBody
    public List<Map<String,Object>> findUserList(){
        return userService.findUserList();
    }
}
