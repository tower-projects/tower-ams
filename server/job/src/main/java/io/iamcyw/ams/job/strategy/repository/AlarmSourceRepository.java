package io.iamcyw.ams.job.strategy.repository;

import io.iamcyw.ams.domain.job.strategy.entity.AlarmSource;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.Optional;

@ApplicationScoped
public class AlarmSourceRepository implements PanacheRepository<AlarmSource> {

    public Optional<AlarmSource> findByName(String name) {
        return find("name", name).singleResultOptional();
    }

}
