package cs544.bank.service;

import java.util.Collection;

import cs544.bank.dao.AccountDAO;
import cs544.bank.dao.IAccountDAO;
import cs544.bank.domain.Account;
import cs544.bank.domain.Customer;
import cs544.bank.jms.IJMSSender;
import cs544.bank.jms.JMSSender;
import cs544.bank.logging.ILogger;
import cs544.bank.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;


public class AccountService implements IAccountService {
	private IAccountDAO accountDAO;

	private ICurrencyConverter currencyConverter;

	private IJMSSender jmsSender;

	
	public AccountService(){
	    /*
	    IAccountDAO accountDAO, ICurrencyConverter currencyConverter, IJMSSender jmsSender, ILogger logger
		this.accountDAO = accountDAO;
		this.currencyConverter = currencyConverter;
		this.jmsSender = jmsSender;
		this.logger = logger;
		*/
		/*
		accountDAO=new AccountDAO();
		currencyConverter= new CurrencyConverter();
		jmsSender =  new JMSSender();
		logger = new Logger();
		*/
	}

	public Account createAccount(long accountNumber, String customerName) {
		Account account = new Account(accountNumber);
		Customer customer = new Customer(customerName);
		account.setCustomer(customer);
		accountDAO.saveAccount(account);
		return account;
	}

	public void deposit(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.deposit(amount);
		accountDAO.updateAccount(account);
		if (amount > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber, 9);
		}
	}

	public Account getAccount(long accountNumber) {
		Account account = accountDAO.loadAccount(accountNumber);
		return account;
	}

	public Collection<Account> getAllAccounts() {
		return accountDAO.getAccounts();
	}

	public void withdraw(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		account.withdraw(amount);
		accountDAO.updateAccount(account);
	}

	public void depositEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.deposit(amountDollars);
		accountDAO.updateAccount(account);
		if (amountDollars > 10000){
			jmsSender.sendJMSMessage("Deposit of $ "+amount+" to account with accountNumber= "+accountNumber, 8);
		}
	}

	public void withdrawEuros(long accountNumber, double amount) {
		Account account = accountDAO.loadAccount(accountNumber);
		double amountDollars = currencyConverter.euroToDollars(amount);
		account.withdraw(amountDollars);
		accountDAO.updateAccount(account);
	}

	public void transferFunds(long fromAccountNumber, long toAccountNumber, double amount, String description) {
		Account fromAccount = accountDAO.loadAccount(fromAccountNumber);
		Account toAccount = accountDAO.loadAccount(toAccountNumber);
		fromAccount.transferFunds(toAccount, amount, description);
		accountDAO.updateAccount(fromAccount);
		accountDAO.updateAccount(toAccount);
		if (amount > 10000){
			jmsSender.sendJMSMessage("TransferFunds of $ "+amount+" from account with accountNumber= "+fromAccount+" to account with accountNumber= "+toAccount, 7);
		}
	}

    public void setAccountDAO(IAccountDAO accountDAO) {
        this.accountDAO = accountDAO;
    }

    public void setCurrencyConverter(ICurrencyConverter currencyConverter) {
        this.currencyConverter = currencyConverter;
    }

    public void setJmsSender(IJMSSender jmsSender) {
        this.jmsSender = jmsSender;
    }
}
