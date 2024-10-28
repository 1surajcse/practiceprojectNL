package com.sample.comunda.usertask;/*
 * @author -Suraj Tiwari
 */



import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("WelcomeTask")
public class WelcomeTask implements JavaDelegate {
    private final Logger logger= LoggerFactory.getLogger(WelcomeTask.class);
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        logger.info("Welcome to the Comunda Learning");
    }
}
