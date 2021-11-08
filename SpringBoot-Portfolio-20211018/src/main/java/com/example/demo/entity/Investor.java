package com.example.demo.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Investor {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    
    @Column
    private String username;
    
    @Column
    private String email;
    
    @Column
    private Integer balance;
    
    
    @OneToMany(cascade=CascadeType.PERSIST, mappedBy="investor", fetch = FetchType.EAGER)
    //@JsonIgnoreProperties("investor")
    private Set<Portfolio> portfolios;
    
    @OneToMany(cascade={CascadeType.PERSIST, CascadeType.REMOVE}, mappedBy="investor", fetch = FetchType.EAGER)
    @JsonIgnoreProperties("investor")
    private Set<Watch> watchs;
    
    public Investor() {
    }

    public Investor(String username, String password, String email, Integer balance) {
        this.username = username;
        this.email = email;
        this.balance = balance;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Set<Portfolio> getPortfolios() {
        return portfolios;
    }

    public void setPortfolios(Set<Portfolio> portfolios) {
        this.portfolios = portfolios;
    }

    public Set<Watch> getWatchs() {
        return watchs;
    }

    public void setWatchs(Set<Watch> watchs) {
        this.watchs = watchs;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

	@Override
	public String toString() {
		return "Investor [id=" + id + ", username=" + username + ", email=" + email + ", balance=" + balance + "]";
	}

    
    
}
