package com.hotdog.springboot.model.customProp;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * Created by hotdog on 2017/3/30.
 */
@ConfigurationProperties(prefix = "audience", locations = "classpath:hotdog.properties")
public class Audience {

    private String clientId;

    private String base64Secret;

    private String name;

    private int expiresSecond;

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getBase64Secret() {
        return base64Secret;
    }

    public void setBase64Secret(String base64Secret) {
        this.base64Secret = base64Secret;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExpiresSecond() {
        return expiresSecond;
    }

    public void setExpiresSecond(int expiresSecond) {
        this.expiresSecond = expiresSecond;
    }
}
