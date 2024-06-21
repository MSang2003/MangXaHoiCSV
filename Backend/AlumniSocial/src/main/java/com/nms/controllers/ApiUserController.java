/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.controllers;

import com.nms.components.JwtService;
import com.nms.pojo.Users;
import com.nms.services.MailService;
import com.nms.services.UsersSevice;
import java.security.Principal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Map;
import javax.ejb.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author Admin
 */
@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class ApiUserController {

    @Autowired
    private UsersSevice userService;
    @Autowired
    private BCryptPasswordEncoder passswordEncoder;
    @Autowired
    private JwtService jwtService;

    @Autowired
    private MailService mailService;

    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

    @DeleteMapping("/users/{userID}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(Model model, @PathVariable(value = "userID") int id) {
        this.userService.deleteUserById(id);
    }

    @PostMapping(path = "/users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void create(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) throws ParseException {
        Users u = new Users();
        u.setName(params.get("name"));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        u.setRole(params.get("role"));  //Alumni Admin
        u.setIsVerified(false);
        u.setIsLocked(false);
        u.setStudentID(params.get("studentID"));
        u.setSex(params.get("sex"));  //Male Female
        u.setCreatedAt(new Date());
        u.setDoB(dateFormat.parse(params.get("doB")));

        u.setUserName(params.get("username"));

        String password = params.get("password");
        u.setPassword(this.passswordEncoder.encode(password));

        if (file.length > 0) {
            u.setAvatarFile(file[0]);
            if (file.length > 1) {
                u.setCoverImageFile(file[1]);
            }
        }

        this.userService.addUser(u);

//        this.mailService.sendMail("2151053053sang@ou.edu.vn", "Thông báo tài khoải", "Thông tin tài khoản của bạn ...");
    }

    @PostMapping(path = "/update-users/", consumes = {
        MediaType.APPLICATION_JSON_VALUE,
        MediaType.MULTIPART_FORM_DATA_VALUE
    })
    @ResponseStatus(HttpStatus.CREATED)
    public void ưpdate(@RequestParam Map<String, String> params, @RequestPart MultipartFile[] file) {
        Users u = new Users();
        u.setName(params.get("name"));
        u.setUserName(params.get("username"));
        u.setEmail(params.get("email"));
        u.setPhone(params.get("phone"));
        String password = params.get("password");
        u.setPassword(this.passswordEncoder.encode(password));
        u.setRole("Alumni");
        if (file.length > 0) {
            u.setAvatarFile(file[0]);
        }

        this.userService.updateUser(u);
    }

    @PostMapping("/login/")
    @CrossOrigin
    public ResponseEntity<String> login(@RequestBody Users user) {
        if (this.userService.authUser(user.getUserName(), user.getPassword()) == true) {
            String token = this.jwtService.generateTokenLogin(user.getUserName());

            return new ResponseEntity<>(token, HttpStatus.OK);
        }
        return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
    }

    @GetMapping(path = "/current-user/", produces = MediaType.APPLICATION_JSON_VALUE)
    @CrossOrigin
    public ResponseEntity<Users> getCurrentUser(Principal p) {

        Users u = this.userService.getUserByUsername(p.getName());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

}
