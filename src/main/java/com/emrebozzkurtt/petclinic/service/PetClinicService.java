package com.emrebozzkurtt.petclinic.service;

import java.util.List;

import com.emrebozzkurtt.petclinic.exception.OwnerNotFoundException;
import com.emrebozzkurtt.petclinic.model.Owner;

public interface PetClinicService {

	List<Owner> findOwners();
	List<Owner> findOwners(String lastName);
	Owner findOwner(int id) throws OwnerNotFoundException ;
	void createOwner(Owner owner);
	Owner updateOwner(Owner owner);
	void deleteOwner(int id);
	
}
