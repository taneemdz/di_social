package com.rj.di_social.resource;

import com.rj.di_social.model.Message;

import java.util.List;

/**
 * User: rjain
 * Date: 03/02/14
 * Time: 10:26 PM
 */
public interface MessageResource {
    public List<Message> getMessage(String fiId);
    public void fetchAndPopulateMessages(String fiId);
    public void fetchAndPopulateMessagesFromFb(String fiId);
    public void fetchAndUpdateMessageSentiment(String fiId);
}
