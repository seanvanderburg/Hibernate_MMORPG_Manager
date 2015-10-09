package entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String charclass;
    private String race;
    private int level;
    
   
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private Player player;
  
    public Character() {}

    public Character(String name, String charclass, String race, int level) {
        this();
        this.name = name;
        this.charclass = charclass;
        this.race = race;
        this.level = level;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
    
	public String getCharClass() {
        return charclass;
    }

    public void setCharClass(String charclass) {
        this.charclass = charclass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
   
    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", charclass="
				+ charclass + ", race=" + race + ", level=" + level + "]";
	}
    
	
	
  
 

}
