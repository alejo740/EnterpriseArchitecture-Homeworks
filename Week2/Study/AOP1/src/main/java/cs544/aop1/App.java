package cs544.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("springconfig.xml");
		System.out.println("Started");
		ClassB b = context.getBean("b", ClassB.class);
		b.printItems();

	}
}
