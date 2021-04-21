package swd20.levykokoelma;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import swd20.levykokoelma.domain.ArtistiRepository;
import swd20.levykokoelma.domain.FormaattiRepository;
import swd20.levykokoelma.domain.Levy;
import swd20.levykokoelma.domain.LevyRepository;


@DataJpaTest
public class LevyRepositoryTest {

	@Autowired
	private LevyRepository lrepository;
	@Autowired
	private ArtistiRepository arepository;
	@Autowired
	private FormaattiRepository frepository;
	
	//Testataan uuden levyn luonti
	@Test
	public void uusiLevy() {
		Levy levy= new Levy("Testinimi", arepository.findByNimi("Beherit").get(0), "infoa", 66, 2020, frepository.findByNimi("CD").get(0));
		lrepository.save(levy);
		System.out.println("TESTILEVY "+levy);
		assertThat(lrepository.findByNimi("Testinimi").get(0).getNimi()).isEqualTo("Testinimi");
	}
	
	//Testataan haku nimellä. Kirjainkoolla ei väliä ja vain nimen osa riittää matchiin
	@Test
	public void haeNimellaContainsJaIgnoreCase() {
		assertThat(lrepository.findByNimiContainingIgnoreCase("engR").get(0).getNimi()).isEqualTo("Engram");
	}
	
	//Testataan levyn nimen muokkausta
	@Test
	public void muokkaaLevya() {
		Long id=lrepository.findByNimi("Engram").get(0).getLevy_id();
		lrepository.findByNimi("Engram").get(0).setNimi("Muokkasin");
		assertThat(lrepository.findById(id).get().getNimi()).isEqualTo("Muokkasin");
	}
	
	//Testataan levyn poistoa
	@Test
	public void poistaLevy() {
		Long id=lrepository.findByNimi("Engram").get(0).getLevy_id();
		lrepository.deleteById(id);
		assertThat(lrepository.findByNimi("Engram").size()).isEqualByComparingTo(0);
	}

}
