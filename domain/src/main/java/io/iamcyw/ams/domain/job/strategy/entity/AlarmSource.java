package io.iamcyw.ams.domain.job.strategy.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Entity;

@Entity(name = "ALARM_SOURCE")
public class AlarmSource extends BasicEntity {

    private String name;

    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

}
