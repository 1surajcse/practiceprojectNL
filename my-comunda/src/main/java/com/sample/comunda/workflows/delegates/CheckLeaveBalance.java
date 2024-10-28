package com.sample.comunda.workflows.delegates;/*
 * @author -Suraj Tiwari
 */

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service("LeaveBalanceCheck")
public class CheckLeaveBalance  implements JavaDelegate {
    public static  int leaveBalance=3;
    private final Logger logger= LoggerFactory.getLogger(CheckLeaveBalance.class);
    @Override
    public void execute(DelegateExecution delegateExecution) throws Exception {
        if(leaveBalance>0){
logger.info("Employee have leave balance: "+leaveBalance);
leaveBalance--;

        }
        else {
            logger.info("Employee dont have leave balance: " + leaveBalance);
        }
    }
}
