package net.thedevteam.theconomy.data;

import net.thedevteam.theconomy.Account;

public interface DataManager {
	
	public void stop();
	
    public Account getAccount(String playername);
    public Account newAccount(String playername);
    
    public void updateAccount(Account account);

}
