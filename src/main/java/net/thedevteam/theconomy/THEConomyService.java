
package net.thedevteam.theconomy;

import org.spout.api.plugin.services.EconomyService;

/**
 * THEConomy Economy service.
 */
public class THEConomyService extends EconomyService {
	
	private THEConomy plugin;

	public THEConomyService(THEConomy tc){
		this.plugin = tc;
	}

    @Override
    public boolean has(String string, double d) {
        if (get(string) >= d){
        	return true;
        }else{
        	return false;
        }
    }

    @Override
    public double get(String string) {
        Account account = plugin.getAccount(string);
        return account.getBalance();
    }

    @Override
    public boolean withdraw(String string, double d) {
        if(has(string,d)){
        	Account account = plugin.getAccount(string);
        	account.setBalance(account.getBalance() - d);
        	plugin.updateAccount(account);
        	return true;
        }
        return false;
    }

    @Override
    public boolean deposit(String string, double d) {
    	Account account = plugin.getAccount(string);
    	account.setBalance(account.getBalance() + d);
    	plugin.updateAccount(account);
    	return true;
    }
    
}
