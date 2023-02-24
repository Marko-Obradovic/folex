package com.folex.workflow.listener;

import com.folex.workflow.configuration.RabbitConfiguration;
import com.folex.workflow.delegate.ProcessVariables;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class OrderListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final ProcessEngine processEngine;
    private final RuntimeService runtimeService;

    public OrderListener(ProcessEngine processEngine, RuntimeService runtimeService) {
        this.processEngine = processEngine;
        this.runtimeService = runtimeService;
    }




    @RabbitListener(queues = {RabbitConfiguration.QUEUE_NAME})
    public void receiver(OrderMessage orderMessage) {
        logger.info("order message {}", orderMessage);
        Map<String, Object> vars = new HashMap<>();
        vars.put(ProcessVariables.SOURCE, orderMessage.getSource());
        vars.put(ProcessVariables.DESCRIPTION, orderMessage.getDescription());

        runtimeService.startProcessInstanceByKey("FolexWatchOrder", vars);

//        processEngine.getRuntimeService().signal("newWatchOrder", vars);
//        processEngine
//                .getRuntimeService().createProcessInstanceById("FolexWatchOrder").setVariables(vars).execute();
    }
}
