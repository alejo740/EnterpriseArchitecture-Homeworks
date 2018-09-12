package cs544.aop1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.util.StopWatch;

import java.text.SimpleDateFormat;
import java.util.Date;

@Aspect
public class TraceCustomerService {

    @After("execution(* cs544.aop1.EmailSender.sendEmail(String, String))")
    public void traceAfterMethod(JoinPoint joinpoint) {
        SimpleDateFormat formatter = new SimpleDateFormat("E MMM dd HH:mm:ss z yyyy");
        String ss = formatter.format(new Date());
        System.out.println(ss + " method= " + joinpoint.getSignature().getName());
        System.out.println("Address= " + joinpoint.getArgs()[0]);
        System.out.println("Message= " + joinpoint.getArgs()[1]);
        System.out.println("outgoing mail server= " + ((IEmailSender)joinpoint.getTarget()).getOutgoingMailServer());
    }

    @Around("execution(* cs544.aop1.CustomerDAO.*(..))")
    public Object invoke(ProceedingJoinPoint call) throws Throwable {
        StopWatch sw = new StopWatch();
        sw.start(call.getSignature().getName());
        Object retVal = call.proceed();
        sw.stop();
        long totaltime = sw.getLastTaskTimeMillis();
        System.out.println("Time to execute save = " + totaltime + " ms");
        return retVal;
    }
}
