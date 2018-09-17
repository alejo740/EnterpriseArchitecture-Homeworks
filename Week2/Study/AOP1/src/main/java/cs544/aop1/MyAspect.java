package cs544.aop1;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;

@Aspect
public class MyAspect {
    private int number;
    public MyAspect() {
        System.out.println("Constructor MyAspect");
    }
    public void setNumber(int number) {
        this.number = number;
    }
    @After("execution(* cs544.aop1.*.*Text(..))")
    public void doSomething(JoinPoint jp) {
        System.out.println("Number:" + number);
        number -= 1;
    }
}
