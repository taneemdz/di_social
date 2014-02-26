package com.rj.di_social.resource;

import com.rj.di_social.DAO.MessageDAO;
import com.rj.di_social.model.SentimentStats;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * User: rjain
 * Date: 24/02/14
 * Time: 2:45 PM
 */

@Service
public class StatsResourceImpl implements StatsResource {
    @Autowired
    MessageDAO messageDAO;
    @Override
    public List<SentimentStats> getSentimentStats() {
        Map<String, Long> messageCountMap= messageDAO.getMessageCount();
        Map<String, Long> positiveMessageCountMap= messageDAO.getPositiveMessageCount();
        Map<String, Long> negativeMessageCountMap= messageDAO.getNegativeMessageCount();

        List<SentimentStats> sentimentStatsList= new ArrayList<SentimentStats>();

        Iterator it = messageCountMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pairs = (Map.Entry)it.next();

            SentimentStats sentimentStats= new SentimentStats();
            String fiId= (String)pairs.getKey();

            sentimentStats.setFiId(fiId);
            sentimentStats.setMessageCount((Long)pairs.getValue());
            sentimentStats.setPositiveSentimentCount((Long)positiveMessageCountMap.get(fiId));
            sentimentStats.setNegativeSentimentCount((Long)negativeMessageCountMap.get(fiId));

            sentimentStatsList.add(sentimentStats);
            it.remove();
        }

        return sentimentStatsList;
    }
}
