package com.rj.di_social.model;

/**
 * User: rjain
 * Date: 24/02/14
 * Time: 2:41 PM
 */
public class SentimentStats {
    private String fiId;
    private Long messageCount;
    private Long positiveSentimentCount;
    private Long negativeSentimentCount;

    public String getFiId() {
        return fiId;
    }

    public void setFiId(String fiId) {
        this.fiId = fiId;
    }

    public Long getMessageCount() {
        return messageCount;
    }

    public void setMessageCount(Long messageCount) {
        this.messageCount = messageCount;
    }

    public Long getPositiveSentimentCount() {
        return positiveSentimentCount;
    }

    public void setPositiveSentimentCount(Long positiveSentimentCount) {
        this.positiveSentimentCount = positiveSentimentCount;
    }

    public Long getNegativeSentimentCount() {
        return negativeSentimentCount;
    }

    public void setNegativeSentimentCount(Long negativeSentimentCount) {
        this.negativeSentimentCount = negativeSentimentCount;
    }
}
