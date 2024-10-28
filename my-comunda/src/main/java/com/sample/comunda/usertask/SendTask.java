package com.sample.comunda.usertask;/*
 * @author -Suraj Tiwari
 */

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("SendTask")
public class SendTask implements JavaDelegate {
    private final Logger logger = LoggerFactory.getLogger(SendTask.class);

    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Inside Send Task");
    }
}