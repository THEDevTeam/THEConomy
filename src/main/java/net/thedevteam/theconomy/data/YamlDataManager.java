package net.thedevteam.theconomy.data;

import java.io.File;
import java.io.IOException;

import org.spout.api.exception.ConfigurationException;
import org.spout.api.util.config.yaml.YamlConfiguration;

import net.thedevteam.theconomy.Account;
import net.thedevteam.theconomy.THEConomy;

public class YamlDataManager implements DataManager {
	
	private THEConomy plugin;
	private YamlConfiguration config;

	public YamlDataManager(THEConomy te){
		this.plugin = te;
		File config = new File(plugin.getDataFolder(), "accountdata.yml");
		if (!config.exists()) {
			try {
				config.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		this.config = new YamlConfiguration(config);
		try {
			this.config.load();
		} catch (ConfigurationException e) {
			e.printStackTrace();
		}
		this.config.setPathSeparator(".");
		try {
			this.config.load();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public void stop() {
		try {
			this.config.save();
		} catch (ConfigurationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public Account getAccount(String playername) {
		Account acc = new Account();
		acc.setName(playername);
		acc.setBalance(config.getNode("accounts."+playername+".balance").getDouble());	
		return acc;
	}

	@Override
	public Account newAccount(String playername) {
		config.getNode("accounts."+playername+".balance").setValue(0);
		return getAccount(playername);
	}

	@Override
	public void updateAccount(Account account) {
		config.getNode("accounts."+account.getName()+".balance").setValue(account.getBalance());

	}

}
