package com.rj.di_social.controller;

import com.rj.di_social.model.Message;
import com.rj.di_social.model.SentimentStats;
import com.rj.di_social.resource.MessageResource;
import com.rj.di_social.resource.StatsResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * User: rjain
 * Date: 24/02/14
 * Time: 2:43 PM
 */

@Controller
@RequestMapping("/stats")
public class StatsController {

    @Autowired
    StatsResource statsResource;

    @RequestMapping(value = "/sentiments", method = RequestMethod.GET)
    public @ResponseBody
    List<SentimentStats> getSentimentStats() {
        return statsResource.getSentimentStats();
    }
}
