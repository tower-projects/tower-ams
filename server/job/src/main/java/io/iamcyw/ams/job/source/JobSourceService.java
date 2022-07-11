package io.iamcyw.ams.job.source;

import io.iamcyw.ams.domain.job.source.usecase.AddAlarmSource;
import io.iamcyw.ams.domain.job.source.usecase.QueryAllSource;
import io.iamcyw.ams.domain.job.strategy.entity.AlarmSource;
import io.iamcyw.ams.domain.job.strategy.usecase.DeleteAlarmSource;
import io.iamcyw.ams.job.AlarmJobServerMessages;
import io.iamcyw.ams.job.strategy.repository.AlarmSourceRepository;
import io.iamcyw.tower.messaging.CommandHandle;
import io.iamcyw.tower.messaging.QueryHandle;
import io.iamcyw.tower.messaging.UseCase;

import javax.transaction.Transactional;
import java.util.List;

@UseCase
@Transactional
public class JobSourceService {

    private final AlarmSourceRepository alarmSourceRepository;

    public JobSourceService(AlarmSourceRepository alarmSourceRepository) {
        this.alarmSourceRepository = alarmSourceRepository;
    }

    @QueryHandle
    public List<AlarmSource> query(QueryAllSource queryAllSource) {
        return alarmSourceRepository.listAll();
    }

    @CommandHandle
    public void command(AddAlarmSource command) {
        if (alarmSourceRepository.findByName(command.name()).isPresent()) {
            throw AlarmJobServerMessages.msg.sourceNameExist(command.name());
        }
        AlarmSource alarmSource = new AlarmSource();
        alarmSource.setName(command.name());
        alarmSource.setComments(command.comment());
        alarmSourceRepository.persist(alarmSource);
    }

    @CommandHandle
    public void command(DeleteAlarmSource command) {
        //todo check source used

        alarmSourceRepository.deleteById(command.id());
    }

}
