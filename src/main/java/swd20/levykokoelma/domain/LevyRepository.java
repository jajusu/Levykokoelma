package swd20.levykokoelma.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;


public interface LevyRepository extends CrudRepository<Levy, Long>{

    List<Levy> findByNimiContainingIgnoreCase(String nimi);
    List<Levy> findByVuosi(int vuosi);
    List<Levy> findByArtisti(String artisti);

}
