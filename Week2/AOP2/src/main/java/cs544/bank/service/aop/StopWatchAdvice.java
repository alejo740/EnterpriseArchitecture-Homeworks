package cs544.bank.service.aop;

import cs544.bank.logging.ILogger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

@Aspect
public class StopWatchAdvice {

    private ILogger logger;

    @Around("within(cs544.bank.service.*)")
    public Object methodTimer(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        logger.log("---methodTimer: Time to execute save = " + totaltime + " ms");
        return retVal;
    }

    public void setLogger(ILogger logger) {
        this.logger = logger;
    }
}
