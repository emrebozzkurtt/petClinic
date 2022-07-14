package com.emrebozzkurtt.petclinic.dao;

import java.util.List;

import com.emrebozzkurtt.petclinic.model.Pet;

public interface PetRepository {
	
	List<Pet> findAll();
	Pet findById(int id);
	List<Pet> findByOwnerId(int id);
	
	void createPet(Pet pet);
	Pet updatePet(Pet pet);
	void deletePet(Pet pet);
	void deleteByOwnerId(int id);			
}
