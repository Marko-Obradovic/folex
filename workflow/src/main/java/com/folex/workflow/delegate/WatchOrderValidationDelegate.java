package com.folex.workflow.delegate;

import org.camunda.bpm.engine.delegate.BpmnError;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Service;

@Service("watchOrderValidation")
public class WatchOrderValidationDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {

        if(hasValue(delegateExecution, ProcessVariables.SOURCE) && hasValue(delegateExecution, ProcessVariables.DESCRIPTION)) {
            // all is well

            delegateExecution.setVariable(ProcessVariables.STATUS, "RECEIVED");
        } else {
            throw new BpmnError("invalid_property");
        }
    }

    private boolean hasValue(DelegateExecution execution, String key) {
        String value = (String) execution.getVariable(key);

        return value != null && value.length() != 0;
    }


}
