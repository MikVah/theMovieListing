package fi.hh.swd20.theMovieListing;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import fi.hh.swd20.theMovieListing.domain.MovieRepository;
import fi.hh.swd20.theMovieListing.domain.Movie;
import fi.hh.swd20.theMovieListing.domain.Rating;
import fi.hh.swd20.theMovieListing.domain.RatingRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class MovieRepositoryTest {

	@Autowired
	private MovieRepository mrepo;
	
	@Autowired
	private RatingRepository rrepo;
	
	@Test
	public void addNewMovie() {
		Movie movie = new Movie("Titanic", "James Cameron", 1994, 180, "Romance", new Rating("PG13"));
		mrepo.save(movie);
		assertThat(movie.getId()).isNotNull();
	}
	
	@Test
	public void findByTitle() {
		List<Movie> movies = mrepo.findByTitle("Parasite");
		assertThat(movies).hasSize(1);
		assertThat(movies.get(0).getDirector()).isEqualTo("Bong Joon-ho");
	}

	// TEST - new rating
	@Test
	public void addNewRating() {
		Rating rating = new Rating("Test");
		rrepo.save(rating);
		assertThat(rating.getRatingid()).isNotNull();
	}
	
	
}
	
