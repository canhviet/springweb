package com.springweb.controller;

import com.springweb.entity.EmailDetails;
import com.springweb.entity.ThanhVien;
import com.springweb.service.EmailService;
import com.springweb.service.ThanhVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Random;

// Annotation
@Controller
// Class
public class EmailController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private ThanhVienService thanhVienService;

    private int randomNum=-1;
    private int id=-1;

    public int generateRandomNumber() {
        Random random = new Random();
        return 10000 + random.nextInt(90000);
    }

    // Sending a simple Email
    @PostMapping("/processing")
    public String sendMail(@RequestParam("MaTV") int MaTV) {
        randomNum = generateRandomNumber();
        ThanhVien tv = thanhVienService.getByMaTV(MaTV);
        id=MaTV;
        EmailDetails details = new EmailDetails();
        details.setRecipient(tv.getEmail());
        details.setMsgBody(randomNum + "");
        details.setSubject("code");

        String status = emailService.sendSimpleMail(details);

        return "redirect:/forgotpassword";
    }

    @GetMapping("forgotpassword")
    public String inputCode() {
        return "forgotpassword";
    }

    @PostMapping("forgotpassword")
    public String forgotEmail(@RequestParam("code") int code) {
        if(code == randomNum) {
            return "redirect:/updatepassword";
        }
        else {
            return "forgotpassword";
        }
    }

    @GetMapping("updatepassword")
    public String UpdatePassword() {
        return "updatepassword";
    }

    @PostMapping("updatepassword")
    public String UpdateNewPassword(@RequestParam("new_password") String password) {
        ThanhVien tv = thanhVienService.getByMaTV(id);
        tv.setPassword(password);
        thanhVienService.SaveThanhVien(tv);

        return "redirect:/login";
    }
}