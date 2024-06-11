/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.repositories.MailRepository;
import com.nms.services.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private MailRepository mailRepo;

    @Override
    public void sendMail(String to, String subject, String content) {
        this.mailRepo.sendMail(to, subject, content);
    }
}
