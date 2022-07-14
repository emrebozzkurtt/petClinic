package com.emrebozzkurtt.petclinic;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties
public class PetClinicProperties {

	private boolean displayOwnerWithPets = false;

	public boolean isDisplayOwnerWithPets() {
		return displayOwnerWithPets;
	}

	public void setDisplayOwnerWithPets(boolean displayOwnerWithPets) {
		this.displayOwnerWithPets = displayOwnerWithPets;
	}
	
	
}
