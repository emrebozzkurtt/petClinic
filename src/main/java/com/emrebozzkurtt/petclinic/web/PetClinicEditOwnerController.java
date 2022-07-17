package com.emrebozzkurtt.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emrebozzkurtt.petclinic.model.Owner;
import com.emrebozzkurtt.petclinic.service.PetClinicService;

@Controller
public class PetClinicEditOwnerController {
	

	@Autowired
	private PetClinicService petClinicService;
	
	@GetMapping(value = "/owners/update/{id}")
	public String loadOwner(@PathVariable int id, ModelMap modelMap) {
		Owner owner = petClinicService.findOwner(id);
		modelMap.put("owner", owner);
		return "editOwner";
	}
	
	@PostMapping(value = "/owners/update/{id}")
	public String handleFormSubmit(@ModelAttribute Owner owner, ModelMap modelMap, BindingResult bindingResult, RedirectAttributes attributes) {
		if(bindingResult.hasErrors()) {
			return "editOwner";
		}
		petClinicService.updateOwner(owner);
		attributes.addFlashAttribute("message", "Owner Updated with id:" + owner.getId());
		return "redirect:/owners";
	}
}
