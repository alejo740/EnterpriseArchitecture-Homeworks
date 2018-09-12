package cs544.bank.service.aop;

import cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class DAOLogAdvice {

    private ILogger logger;

    @After("within(cs544.bank.dao.*)")
    public void logDAOCall(JoinPoint joinpoint) {
        logger.log("---logDAOCall: " + joinpoint.getSignature().toString());
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }
}
