package com.emrebozzkurtt.petclinic.web;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.emrebozzkurtt.petclinic.exception.InternalServerException;
import com.emrebozzkurtt.petclinic.exception.OwnerNotFoundException;
import com.emrebozzkurtt.petclinic.model.Owner;
import com.emrebozzkurtt.petclinic.service.PetClinicService;

@RestController
@RequestMapping("/rest")
public class PetClinicRestController {
	@Autowired
	private PetClinicService petClinicService;

	@RequestMapping(method = RequestMethod.POST, value = "/owner")
	public ResponseEntity<URI> createOwner(@RequestBody Owner owner) {
		try {
			petClinicService.createOwner(owner);
			int id = owner.getId();
			URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(id).toUri();
			return ResponseEntity.created(location).build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/owner/{id}")
	public ResponseEntity<?> updateOwner(@PathVariable("id") int id, @RequestBody Owner ownerRequest) {
		try {

			Owner owner = petClinicService.findOwner(id);
			owner.setFirstName(ownerRequest.getFirstName());
			owner.setLastName(ownerRequest.getLastName());
			petClinicService.updateOwner(owner);
			;
			return ResponseEntity.ok().build();

		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		} catch (Exception ex) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/owner/{id}")
	public ResponseEntity<?> deleteOwner(@PathVariable("id") int id) {
		try {

			Owner owner = petClinicService.findOwner(id);
			petClinicService.deleteOwner(id);
			;
			return ResponseEntity.ok().build();

		} catch (OwnerNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}
	}

	@RequestMapping(method = RequestMethod.GET, value = "/owner/{id}", produces = "application/json")
	public ResponseEntity<?> getOwnerAsHateoasResource(@PathVariable("id") int id) {
		try {
			Owner owner = petClinicService.findOwner(id);
			Link self = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withSelfRel();
			Link create = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner").withRel("create");
			Link update = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("update");
			Link delete = WebMvcLinkBuilder.linkTo(PetClinicRestController.class).slash("/owner/" + id).withRel("delete");
			EntityModel<Owner> model = new EntityModel<Owner>(owner,self,create,update,delete);
			return ResponseEntity.ok(model);

		} catch (OwnerNotFoundException ex) {
			throw ex;
		} catch (Exception ex) {
			throw new InternalServerException(ex);
		}
	}

	@GetMapping(value = "/owners")
	public ResponseEntity<List<Owner>> getOwners() {
		List<Owner> owners = petClinicService.findOwners();
		return ResponseEntity.ok(owners);
	}

	@GetMapping(value = "/owner")
	public ResponseEntity<List<Owner>> getOwners(@RequestParam("lastName") String lastName) {
		List<Owner> owners = petClinicService.findOwners(lastName);
		return ResponseEntity.ok(owners);
	}

	@GetMapping(value = "/owner/{id}")
	public ResponseEntity<Owner> getOwner(@PathVariable("id") int id) {
		try {
			Owner owner = petClinicService.findOwner(id);
			return ResponseEntity.ok(owner);
		} catch (OwnerNotFoundException ex) {
			return ResponseEntity.notFound().build();
		}

	}

}
