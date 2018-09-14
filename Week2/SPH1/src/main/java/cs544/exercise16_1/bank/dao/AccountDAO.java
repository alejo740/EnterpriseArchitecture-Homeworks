package cs544.exercise16_1.bank.dao;

import java.util.*;

import cs544.exercise16_1.bank.domain.Account;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Transactional(propagation= Propagation.MANDATORY)
public class AccountDAO implements IAccountDAO {

    @Autowired
    private SessionFactory sf;

    public void saveAccount(Account account) {
        // System.out.println("AccountDAO: saving account with accountnr ="+account.getAccountnumber());
        sf.getCurrentSession().save(account);
    }

    public void updateAccount(Account account) {
        // System.out.println("AccountDAO: update account with accountnr ="+account.getAccountnumber());
        sf.getCurrentSession().saveOrUpdate(account);
    }

    public Account loadAccount(long accountnumber) {
        // System.out.println("AccountDAO: loading account with accountnr ="+accountnumber);
        return (Account) this.sf.getCurrentSession().get(Account.class, accountnumber);
    }

    public Collection<Account> getAccounts() {
        Query query = this.sf.getCurrentSession().createQuery("select A from Account A");
        return query.getResultList();
    }
}
