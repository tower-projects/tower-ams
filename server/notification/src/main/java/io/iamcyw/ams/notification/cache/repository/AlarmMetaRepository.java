package io.iamcyw.ams.notification.cache.repository;

import io.iamcyw.ams.domain.notification.cache.entity.AlarmMeta;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlarmMetaRepository implements PanacheRepository<AlarmMeta> {
}
