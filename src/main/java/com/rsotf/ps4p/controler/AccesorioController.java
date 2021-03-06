package com.rsotf.ps4p.controler;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rsotf.ps4p.model.entity.AccesorioEntity;
import com.rsotf.ps4p.model.entity.SalaEntity;
import com.rsotf.ps4p.model.service.IAccesorioService;
import com.rsotf.ps4p.model.service.ISalaService;

@Controller
@SessionAttributes("accesorio")
@RequestMapping("/accesorios")
public class AccesorioController {

	@Autowired
	private IAccesorioService accesorioService;
	
	@Autowired
	private ISalaService salaService;
	
	@GetMapping("/list")
	public String findAll(Model model) {	
		
		model.addAttribute("titulo", "Listado de Edificios");
		model.addAttribute("accesorio", accesorioService.findAll());
		return "accesorios/listarAccesorios";
	}
	
	@GetMapping("/findByNombre/{name}")
	public String findByNombre(@PathVariable(value = "name") String name) {
		
		List<AccesorioEntity> acce = accesorioService.findByNombreLikeIgnoreCase(name);
		if (acce != null) {
			System.out.println("existe");
		}else {
		System.out.println("no existe");
		}
		return "";
	}
	
	@PostMapping("/save")
	public String save(@Valid @ModelAttribute("accesorio") AccesorioEntity accesorio, RedirectAttributes flash,SessionStatus status) {
		SalaEntity sala = null;
		if (accesorio.getSala() != null) {
			sala = salaService.findById(accesorio.getSala().getId());
		}		
		accesorio.setSala(sala);
		accesorioService.save(accesorio);
		status.setComplete();
		flash.addFlashAttribute("success", "Accesorio Creado con Exito");
		return "redirect:/accesorios/list";		
	}
	
	@GetMapping({"/form/{sala}", "/form"})
	public String form(Model model, @RequestParam(value = "sala", defaultValue = "0") Long sala) {
		
		AccesorioEntity accesorio = new AccesorioEntity();
		if ( sala > 0) {
			SalaEntity sal = salaService.findById(sala);
			accesorio.setSala(sal);
		}		
		model.addAttribute("accesorio", accesorio);
		model.addAttribute("titulo", "Crear nuevo Accesorio");
		return "accesorios/form";
	}
	
	@GetMapping("/formE/{id}")
	public String formE(Model model, @PathVariable(value = "id") Long id) {
		
		AccesorioEntity accesorio = accesorioService.findById(id);	
		System.out.println(accesorio.getSala());
		model.addAttribute("accesorio", accesorio);
		model.addAttribute("titulo", "Editar  Accesorio");
		return "accesorios/form";
	}
	
	
}
