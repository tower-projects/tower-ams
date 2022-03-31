package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.AlarmSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class AlarmSourceRepository implements PanacheRepository<AlarmSource> {


    public AlarmSource findByName(String name) {
        return find("name", name).singleResultOptional().orElseThrow(IllegalStateException::new);
    }

}
