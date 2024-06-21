/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.nms.services.impl;

import com.nms.repositories.StatsRepository;
import com.nms.services.StatsService;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Admin
 */
@Service
public class StatsServiceImpl implements StatsService {

    @Autowired
    private StatsRepository statsRepo;

    @Override
    public List<Object[]> statsAlumniByYear() {
        return this.statsRepo.statsAlumniByYear();
    }

    @Override
    public List<Object[]> statsAmountUser(String role, int year, String period) {
        return this.statsRepo.statsAmountUser(role, year, period);
    }

    @Override
    public List<Object[]> statsPostByUser(String name, int year, String period) {
        return this.statsRepo.statsPostByUser(name, year, period);
    }

    @Override
    public List<Map<String, Object>> statsSurveyResponses(Integer surveyID) {
        return  this.statsRepo.statsSurveyResponses(surveyID);
    }

    
}
