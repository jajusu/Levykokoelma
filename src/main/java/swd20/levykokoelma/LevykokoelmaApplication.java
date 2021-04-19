package swd20.levykokoelma;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import swd20.levykokoelma.domain.Artisti;
import swd20.levykokoelma.domain.ArtistiRepository;
import swd20.levykokoelma.domain.Formaatti;
import swd20.levykokoelma.domain.FormaattiRepository;
import swd20.levykokoelma.domain.Levy;
import swd20.levykokoelma.domain.LevyRepository;
import swd20.levykokoelma.domain.User;
import swd20.levykokoelma.domain.UserRepository;

@SpringBootApplication
public class LevykokoelmaApplication {
	private static final Logger log = LoggerFactory.getLogger(LevykokoelmaApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(LevykokoelmaApplication.class, args);
	}

	@Bean
	public CommandLineRunner levyDemo(LevyRepository lrepository, ArtistiRepository arepository, FormaattiRepository frepository, UserRepository urepository) {
		return (args)->{
			log.info("Tallennetaan artisteja");
			//artisti: nimi, maa
			Artisti roky=new Artisti("Roky Erickson", "USA");
			arepository.save(roky);
			Artisti beherit=new Artisti("Beherit", "Suomi");
			arepository.save(beherit);
			Artisti kylie=new Artisti("Kylie Minogue", "Australia");
			arepository.save(kylie);
			
			log.info("Tallennetaan formaatteja");
			//formaatti: nimi
			Formaatti cd=new Formaatti("CD");
			frepository.save(cd);
			Formaatti lp=new Formaatti("LP");
			frepository.save(lp);
			Formaatti kasetti=new Formaatti("Kasetti");
			frepository.save(kasetti);
			
			log.info("Tallennetaan levyjä");
			//levy: 	public Levy(String nimi, Artisti artisti, String info, int kesto, int vuosi, Formaatti formaatti) {
			lrepository.save(new Levy("The Evil One", roky, "Pientä naarmua, soi hyvin", 52, 1985, lp));
			lrepository.save(new Levy("Gremlins have pictures", roky, "Japanipainos", 53, 1986, cd));
			lrepository.save(new Levy("The oath of Black Blood", beherit, "Bootleg", 66, 1991, lp));
			lrepository.save(new Levy("Engram", beherit, "Levy muoveissa", 45, 2009, cd));
			lrepository.save(new Levy("Enjoy Yourself", kylie, "Kuin uusi", 36, 1989, kasetti));
			
			// Create users: admin/demoni demo/demo
			User user1 = new User("demo", "$2a$10$E0Yq9eUwiA9GNMai6gUxp.XhDcU1rE6qcbnLAz6xuAjQgvltaVp7u", "USER");
			User user2 = new User("admin", "$2a$10$H61PVKvCkkr/OZqaS4tMLOWeQlYdQGhWSVFwCXGrmI1.uZJFRJhPK", "ADMIN");
			urepository.save(user1);
			urepository.save(user2);
			
			log.info("fetch all ARTISTIs");
			for (Artisti artisti:arepository.findAll()) {
				log.info(artisti.toString());
			}
			
			log.info("fetch all FORMAATTIs");
			for (Formaatti formaatti:frepository.findAll()) {
				log.info(formaatti.toString());
			}

			log.info("fetch all LEVYs");
			for (Levy levy:lrepository.findAll()) {
				log.info(levy.toString());
			}
			
			log.info("fetch all USERs");
			for (User user:urepository.findAll()) {
				log.info(user.toString());
			}


		};
	}
}
