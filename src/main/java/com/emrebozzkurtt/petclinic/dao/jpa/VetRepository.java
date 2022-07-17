package com.emrebozzkurtt.petclinic.dao.jpa;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emrebozzkurtt.petclinic.model.Vet;

public interface VetRepository extends JpaRepository<Vet, Integer> {

}
