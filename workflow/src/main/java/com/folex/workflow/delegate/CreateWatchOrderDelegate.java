package com.folex.workflow.delegate;

import com.folex.workflow.feign.CreateOrderRequest;
import com.folex.workflow.feign.CreateOrderResponse;
import com.folex.workflow.feign.OrderApi;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("createWatchOrder")
public class CreateWatchOrderDelegate implements JavaDelegate {

    private final OrderApi orderApi;

    public CreateWatchOrderDelegate(OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        final String source = (String) delegateExecution.getVariable(ProcessVariables.SOURCE);
        final String description = (String) delegateExecution.getVariable(ProcessVariables.DESCRIPTION);

        final CreateOrderRequest request = new CreateOrderRequest("RECEIVED", source, description);
        final CreateOrderResponse response = orderApi.createOrder(request);

        delegateExecution.setVariable(ProcessVariables.ID, response.getId().toString());
        delegateExecution.setVariable(ProcessVariables.STATUS, response.getStatus());

    }
}
