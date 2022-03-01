package io.iamcyw.ams.job.strategy.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "ALARM_SOURCE")
public class AlarmSourcePO extends PanacheEntity {

    @Column(name = "name")
    public String name;

    public static AlarmSourcePO findByName(String name) {
        return find("name", name).singleResult();
    }

}
