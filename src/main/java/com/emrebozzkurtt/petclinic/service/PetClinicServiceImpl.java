package com.emrebozzkurtt.petclinic.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.emrebozzkurtt.petclinic.dao.OwnerRepository;
import com.emrebozzkurtt.petclinic.dao.jpa.VetRepository;
import com.emrebozzkurtt.petclinic.exception.OwnerNotFoundException;
import com.emrebozzkurtt.petclinic.exception.VetNotFoundException;
import com.emrebozzkurtt.petclinic.model.Owner;
import com.emrebozzkurtt.petclinic.model.Vet;

@Service
@Transactional(rollbackFor = Exception.class)
public class PetClinicServiceImpl implements PetClinicService{

	private OwnerRepository ownerRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	@Autowired
	private VetRepository vetRepository;

	@Autowired
	public void setVetRepository(VetRepository vetRepository) {
		this.vetRepository = vetRepository;
	}

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
	@Secured(value = {"ROLE_USER","ROLE_EDITOR"})
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
		
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		mailMessage.setFrom("sender@sender.com");
		mailMessage.setTo("receiver@receiver.com");
		mailMessage.setSubject("Owner Created!");
		mailMessage.setText("Owner entity with\nid= "+ owner.getId() +
				",\t" + owner.getFirstName() + " " + owner.getLastName() +
				"\n Created Successfully.");
		mailSender.send(mailMessage);
		
		
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

	@Override
	public List<Vet> findVets() {
		return vetRepository.findAll();
	}

	@Override
	public Vet findVet(int id) throws VetNotFoundException {
		return vetRepository.findById(id).orElseThrow(()->{return new VetNotFoundException("Vet Not Found Id: "+id);});
	}

	
}
