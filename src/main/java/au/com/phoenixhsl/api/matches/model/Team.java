package au.com.phoenixhsl.api.matches.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Team implements Serializable {

	private static final long serialVersionUID = 2878771446906611402L;

	@Id
	@SequenceGenerator(name = "team_seq", sequenceName = "team_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "team_seq")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;

	public Team() {
	}
	
	public Team(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}
}