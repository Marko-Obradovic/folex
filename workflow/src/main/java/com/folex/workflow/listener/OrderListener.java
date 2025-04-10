package com.folex.workflow.listener;

import com.folex.workflow.delegate.ProcessVariables;
import org.camunda.bpm.engine.RuntimeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@RabbitListener(queues = "watches")
public class OrderListener {

    private static final Logger logger = LoggerFactory.getLogger(OrderListener.class);
    private final RuntimeService runtimeService;

    public OrderListener(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    @RabbitHandler
    public void receiver(Map<String, String> input) {
        logger.info("input {}", input);
        OrderMessage orderMessage = new OrderMessage();
        orderMessage.setSource(input.get("source"));
        orderMessage.setDescription(input.get("description"));

        logger.info("order message {}", orderMessage);
        Map<String, Object> vars = new HashMap<>();
        vars.put(ProcessVariables.SOURCE, orderMessage.getSource());
        vars.put(ProcessVariables.DESCRIPTION, orderMessage.getDescription());

        runtimeService.startProcessInstanceByKey("FolexWatchOrder", vars);
    }
}
