package io.iamcyw.ams.domain.job.strategy.entity;


import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "ALARM_SOURCE")
public class AlarmSource extends BasicEntity {

    @Column(name = "name")
    public String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
