package com.rj.di_social.model;

/**
 * User: rjain
 * Date: 03/02/14
 * Time: 11:32 PM
 */
public class Message {
    private long id;
    private String fiId;
    private String text;
    private String fromId;
    private long referId;
    private long createdTime;
    private SocialMediaSource socialMediaSource;
    private float sentiment;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFiId() {
        return fiId;
    }

    public void setFiId(String fiId) {
        this.fiId = fiId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getFromId() {
        return fromId;
    }

    public void setFromId(String fromId) {
        this.fromId = fromId;
    }

    public long getReferId() {
        return referId;
    }

    public void setReferId(long referId) {
        this.referId = referId;
    }

    public long getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(long createdTime) {
        this.createdTime = createdTime;
    }

    public SocialMediaSource getSocialMediaSource() {
        return socialMediaSource;
    }

    public void setSocialMediaSource(SocialMediaSource socialMediaSource) {
        this.socialMediaSource = socialMediaSource;
    }

    public float getSentiment() {
        return sentiment;
    }

    public void setSentiment(float sentiment) {
        this.sentiment = sentiment;
    }
}
