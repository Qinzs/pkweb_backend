package com.pkweb.backend1.Controllers;

import com.pkweb.backend1.pojo.User;
import com.pkweb.backend1.Repositories.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;
import java.util.Random;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

@RestController
public class ForgetPassword {
    // using UserMapper interface
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JavaMailSender mailSender;

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


    // return ok if it does work
    // print target user with particular email
    // format: "Email": "xxx@xxx.xx"
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
            sendEmail(UserEmail,"Reset your password","This is your verify code: "+code);
            return "OK";
        }else{
            System.out.println("No such a user!");
        }
        return "No such a user!";
    }

    // return ok if all good
    // input 6-digits code
    // format: "Code": "012345"
    @RequestMapping("/digitCode")
    public String inputCode(@RequestBody String s){
        String[] cod = s.split("\"");
        if(cod[3].equals(code)){
            System.out.println("done");
            return "Right code";
        }else{
            System.out.println("wrong code");
        }
        return "Wrong code";
    }

    // return ok if all good
    // reset password of target user
    // format: "Password": "abc"
    @RequestMapping("/resetPassword")
    public String setPasswordByID(@RequestBody String s){
        String[] res = s.split("\"");
        if(getTargetUser()!=null) {
            getTargetUser().setPassword(res[3]);
            userMapper.updatePassword(targetUser);
            System.out.println(targetUser.toString());
            return "OK";
        }else{
            System.out.println("null target user.");
            return "OK";
        }
    }

    public void sendEmail(String to, String subject, String body) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        System.out.println("To done");
        message.setSubject(subject);
        System.out.println("subject done");
        message.setText(body);
        System.out.println("body done");

        mailSender.send(message);
        System.out.println("sendDone");
    }

    private User getTargetUser(){
        return this.targetUser;
    }
}
