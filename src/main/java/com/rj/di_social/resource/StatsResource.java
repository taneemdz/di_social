package com.rj.di_social.resource;

import com.rj.di_social.model.SentimentStats;

import java.util.List;

/**
 * User: rjain
 * Date: 24/02/14
 * Time: 2:44 PM
 */
public interface StatsResource {
    public List<SentimentStats> getSentimentStats();
}
