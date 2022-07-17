package com.emrebozzkurtt.petclinic.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "vet")
public class Vet extends BaseEntity{

	@NotEmpty
	@Column(name = "vetname")
	private String vetName;
	
	@NotEmpty
	@Column(name = "vetsurname")
	private String vetSurname;


	public String getVetName() {
		return vetName;
	}

	public void setVetName(String vetName) {
		this.vetName = vetName;
	}

	public String getVetSurname() {
		return vetSurname;
	}

	public void setVetSurname(String vetSurname) {
		this.vetSurname = vetSurname;
	}
	
	
}
