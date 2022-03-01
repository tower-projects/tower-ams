package io.iamcyw.ams.job.predicate

import io.iamcyw.ams.domain.job.express
import io.iamcyw.ams.domain.job.fixed
import io.iamcyw.ams.domain.job.predicate.PredicatePayLoad
import io.iamcyw.ams.domain.job.predicate.QueryAllowStrategy
import io.iamcyw.ams.domain.job.strategy.PolicyContrastType.*
import io.iamcyw.ams.domain.job.strategy.StrategyPolicyPO
import io.iamcyw.tower.commandhandling.gateway.CommandGateway
import io.iamcyw.tower.queryhandling.QueryHandle
import io.iamcyw.tower.queryhandling.gateway.QueryGateway
import javax.enterprise.context.ApplicationScoped
import javax.inject.Inject

@ApplicationScoped
class JobPredicateService @Inject constructor(
    private val commandGateway: CommandGateway,
    private val queryGateway: QueryGateway
) {

    @QueryHandle
    fun query(queryAllowStrategy: QueryAllowStrategy): List<Long> {
        val policies = StrategyPolicyPO.queryBySource(queryAllowStrategy.source)
        return policies.filter {
            val predicatePayLoad = PredicatePayLoad(queryAllowStrategy.payload, it.id!!)
            queryGateway.query(predicatePayLoad)
        }.flatMap {
            it.strategy.map { strategy ->
                strategy.id!!
            }
        }.distinct()
    }

    @QueryHandle
    fun query(predicatePayLoad: PredicatePayLoad): Boolean {
        val policyPO = StrategyPolicyPO.findById(predicatePayLoad.policy)
            ?: throw IllegalStateException("the alarm(${predicatePayLoad.policy}) no found")

        return policyPO.metadata.all {

            when (it.contrastType) {
                Pass -> {
                    true
                }
                Fixed -> predicatePayLoad.message.fixed(it.scope, it.contrastKey, it.contrastValue)
                Express -> predicatePayLoad.message.express(
                    it.scope,
                    it.contrastKey,
                    it.contrastValue
                )
                null -> false
            }
        }
    }

}