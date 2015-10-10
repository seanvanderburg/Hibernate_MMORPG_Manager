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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Server {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String adress;
    private String name;
    private String location;
    private int maxusers;
    private int connectedusers;
    

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "servers")
    private Set<Player> players = new HashSet<Player>();
    
    public Server() {}

    public Server(Long id, String adress, String name, String location,
			int maxusers, int connectedusers, Set<Player> players) {
		this();
		this.id = id;
		this.adress = adress;
		this.name = name;
		this.location = location;
		this.maxusers = maxusers;
		this.connectedusers = connectedusers;
		this.players = players;
	}
    
	@Column(name = "server_id", unique = true, nullable = false)
    public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public int getMaxusers() {
		return maxusers;
	}

	public void setMaxusers(int maxusers) {
		this.maxusers = maxusers;
	}

	public int getConnectedusers() {
		return connectedusers;
	}

	public void setConnectedusers(int connectedusers) {
		this.connectedusers = connectedusers;
	}
	
	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", adress=" + adress + ", name=" + name
				+ ", location=" + location + ", maxusers=" + maxusers
				+ ", connectedusers=" + connectedusers + ", players=" + players
				+ "]";
	}

	
}
