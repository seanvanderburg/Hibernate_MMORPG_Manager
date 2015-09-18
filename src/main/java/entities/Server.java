package entities;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Server {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String adress;
    private String name;
    private String location;
    private String maxusers;
    private String connectedusers;
    

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name= "player_id")
    private Player player;
    
    public Server() {}

    public Server(Long id, String adress, String name, String location,
			String maxusers, String connectedusers, Player player) {
		this();
		this.id = id;
		this.adress = adress;
		this.name = name;
		this.location = location;
		this.maxusers = maxusers;
		this.connectedusers = connectedusers;
		this.player = player;
	}

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

	public String getMaxusers() {
		return maxusers;
	}

	public void setMaxusers(String maxusers) {
		this.maxusers = maxusers;
	}

	public String getConnectedusers() {
		return connectedusers;
	}

	public void setConnectedusers(String connectedusers) {
		this.connectedusers = connectedusers;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Server [id=" + id + ", adress=" + adress + ", name=" + name
				+ ", location=" + location + ", maxusers=" + maxusers
				+ ", connectedusers=" + connectedusers + ", player=" + player
				+ "]";
	}

	
}
