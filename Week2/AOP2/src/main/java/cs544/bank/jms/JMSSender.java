package cs544.bank.jms;


public class JMSSender implements IJMSSender{
	
	public void sendJMSMessage (String text, int idx){
		System.out.println("JMSSender: sending JMS message ="+text);
	}

}
