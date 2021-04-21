package fi.hh.swd20.theMovieListing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import fi.hh.swd20.theMovieListing.domain.User;
import fi.hh.swd20.theMovieListing.domain.UserRepository;
import fi.hh.swd20.theMovieListing.domain.Movie;
import fi.hh.swd20.theMovieListing.domain.MovieRepository;
import fi.hh.swd20.theMovieListing.domain.Rating;
import fi.hh.swd20.theMovieListing.domain.RatingRepository;

@SpringBootApplication
public class TheMovieListingApplication {

	private static final Logger log = LoggerFactory.getLogger(TheMovieListingApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(TheMovieListingApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner movieDemo(MovieRepository mrepo, RatingRepository rrepo, UserRepository urepo) {
		return (args) -> {
			
			log.info("Save ratings and new movies");
			
			Rating rate1 = new Rating("G - All Ages");
			rrepo.save(rate1);
			
			Rating rate2 = new Rating("PG - Parental Guidance");
			rrepo.save(rate2);
			
			Rating rate3 = new Rating("R - Adult");
			rrepo.save(rate3);
			
			mrepo.save(new Movie("The Lion King","Jon Favreau", 1994, 88, "Family", rate1));
			mrepo.save(new Movie("Spirited Away","Hayao Miyazaki", 2001, 125, "Fantasy", rate2));
			mrepo.save(new Movie("Parasite","Bong Joon-ho", 2019, 132, "Dark Humor", rate3));
			
			//Users/admins
			
			User user1 = new User("visitor", "$2a$10$k2eDxQs1ckJL23dfJNe28uC3aVAOfP48B9Z5YpdWsnZPsr.5hka26", "VISITOR");
			User user2 = new User("admin", "$2a$10$QViDekoV4kmuGFlfe4.jIOXkEeqXt8RSMt9HfwB.wmeAoXPHMeSgW", "ADMIN");
			urepo.save(user1);
			urepo.save(user2);
			
			log.info("fetch all movies");
			for (Movie movie : mrepo.findAll()) {
				log.info(movie.toString());
			}
		};
	}
}
