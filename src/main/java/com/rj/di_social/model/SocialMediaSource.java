package com.rj.di_social.model;

/**
 * User: rjain
 * Date: 03/02/14
 * Time: 11:35 PM
 */
public enum SocialMediaSource {
    FACEBOOK("FACEBOOK"), TWITTER("TWITTER");

    private String value;

    private SocialMediaSource(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
