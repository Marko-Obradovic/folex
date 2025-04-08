package com.folex.workflow.delegate;

import com.folex.workflow.feign.OrderApi;
import com.folex.workflow.feign.UpdateOrderRequest;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service("updateSuccess")
public class UpdateSuccessDelegate implements JavaDelegate {

    private final OrderApi orderApi;

    public UpdateSuccessDelegate(OrderApi orderApi) {
        this.orderApi = orderApi;
    }

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        String id = (String) delegateExecution.getVariable(ProcessVariables.ID);

        UpdateOrderRequest updateOrderRequest = new UpdateOrderRequest("SHIPPED", UUID.fromString(id));

        orderApi.updateOrder(updateOrderRequest.getId(), updateOrderRequest);

    }
}
