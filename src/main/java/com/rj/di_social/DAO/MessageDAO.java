package com.rj.di_social.DAO;

import com.rj.di_social.model.Message;

import java.util.List;
import java.util.Map;

/**
 * User: rjain
 * Date: 04/02/14
 * Time: 2:49 PM
 */
public interface MessageDAO {
    public List<Message> getMessages(String fiId);
    public long postMessage(Message message);
    public void updateSentiment(Message message);
    public Map<String,Long> getMessageCount();
    public Map<String,Long> getPositiveMessageCount();
    public Map<String,Long> getNegativeMessageCount();
}
