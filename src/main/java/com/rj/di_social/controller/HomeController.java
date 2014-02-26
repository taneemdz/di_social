package com.rj.di_social.controller;

import com.rj.di_social.model.FiInfo;
import com.rj.di_social.resource.FiInfoResource;
import com.rj.di_social.resource.SentimentAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * User: rjain
 * Date: 07/02/14
 * Time: 9:45 AM
 */

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    SentimentAnalysis sentimentAnalysis;

    @RequestMapping(method = RequestMethod.GET)
    public String getPreferenceUI(ModelMap model){
        return "index";
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public void test(){
        System.out.println(sentimentAnalysis.analyseSentiment("Thank you, CNB, for feeding a hungry crowd today."));
        return;
    }
}
