package entities;


import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;
    private String name;
    private String charclass;
    private String race;
    private int level;
   
    @ManyToMany(mappedBy="characters")
    private Set<Player> players = new HashSet<Player>();

  
    public Character() {}

    public Character(String name, String charclass, String race, int level) {
        this();
        this.name = name;
        this.charclass = charclass;
        this.race = race;
        this.level = level;
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
    
   
    public Set<Player> getPlayers()  
    {  
        return players;  
    }  
    public void setCharacters(Set<Player> players)  
    {  
        this.players = players;  
    }  
    

	@Override
	public String toString() {
		return "Character [id=" + id + ", name=" + name + ", charclass="
				+ charclass + ", race=" + race + ", level=" + level
				+ ", players=" + players + "]";
	}
  
 

}
