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
public interface StatsRepository {

    List<Object[]> statsAlumniByYear();

    List<Object[]> statsAmountUser(String role, int year, String period);

    List<Object[]> statsPostByUser(String name, int year, String period);
}
