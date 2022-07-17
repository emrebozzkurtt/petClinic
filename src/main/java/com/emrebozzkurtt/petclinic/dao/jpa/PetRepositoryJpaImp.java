package com.emrebozzkurtt.petclinic.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emrebozzkurtt.petclinic.dao.PetRepository;
import com.emrebozzkurtt.petclinic.model.Pet;

@Repository("petRepository")
public class PetRepositoryJpaImp implements PetRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Pet> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Pet", Pet.class).getResultList();
	}

	@Override
	public Pet findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Pet.class, id);
	}

	@Override
	public List<Pet> findByOwnerId(int id) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Pet where owner_id = :id", Pet.class).setParameter("id", id).getResultList();
	}

	@Override
	public void createPet(Pet pet) {
		// TODO Auto-generated method stub
		entityManager.persist(pet);
	}

	@Override
	public Pet updatePet(Pet pet) {
		// TODO Auto-generated method stub
		return entityManager.merge(pet);
	}

	@Override
	public void deletePet(int id) {
		entityManager.remove(entityManager.getReference(Pet.class, id));

	}

	@Override
	public void deleteByOwnerId(int id) {
		// TODO Auto-generated method stub
		entityManager.createQuery("delete from Pet where owner.id = :id").setParameter("owner_id", id).executeUpdate();
	}

}
