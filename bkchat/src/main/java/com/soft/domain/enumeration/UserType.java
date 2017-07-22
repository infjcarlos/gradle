package com.soft.domain.enumeration;

/**
 * Created by jcori on 7/15/2017.
 */
public enum UserType {
    STATUS_REMOVED("REMOVED"),
    STATUS_ACTIVE("ACTIVE");
    private String key;

    UserType(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }
}
