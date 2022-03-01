package io.iamcyw.ams.notification.cache.persistence;

import io.quarkus.hibernate.orm.panache.PanacheEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.Map;
import java.util.Set;

@Entity(name = "ALARM_META")
public class AlarmMetaPO extends PanacheEntity {
    public String metaKey;

    public String metaValue;

    @ManyToOne(fetch = FetchType.LAZY)
    public AlarmPO alarm;

    public AlarmMetaPO(String key, String value) {
        this.metaKey = key;
        this.metaValue = value;
    }

    public AlarmMetaPO() {

    }

    public static Map<String, String> struct(Set<AlarmMetaPO> metaPOS) {
        Map<String, String> meta = Map.of();
        metaPOS.forEach(alarmMetaPO -> {
            meta.put(alarmMetaPO.metaKey, alarmMetaPO.metaValue);
        });
        return meta;
    }

    public static Set<AlarmMetaPO> struct(Map<String, String> meta) {
        Set<AlarmMetaPO> alarmMetaPOSet = Set.of();
        meta.forEach((key, value) -> {
            alarmMetaPOSet.add(new AlarmMetaPO(key, value));
        });
        return alarmMetaPOSet;
    }

    @Override
    public int hashCode() {
        int result = metaKey.hashCode();
        result = 31 * result + metaValue.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;

        AlarmMetaPO that = (AlarmMetaPO) o;

        if (!metaKey.equals(that.metaKey))
            return false;
        return metaValue.equals(that.metaValue);
    }

}
