package com.javaschool.schedule.handler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.javaschool.schedule.config.JMSConfig;
import com.javaschool.schedule.model.TherapyCase;
import com.javaschool.schedule.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.PostConstruct;
import java.util.List;

@Component
@Slf4j
public class JMSListener {

    public static List<TherapyCase> list;

    private final JMSConfig receiverConfigBean;
    private final ScheduleService service;
    private final SimpMessagingTemplate messageTemplate;

    @Autowired
    public JMSListener(JMSConfig receiverConfigBean, ScheduleService service, SimpMessagingTemplate messageTemplate){
        this.receiverConfigBean = receiverConfigBean;
        this.service = service;
        this.messageTemplate = messageTemplate;
    }

    @JmsListener(destination = "${activemq.destination}", containerFactory = "jmsFactory")
    public void updateSchedule(String response) throws JsonProcessingException {
        list = service.getSchedule(response);
        log.info("Schedule update > " + list);
        messageTemplate.convertAndSend("/message","Update");
    }

    @PostConstruct
    public void initSchedule() throws JsonProcessingException {
        receiverConfigBean.connectionFactory();
        list = service.getSchedule();
        log.info("Schedule init  > " + list);
    }
}
