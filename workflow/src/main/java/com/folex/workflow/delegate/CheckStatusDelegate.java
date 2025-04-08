package com.folex.workflow.delegate;

import com.folex.workflow.feign.FactoryApi;
import com.folex.workflow.feign.FactoryOrderStatus;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("checkStatus")
public class CheckStatusDelegate implements JavaDelegate {
    
    private final FactoryApi factoryApi;

    public CheckStatusDelegate(FactoryApi factoryApi) {
        this.factoryApi = factoryApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String orderId = (String) delegateExecution.getVariable(ProcessVariables.ID);

        FactoryOrderStatus status = factoryApi.getOrder(orderId);
        
        if("ERROR".equalsIgnoreCase(status.getStatus())) {
            delegateExecution.setVariable(ProcessVariables.STATUS, "ERROR");
        } else if ("SUCCESS".equalsIgnoreCase(status.getStatus())) {
            delegateExecution.setVariable(ProcessVariables.STATUS, "SHIPPED");
        }
    }
}
