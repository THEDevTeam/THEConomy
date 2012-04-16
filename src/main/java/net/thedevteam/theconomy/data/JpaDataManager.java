package net.thedevteam.theconomy.data;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import net.thedevteam.theconomy.Account;
import net.thedevteam.theconomy.THEConomy;

public class JpaDataManager implements DataManager {

	private EntityManagerFactory entityManagerFactory;
	
	public JpaDataManager(THEConomy tem) {
		entityManagerFactory =  Persistence.createEntityManagerFactory("jpa"); //TODO: set persistance.xml
	}
	

	@Override
	public Account getAccount(String playername) {
		EntityManager em = entityManagerFactory.createEntityManager();
    	Account account = em.find(Account.class, playername);
    	if (account == null){
    		account = newAccount(playername);
    	}
    	em.close();
    	return account;
	}

	@Override
	public Account newAccount(String playername) {
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction ut = em.getTransaction();
        
        ut.begin();
    	Account account = new Account();
    	account.setName(playername);
    	em.persist(account);
    	ut.commit();
    	em.close();
    	
    	return account;
	}

	@Override
	public void updateAccount(Account account) {
		 EntityManager em = entityManagerFactory.createEntityManager();
	        EntityTransaction ut = em.getTransaction();
	        
	        ut.begin();
	        em.refresh(account);
	        ut.commit();
	        em.close();
		
	}



	@Override
	public void stop() {
		entityManagerFactory.close();
	}

}
