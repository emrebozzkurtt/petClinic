package com.emrebozzkurtt.petclinic.dao;

import java.util.List;

import com.emrebozzkurtt.petclinic.model.Owner;

public interface OwnerRepository {

	List<Owner> findAll();
	Owner findById(int id);
	List<Owner> findByLastName(String lastName);
	
	void createOwner(Owner owner);
	Owner updateOwner(Owner owner);
	void deleteOwner(int id);
}
