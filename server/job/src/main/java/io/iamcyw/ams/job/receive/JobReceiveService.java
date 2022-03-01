package io.iamcyw.ams.job.receive;

import io.iamcyw.ams.domain.job.predicate.QueryAllowStrategy;
import io.iamcyw.ams.domain.job.receive.ReceiveAlarm;
import io.iamcyw.ams.domain.notification.cache.AddAlarm;
import io.iamcyw.ams.job.strategy.persistence.AlarmSourcePO;
import io.iamcyw.ams.job.strategy.persistence.AlarmStrategyPO;
import io.iamcyw.tower.commandhandling.CommandHandle;
import io.iamcyw.tower.commandhandling.gateway.CommandGateway;
import io.iamcyw.tower.queryhandling.gateway.QueryGateway;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class JobReceiveService {

    protected final QueryGateway queryGateway;

    protected final CommandGateway commandGateway;

    public JobReceiveService(QueryGateway queryGateway, CommandGateway commandGateway) {
        this.queryGateway = queryGateway;
        this.commandGateway = commandGateway;
    }

    @CommandHandle
    public void handle(ReceiveAlarm receiveAlarm) {
        AlarmSourcePO alarmSourcePO = AlarmSourcePO.findByName(receiveAlarm.source);
        List<Long> strategies = queryGateway.queries(
                new QueryAllowStrategy(alarmSourcePO.id, receiveAlarm.getMessage()));
        strategies.stream()
                  .map(id -> AlarmStrategyPO.<AlarmStrategyPO>findById(id))
                  .filter(alarmStrategyPO -> {
                      // todo 检查是否需要在单位时间内忽略重复警报
                      if (alarmStrategyPO.repeatTimeInterval != 0L) {

                      }
                      return true;
                  })
                  .map(alarmStrategyPO -> new AddAlarm(alarmStrategyPO.id, alarmSourcePO.id, receiveAlarm.getMessage()))
                  .forEach(commandGateway::send);

    }

}
