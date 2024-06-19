/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.repositories;

import java.util.List;

/**
 *
 * @author Admin
 */
public interface MailRepository {
    void sendMail(String to, String subject, String content);
}
