package fi.hh.swd20.theMovieListing.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Long>{
	
	List<Rating> findByName(String name);

}
