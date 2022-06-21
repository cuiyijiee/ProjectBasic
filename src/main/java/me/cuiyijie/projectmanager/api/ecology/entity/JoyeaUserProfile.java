package me.cuiyijie.projectmanager.api.ecology.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Data;

@Data
public class JoyeaUserProfile {

    @JsonAlias("subcompanyid")
    private int subCompanyId;

    @JsonAlias("workcode")
    private String workCode;

    private String sex;

    @JsonAlias("departmentid")
    private int departmentId;

    private String mobile;


    @JsonAlias("systemlanguage")
    private String systemLanguage;

    private String telephone;

    @JsonAlias("managerid")
    private String managerId;

    @JsonAlias("lastname")
    private String lastName;

    private int id;

    @JsonAlias("countryid")
    private int countryId;

    private String email;

    private String status;

}
