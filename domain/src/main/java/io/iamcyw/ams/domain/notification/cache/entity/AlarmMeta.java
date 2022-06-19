package io.iamcyw.ams.domain.notification.cache.entity;

import io.iamcyw.ams.domain.BasicEntity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity(name = "ALARM_META")
public class AlarmMeta extends BasicEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    public Alarm alarm;

    String metaKey;

    String metaValue;

    public AlarmMeta(String key, String value) {
        this.metaKey = key;
        this.metaValue = value;
    }

    public AlarmMeta() {

    }

    public static Map<String, String> struct(Set<AlarmMeta> metaPOS) {
        Map<String, String> meta = new HashMap<>(metaPOS.size());
        metaPOS.forEach(alarmMeta -> meta.put(alarmMeta.metaKey, alarmMeta.metaValue));
        return meta;
    }

    public static Set<AlarmMeta> struct(Map<String, String> meta) {
        Set<AlarmMeta> alarmMetaSet = new HashSet<>(meta.size());
        meta.forEach((key, value) -> alarmMetaSet.add(new AlarmMeta(key, value)));
        return alarmMetaSet;
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

        AlarmMeta that = (AlarmMeta) o;

        if (!metaKey.equals(that.metaKey))
            return false;
        return metaValue.equals(that.metaValue);
    }

}
