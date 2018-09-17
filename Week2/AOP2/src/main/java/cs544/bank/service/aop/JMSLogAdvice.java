package cs544.bank.service.aop;

import cs544.bank.logging.ILogger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class JMSLogAdvice {

    private ILogger logger;

    @After("execution(* cs544.bank.jms.IJMSSender.sendJMSMessage(..)) && args(text, idx)")
    public void logJMSMessage(JoinPoint joinpoint, String text, int idx) throws Throwable {
        logger.log("---logJMSMessage: " + text);
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }
}
