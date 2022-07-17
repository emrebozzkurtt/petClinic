package com.emrebozzkurtt.petclinic.web;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emrebozzkurtt.petclinic.model.Owner;
import com.emrebozzkurtt.petclinic.service.PetClinicService;

@Controller
public class PetClinicNewOwnerController {
	
	@Autowired
	private PetClinicService petClinicService;
	
	@GetMapping(value = "/owners/new")
	public String newOwner() {
		return "newOwner";
	}
	
	@ModelAttribute
	public Owner initModel() {
		return new Owner();
	}
	
	@PostMapping(value = "/owners/new")
	public String handleFormSubmit(@ModelAttribute @Valid Owner owner, BindingResult bindingResult, RedirectAttributes attributes) {
		if(bindingResult.hasErrors()) {
			return "newOwner";
		}
		petClinicService.createOwner(owner);
		attributes.addFlashAttribute("message", "Owner Created with id:" + owner.getId());
		return "redirect:/owners";
	}
}
