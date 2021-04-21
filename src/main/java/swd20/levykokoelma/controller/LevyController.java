package swd20.levykokoelma.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.levykokoelma.domain.ArtistiRepository;
import swd20.levykokoelma.domain.FormaattiRepository;
import swd20.levykokoelma.domain.Levy;
import swd20.levykokoelma.domain.LevyRepository;

@CrossOrigin //Huom! Lisää tämä jos koodi ja palvelin eri sijainnissa
@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä

public class LevyController {
	
	@Autowired
	LevyRepository lrepository;
	
	@Autowired
	ArtistiRepository arepository;
	
	@Autowired
	FormaattiRepository frepository;
	
	//Oma login
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
	
	// REST levyille
    //JAva-kielinen Levy-luokan oliolista muunnetaan JSON-levylistaksi ja 
    //lähetetään web-selaimelle vastauksena
    @RequestMapping(value="/levyt", method = RequestMethod.GET)
    public @ResponseBody List<Levy> levyListaRest() {	
        return (List<Levy>) lrepository.findAll();
    }
    
    //voi tehdä myös näin RequestMappingin sijaan: @GetMapping ("levyt/{id}")
    
	//etsi levy Id:llä
    @RequestMapping(value="/levyt/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Levy> etsiLevyRest(@PathVariable("id") Long levyId) {	
    	return lrepository.findById(levyId);
    }      
    
    //tallenna levy rest
    @RequestMapping(value="/levyt", method = RequestMethod.POST)
    public @ResponseBody Levy tallennaLevyRest(@RequestBody Levy levy) {	
    	return lrepository.save(levy);
    }
	
    //listaa kaikki restin kautta
    @RequestMapping(value="/listaa2", method = RequestMethod.GET)
    public String listaaLevyt(Model model) {
    	model.addAttribute("levyt", lrepository.findAll());
    	return "listaa";
    }
    
    //listaa restin kautta
    @RequestMapping(value="/listaa2/{vuosi}", method = RequestMethod.GET)
    public String listaaLevytVuosi(Model model, int vuosi) {
    	model.addAttribute("levyt", lrepository.findByVuosi(vuosi));
    	return "listaa";
    }
	
	// Listaa kaikki
    @RequestMapping(value="/listaa")
    public String levyLista(Model model, String haku) {	
        model.addAttribute("levyt", lrepository.findAll());
        return "listaa";
    }
    
	// Näytä kaikki levyt
    @RequestMapping(value="/haku",  method = RequestMethod.GET)
    public String hakuLista(Model model, String haku) {	
    	if (haku=="") {
            model.addAttribute("levyt", lrepository.findAll());
    	}else {
    		model.addAttribute("levyt", lrepository.findByNimiContainingIgnoreCase(haku));
    	}
        return "listaa";
    }

	//testi
	@RequestMapping(value = "/index", method= RequestMethod.GET) //http://localhost:8080/index
	public String index() {
		return "testi"; //testi.html
	}
	
    // Lisää levy
    @RequestMapping(value = "/lisaaLevy")
    public String lisaaLevy(Model model){
    	model.addAttribute("levy", new Levy());
    	model.addAttribute("artisti", arepository.findAll());
    	model.addAttribute("formaatti", frepository.findAll());
        return "lisaaLevy";
    }

    
//    // Tallenna uusi levy validoinnilla
//    @RequestMapping(value = "/tallenna", method = RequestMethod.POST)
//    public String tallenna(@Valid Levy levy, BindingResult bindingResult,Model model){
//    	if (bindingResult.hasErrors()) { // validation errors 
//        	model.addAttribute("artisti", arepository.findAll());
//        	model.addAttribute("formaatti", frepository.findAll());
//			return "lisaaLevy";  // return back to form
//		} else { // no validation errors
//			lrepository.save(levy);
//	        return "redirect:listaa";
//		}
//        
//    }    
    
    // Tallenna uusi tai muokattu levy validoinnilla. Käytössä.
    @RequestMapping(value = "/tallennaKaikki", method = RequestMethod.POST)
    public String tallennaKaikki(@Valid Levy levy, BindingResult bindingResult, Model model){
    	if (bindingResult.hasErrors()) { // validation errors 
         	model.addAttribute("artisti", arepository.findAll());
        	model.addAttribute("formaatti", frepository.findAll());
    		if (levy.getLevy_id()==0) {
    			return "lisaaLevy";  // lisaaLevy.html
    		}else { 
    	        return "muokkaaLevy"; //muokkaaLevy.html
    		}
		} else { // no validation errors
			lrepository.save(levy);
	        return "redirect:listaa";
		}
        
    }  
    
//    // Tallenna levy vanha
//    @RequestMapping(value = "/save", method = RequestMethod.POST)
//    public String save(Levy levy){
//        lrepository.save(levy);
//        return "redirect:listaa";
//    }    

    // Poista levy
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteLevy(@PathVariable("id") Long levy_id) {
    	lrepository.deleteById(levy_id);
        return "redirect:../listaa";
    }  
    
//    // Tallenna muokattu levy validoinnilla
//    @RequestMapping(value = "/tallennaMuokkaus", method = RequestMethod.POST)
//    public String tallennaMuokkaus(@Valid Levy levy, BindingResult bindingResult,Model model){
//    	if (bindingResult.hasErrors()) { // validation errors 
//        	model.addAttribute("artisti", arepository.findAll());
//        	model.addAttribute("formaatti", frepository.findAll());
//			return "muokkaaLevy";  // return back to form
//		} else { // no validation errors
//			lrepository.save(levy);
//	        return "redirect:listaa";
//		}
//        
//    }    
    
    
    // Muokkaa levy
    @RequestMapping(value = "/update/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String muokkaa(@PathVariable(value="id") Long levy_id, Model model){
    	model.addAttribute("artisti", arepository.findAll());
    	model.addAttribute("formaatti", frepository.findAll());
    	model.addAttribute("levy", lrepository.findById(levy_id));
        return "muokkaaLevy"; //muokkaaLevy.html
    }     


}
