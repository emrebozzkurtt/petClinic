package com.emrebozzkurtt.petclinic.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.emrebozzkurtt.petclinic.model.Owner;
import com.emrebozzkurtt.petclinic.service.PetClinicService;

@Controller
public class PetClinicDeleteOwnerController {
	
	@Autowired
	private PetClinicService petClinicService;
	
	@GetMapping(value = "/owners/delete/{id}")
	public String loadOwner(@PathVariable int id, ModelMap modelMap) {
		Owner owner = petClinicService.findOwner(id);
		modelMap.put("owner", owner);
		return "deleteOwner";
	}
	
	@PostMapping(value = "/owners/delete/{id}")
	public String handleFormSubmit(@PathVariable int id, ModelMap modelMap, RedirectAttributes attributes) {
		petClinicService.deleteOwner(id);
		attributes.addFlashAttribute("message", "Owner deleted with id:" + id);
		return "redirect:/owners";
	}
	
	
}
