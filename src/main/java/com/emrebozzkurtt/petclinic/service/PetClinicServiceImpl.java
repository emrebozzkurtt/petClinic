package com.emrebozzkurtt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emrebozzkurtt.petclinic.dao.OwnerRepository;
import com.emrebozzkurtt.petclinic.exception.OwnerNotFoundException;
import com.emrebozzkurtt.petclinic.model.Owner;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImpl implements PetClinicService{

	private OwnerRepository ownerRepository;
	
	@Autowired
	public void setOwnerRepository(OwnerRepository ownerRepository) {
		this.ownerRepository = ownerRepository;
	}
	
	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Owner> findOwners() {
		return ownerRepository.findAll();
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public List<Owner> findOwners(String lastName) {
		return ownerRepository.findByLastName(lastName);
	}

	@Override
	@Transactional(propagation = Propagation.SUPPORTS, readOnly = true)
	public Owner findOwner(int id) throws OwnerNotFoundException {
		Owner owner = ownerRepository.findById(id);
		if(owner == null) {
			new OwnerNotFoundException("Owner Not Found With id:" + id);
		}
		return owner;
	}

	@Override
	public void createOwner(Owner owner) {
		ownerRepository.createOwner(owner);
		
	}

	@Override
	public Owner updateOwner(Owner owner) {
		ownerRepository.updateOwner(owner);
		return owner;
	}

	@Override
	public void deleteOwner(int id) {
		ownerRepository.deleteOwner(id);
		
	}

	
}
