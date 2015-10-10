package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Player {
    @Id @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String username;
    private double balance;
    private String firstname;
    private String lastname; 
    private String iban; 
    private int characterslots; 
    private String lastpayment; 
    private int monthspayed; 
    private String password; 
    private String banned; 
    
    @OneToMany(fetch = FetchType.LAZY, mappedBy="player", cascade = CascadeType.ALL) 
    private Set<Character> characters = new HashSet<Character>();
    
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	@JoinTable(name = "player_server", joinColumns = { 
			@JoinColumn(name = "player_id", nullable = false, updatable = false) }, 
			inverseJoinColumns = { @JoinColumn(name = "server_id", nullable = false, updatable = false) })
    private Set<Server> servers = new HashSet<Server>();

    
    public Player() {}

    public Player(String username, double balance, String firstname, String lastname, 
    String iban, int characterslots, String lastpayment, int monthspayed, 
    String password, String banned, Set<Character> characters, Set<Server> servers) {
        this();
        this.username = username;
        this.balance = balance;
        this.firstname = firstname;
        this.lastname = lastname;
        this.iban = iban;
        this.characterslots = characterslots;
        this.lastpayment = lastpayment;
        this.monthspayed = monthspayed;
        this.password = password;
        this.banned = banned;
        this.characters = characters;
        this.servers = servers;
    }

	@Column(name = "player_id", unique = true, nullable = false)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    
    public Set<Character> getCharacters()  
    {  
        return characters;  
    }  
    public void setCharacters(Set<Character> characters)  
    {  
        this.characters = characters;  
    }  
    
    public Set<Server> getServers()  
    {  
        return servers;  
    }  
    public void setServers(Set<Server> servers)  
    {  
        this.servers = servers;  
    }  
    
    public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getIban() {
		return iban;
	}

	public void setIban(String iban) {
		this.iban = iban;
	}

	public int getCharacterslots() {
		return characterslots;
	}

	public void setCharacterslots(int characterslots) {
		this.characterslots = characterslots;
	}

	public String getLastpayment() {
		return lastpayment;
	}

	public void setLastpayment(String lastpayment) {
		this.lastpayment = lastpayment;
	}

	public int getMonthspayed() {
		return monthspayed;
	}

	public void setMonthspayed(int monthspayed) {
		this.monthspayed = monthspayed;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getBanned() {
		return banned;
	}

	public void setBanned(String banned) {
		this.banned = banned;
	}

	@Override
	public String toString() {
		return "Player [id=" + id + ", username=" + username + ", balance="
				+ balance + ", firstname=" + firstname + ", lastname="
				+ lastname + ", iban=" + iban + ", characterslots="
				+ characterslots + ", lastpayment=" + lastpayment
				+ ", monthspayed=" + monthspayed + ", password=" + password
				+ ", banned=" + banned + "]";
	}

	

      
}

