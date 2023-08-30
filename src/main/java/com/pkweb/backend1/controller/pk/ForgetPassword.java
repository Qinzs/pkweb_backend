package com.pkweb.backend1.controller.pk;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;


@RestController
public class ForgetPassword {
    // using UserMapper interface
    @Autowired
    private UserMapper userMapper;

    // Store user's email and new password
    String UserEmail = "z1605235289@gmail.com";
    String newPassword = "1789";

    String code = "000000";

    // Store user with particular email
    User targetUser;

    // setter
    public void setUserEmail(String UserEmail){
        this.UserEmail = UserEmail;
    }

    public void setNewPassword(String newPassword){
        this.newPassword = newPassword;
    }

    public void setTargetUser(User targetUser){
        this.targetUser = targetUser;
    }

    // random generate 6 digits
    public void sixDigit(){
        Random random = new Random();
        String res = "";
        for(int i=0;i<6;i++){
            res+= random.nextInt(10);
        }
        code = res;
        System.out.println("Code is: "+ code);
    }


    // return ok if it does works
    // print target user with particular email
    @RequestMapping("/forget")
    public String checkUserByEmail(@RequestBody String s){
        String[] e =s.split("\"");
        System.out.println(e[3]);
        setUserEmail(e[3]);
        System.out.println(e[3]);
        if(userMapper.findByEmail(UserEmail)!=null) {
            System.out.println("found user!");
            setTargetUser(userMapper.findByEmail(UserEmail));
            System.out.println(targetUser.toString());
            sixDigit();
        }else{
            System.out.println("No such a user!");
        }
        return "OK";
    }

    // return ok ig all good
    // input 6-digits code
    @RequestMapping("/digitCode")
    public String inputCode(@RequestBody String s){
        String[] cod = s.split("\"");
        if(cod[3].equals(code)){
            System.out.println("done");
        }else{
            System.out.println("wrong code");
        }
        return "OK";
    }

    // return ok if all good
    // reset password of target user
    @RequestMapping("/resetPassword")
    public String setPasswordByID(@RequestBody String s){
        String[] res = s.split("\"");
        targetUser.setPassword(res[3]);
        userMapper.updateTargetUser(targetUser);
        System.out.println(targetUser.toString());
        return "OK";
    }
}
