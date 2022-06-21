package me.cuiyijie.projectmanager.api.ecology.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class JoyeaAccessToken {

    private String msg;
    @JsonAlias("access_token")
    private String accessToken;
    private long expires;
    private int status;

}