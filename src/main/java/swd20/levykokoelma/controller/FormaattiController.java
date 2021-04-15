package swd20.levykokoelma.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.levykokoelma.domain.Formaatti;
import swd20.levykokoelma.domain.FormaattiRepository;

@CrossOrigin
@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class FormaattiController {
	
	
	@Autowired
	private FormaattiRepository formaattiRepository;
	
	// RESTful service to get all formats
    @RequestMapping(value="/listaaFormaatit", method = RequestMethod.GET)
    public @ResponseBody List<Formaatti> getArtistsRest() {	
        return (List<Formaatti>) formaattiRepository.findAll();
    }    

	// RESTful service to get format by id
    @RequestMapping(value="/formaatit/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Formaatti> findFormats(@PathVariable("id") Long formaatti_id) {	
    	return formaattiRepository.findById(formaatti_id);
    } 
    
    // RESTful service to save new format
    @RequestMapping(value="/formaatit", method = RequestMethod.POST)
    public @ResponseBody Formaatti saveArtistRest(@RequestBody Formaatti formaatti) {	
    	return formaattiRepository.save(formaatti);
    }
	
	
	//hakee kaikki formaatit 
    @RequestMapping(value="/listaaFormaatit") //http://localhost:8080/listaaArtistit
    public String formattList(Model model) {	
        model.addAttribute("formaatti", formaattiRepository.findAll());
        return "listaaArtistit";
    }
    
    //palauttaa tyhjän formaatin lisäyslomakkeen
    @RequestMapping(value = "/lisaaFormaatti") 
    public String addFormat(Model model){
    	model.addAttribute("formaatti", new Formaatti());
        return "lisaaFormaatti"; //lisaaFormaatti.html
    }     
    
    //vastaanottaa formaattilomakkeen tiedot ja tallentaa ne tietokantaan
    @RequestMapping(value = "/tallennaFormaatti", method = RequestMethod.POST)
    public String save(Formaatti formaatti){
    	formaattiRepository.save(formaatti);
        return "redirect:listaa"; //Muuta tähän listaaFormaatit, jos haluat että ohjaa sinne
    }    
	

}
