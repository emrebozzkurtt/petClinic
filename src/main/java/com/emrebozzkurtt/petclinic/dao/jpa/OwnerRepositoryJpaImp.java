package com.emrebozzkurtt.petclinic.dao.jpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import com.emrebozzkurtt.petclinic.dao.OwnerRepository;
import com.emrebozzkurtt.petclinic.model.Owner;

@Repository("ownerRepository")
public class OwnerRepositoryJpaImp implements OwnerRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<Owner> findAll() {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Owner", Owner.class).getResultList();
	}

	@Override
	public Owner findById(int id) {
		// TODO Auto-generated method stub
		return entityManager.find(Owner.class, id);
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return entityManager.createQuery("from Owner where lastname = :lastName", Owner.class).getResultList();
	}

	@Override
	public void createOwner(Owner owner) {
		entityManager.persist(owner);
	}

	@Override
	public Owner updateOwner(Owner owner) {
		return entityManager.merge(owner);
	}

	@Override
	public void deleteOwner(int id) {
		entityManager.remove(entityManager.getReference(Owner.class, id));
	}

}
