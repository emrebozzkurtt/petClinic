package com.emrebozzkurtt.petclinic.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonIgnore;

@XmlRootElement
@Entity
@Table(name="owner")
public class Owner extends BaseEntity {
	
	@NotEmpty
	@Column(name = "firstname")
	private String firstName;
	
	@NotEmpty
	@Column(name = "lastname")
	private String lastName;
	
	@OneToMany(mappedBy = "owner")
	private Set<Pet> pets = new HashSet<>();
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}
	
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	@XmlTransient
	@JsonIgnore
	public Set<Pet> getPets() {
		return pets;
	}
	
	public void setPets(Set<Pet> pets) {
		this.pets = pets;
	}

	@Override
	public String toString() {
		return "Owner [id=" + getId() + ", firstName=" + firstName + ", lastName=" + lastName + "]";
	}
	
	
	
}
