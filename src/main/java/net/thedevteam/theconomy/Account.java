package net.thedevteam.theconomy;

import java.io.Serializable;
import javax.persistence.*;


@Entity(name = "ACCOUNT")
public class Account implements Serializable {
	@Id
private String name;
private double balance;


public double getBalance() {
	return balance;
}
public void setBalance(double balance) {
	this.balance = balance;
}

public String getName() {
	return name;
}
public void setName(String name) {
	this.name = name;
}

}
