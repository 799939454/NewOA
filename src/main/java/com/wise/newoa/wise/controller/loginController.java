package com.wise.newoa.wise.controller;




import com.wise.newoa.wise.entity.User;
import com.wise.newoa.wise.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


@Controller
//@RequestMapping("/demo")
public class loginController {

    @Autowired
    private UserService service;


    @RequestMapping("/login")
    public String test(){
        System.out.println("这是login");


        return "cxl";
    }


    @ResponseBody
    @RequestMapping("/save")
    public String save(){

        System.out.println("这是save");
        User user=new User();
       // user.setId(15);
        user.setUserName("小明");
        user.setPassWord("123");

        service.save(user);

        return "index";
    }
    @ResponseBody
    @RequestMapping("/save1")
    public User save1(){

        System.out.println("这是save");
        User user=new User();
        // user.setId(15);
        user.setUserName("小明");
        user.setPassWord("123");

        service.save(user);



        return user;
    }
}
