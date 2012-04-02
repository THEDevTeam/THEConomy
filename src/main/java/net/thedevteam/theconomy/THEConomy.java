package net.thedevteam.theconomy;

import java.util.logging.Level;
import javax.persistence.*;
import org.spout.api.plugin.CommonPlugin;
import org.spout.api.plugin.ServiceManager.ServicePriority;
import org.spout.api.plugin.services.EconomyService;

/**
 * THEConomy main class.
 */
public class THEConomy extends CommonPlugin {
    private THEConomyService service;
    private EntityManagerFactory entityManagerFactory;

    @Override
    public void onEnable() {
    	entityManagerFactory =  Persistence.createEntityManagerFactory("jpa"); //TODO: set persistance.xml
        getGame().getServiceManager().register(EconomyService.class, service, this, ServicePriority.High);
        getLogger().log(Level.INFO, "THEConomy version {0} enabled.", getDescription().getVersion());
    }

    @Override
    public void onDisable() {
    	entityManagerFactory.close();
        getGame().getServiceManager().unregisterAll(this);
        getLogger().log(Level.INFO, "THEConomy version {0} disabled.", getDescription().getVersion());
    }
    
    public Account getAccount(String playername){
    	EntityManager em = entityManagerFactory.createEntityManager();
    	Account account = em.find(Account.class, playername);
    	if (account == null){
    		account = newAccount(playername);
    	}
    	em.close();
    	return account;
    }
    
    public Account newAccount(String playername){
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
    
    public void updateAccount(Account account){
        EntityManager em = entityManagerFactory.createEntityManager();
        EntityTransaction ut = em.getTransaction();
        
        ut.begin();
        em.refresh(account);
        ut.commit();
        em.close();
    }

}
