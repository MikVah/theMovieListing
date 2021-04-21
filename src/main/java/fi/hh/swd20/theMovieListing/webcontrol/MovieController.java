package fi.hh.swd20.theMovieListing.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import fi.hh.swd20.theMovieListing.domain.MovieRepository;
import fi.hh.swd20.theMovieListing.domain.RatingRepository;

import java.util.List;
import java.util.Optional;

import fi.hh.swd20.theMovieListing.domain.Movie;

@CrossOrigin
@Controller
public class MovieController {

	@Autowired
	private MovieRepository mrepo;
	
	@Autowired
	private RatingRepository rrepo;
	
	// <<< CONTROLLERS >>>
	
	// Get all movies
	@RequestMapping(value="/movielist")
	public String getAllMovies(Model model) {
		model.addAttribute("movies", mrepo.findAll());
		return "movielist";
	}
	
	// REST all movies
	@RequestMapping(value="/movies", method = RequestMethod.GET)
	public @ResponseBody List<Movie> movieListRest() {
		return (List<Movie>) mrepo.findAll();
	}
	
	// REST find by ID
	@RequestMapping(value="/movies/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Movie> findMovieByIdRest(@PathVariable("id") Long movieId) {
		return mrepo.findById(movieId);
	}
	
	// REST SAVE NEW MOVIE
	@RequestMapping(value="/movies", method = RequestMethod.POST)
	public @ResponseBody Movie saveMovieRest(@RequestBody Movie movie) {
		return mrepo.save(movie);
	}
	
	// ADD movie
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/add")
	public String addMovie(Model model) {
		model.addAttribute("movie", new Movie());
		model.addAttribute("ratings", rrepo.findAll());
		return "addmovie";
	}
	
	// SAVE
	@RequestMapping(value="/save", method = RequestMethod.POST)
	public String saveMovie(Movie movie) {	
		mrepo.save(movie);
		return "redirect:/movielist";
	}	
	

	// DELETE movie by ID
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/delete/{id}", method = RequestMethod.GET)
	public String deleteMovie(@PathVariable(value="id") Long movieId) {
		mrepo.deleteById(movieId);
		return "redirect:/movielist";
	}
	
	// EDIT movie
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value="/edit/{id}", method = RequestMethod.GET)
	public String editMovie(@PathVariable(value="id") Long movieId, Model model) {
		model.addAttribute("movie", mrepo.findById(movieId));
		model.addAttribute("rating", rrepo.findAll());
		return "editmovie";
	}
}
