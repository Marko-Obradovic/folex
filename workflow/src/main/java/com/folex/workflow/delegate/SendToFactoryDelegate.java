package com.folex.workflow.delegate;

import com.folex.workflow.feign.FactoryApi;
import com.folex.workflow.feign.NewFactoryOrder;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("sendToFactory")
public class SendToFactoryDelegate implements JavaDelegate {

    private final FactoryApi factoryApi;

    public SendToFactoryDelegate(FactoryApi factoryApi) {
        this.factoryApi = factoryApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        UUID orderId = (UUID) delegateExecution.getVariable(ProcessVariables.ID);
        NewFactoryOrder order = new NewFactoryOrder(orderId);

        factoryApi.addOrder(order);
    }
}
