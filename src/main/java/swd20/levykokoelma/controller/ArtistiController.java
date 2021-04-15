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

import swd20.levykokoelma.domain.Artisti;
import swd20.levykokoelma.domain.ArtistiRepository;


@CrossOrigin
@Controller //Spring-alustaohjelma Controller-luokasta olion sovelluksen käynnistyessä
public class ArtistiController {
	
	@Autowired
	private ArtistiRepository artistiRepository;
	
	// RESTful service to get all artists
    @RequestMapping(value="/listaaArtistit", method = RequestMethod.GET)
    public @ResponseBody List<Artisti> getArtistsRest() {	
        return (List<Artisti>) artistiRepository.findAll();
    }    

	// RESTful service to get artist by id
    @RequestMapping(value="/artistit/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Artisti> findArtists(@PathVariable("id") Long artisti_id) {	
    	return artistiRepository.findById(artisti_id);
    } 
    
    // RESTful service to save new artist
    @RequestMapping(value="/artistit", method = RequestMethod.POST)
    public @ResponseBody Artisti saveArtistRest(@RequestBody Artisti artisti) {	
    	return artistiRepository.save(artisti);
    }
	
	
	//hakee kaikki kategoriat 
    @RequestMapping(value="/listaaArtistit") //http://localhost:8080/listaaArtistit
    public String artistList(Model model) {	
        model.addAttribute("artisti", artistiRepository.findAll());
        return "listaaArtistit";
    }
    
    //palauttaa tyhjän artistin lisäyslomakkeen
    @RequestMapping(value = "/lisaaArtisti") 
    public String addartist(Model model){
    	model.addAttribute("artisti", new Artisti());
        return "lisaaArtisti"; //lisaaArtisti.html
    }     
    
    //vastaanottaa artistilomakkeen tiedot ja tallentaa ne tietokantaan
    @RequestMapping(value = "/tallennaArtisti", method = RequestMethod.POST)
    public String save(Artisti artisti){
    	artistiRepository.save(artisti);
        return "redirect:listaa"; //Muuta tähän listaaArtistit, jos haluat että ohjaa sinne
    }    
	


}
