package au.com.phoenixhsl.api.matches.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class League implements Serializable {

	private static final long serialVersionUID = -818450516685847263L;

	@Id
	@SequenceGenerator(name = "league_seq", sequenceName = "league_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "league_seq")
	private Long id;
	
	@Column(length = 100, nullable = false)
	private String name;

	public League() {
	}
	
	public League(String name) {
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