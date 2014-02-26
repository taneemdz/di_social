package com.rj.di_social.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * User: rjain
 * Date: 06/02/14
 * Time: 12:23 PM
 */

@Component
public class MessageSyncScheduler {

    @Autowired
    FiInfoResource fiInfoResource;

    @Autowired
    MessageResource messageResource;

    @Scheduled(cron= "0 35 17 * * ?")
    public void syncFbMessage() {
        System.out.println("Message Sync Started: "+ new Date());

        List<String> fiIdList= fiInfoResource.getAllFiId();
        for(String fiId: fiIdList) {
            System.out.println("Sync started for FiId: "+ fiId);
            messageResource.fetchAndPopulateMessagesFromFb(fiId);
            System.out.println("Sync finished for FiId: "+ fiId);
        }

        System.out.println("Message Sync Finished: "+ new Date());
    }

    @Scheduled(cron= "0 52 19 * * ?")
    public void updateSentimentScore() {
        System.out.println("Update sentiment score started: "+ new Date());

        List<String> fiIdList= fiInfoResource.getAllFiId();
        for(String fiId: fiIdList) {
            if(Integer.parseInt(fiId)>3621) {
                System.out.println("Update sentiment started for FiId: "+ fiId);
                messageResource.fetchAndUpdateMessageSentiment(fiId);
                System.out.println("Update sentiment finished for FiId: "+ fiId);
            }
        }

        System.out.println("Update sentiment score finished: "+ new Date());
    }
}
