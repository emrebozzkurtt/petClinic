package com.emrebozzkurtt.petclinic.dao.mem;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Repository;

import com.emrebozzkurtt.petclinic.dao.OwnerRepository;
import com.emrebozzkurtt.petclinic.model.Owner;

@Repository
public class OwnerRepositoryInMemoryImpl implements OwnerRepository{

	private Map<Integer, Owner> ownersMap = new HashMap<>();
	
	public OwnerRepositoryInMemoryImpl() {
		Owner owner1 = new Owner();
		owner1.setFirstName("Emre");
		owner1.setLastName("Bozkurt");
		owner1.setId(0);
		
		Owner owner2 = new Owner();
		owner2.setFirstName("Mustafa");
		owner2.setLastName("Bozkurt");
		owner2.setId(1);
		
		Owner owner3 = new Owner();
		owner3.setFirstName("Cuma");
		owner3.setLastName("Dindar");
		owner3.setId(2);
		
		Owner owner4 = new Owner();
		owner4.setFirstName("Berkay");
		owner4.setLastName("Can");
		owner4.setId(3);
		
		ownersMap.put(owner1.getId(), owner1);
		ownersMap.put(owner2.getId(), owner2);
		ownersMap.put(owner3.getId(), owner3);
		ownersMap.put(owner4.getId(), owner4);
	}
	
	@Override
	public List<Owner> findAll() {
		// TODO Auto-generated method stub
		return new ArrayList<>(ownersMap.values());
	}

	@Override
	public Owner findById(int id) {
		// TODO Auto-generated method stub
		return ownersMap.get(id);
	}

	@Override
	public List<Owner> findByLastName(String lastName) {
		// TODO Auto-generated method stub
		return ownersMap.values().stream().filter(owner->owner.getLastName().equals(lastName)).collect(Collectors.toList());
	}

	@Override
	public void createOwner(Owner owner) {
		owner.setId(new Date().getSeconds());	
		ownersMap.put(owner.getId(), owner);
	}

	@Override
	public Owner updateOwner(Owner owner) {
		ownersMap.replace(owner.getId(), owner);
		return owner;
	}

	@Override
	public void deleteOwner(int id) {
		ownersMap.remove(id);
	}

}
