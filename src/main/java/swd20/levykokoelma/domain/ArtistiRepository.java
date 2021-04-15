package swd20.levykokoelma.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ArtistiRepository extends CrudRepository <Artisti, Long>{
    List<Artisti> findByNimi(String nimi);

}
