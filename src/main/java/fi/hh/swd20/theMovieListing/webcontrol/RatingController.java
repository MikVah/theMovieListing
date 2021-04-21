package fi.hh.swd20.theMovieListing.webcontrol;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.PathVariable;

import fi.hh.swd20.theMovieListing.domain.RatingRepository;

import java.util.List;
import java.util.Optional;


import fi.hh.swd20.theMovieListing.domain.Rating;

@Controller
public class RatingController {

	@Autowired
	private RatingRepository rrepo;
	
	// <<< CONTROLLERS >>>
	
	// Get all ratings
	@RequestMapping(value="/ratinglist")
	public String getAllRatings(Model model) {
		model.addAttribute("ratings", rrepo.findAll());
		return "ratinglist";
	}
	
	// REST get all ratings
	@RequestMapping(value="/ratings", method = RequestMethod.GET)
	public @ResponseBody List<Rating> findAllRatingsRest() {
		return (List <Rating>) rrepo.findAll();
	}
	
	// REST get rating by ID 
	@RequestMapping(value="/ratings/{id}", method = RequestMethod.GET)
	public @ResponseBody Optional<Rating> findRatingByIdRest(@PathVariable("id") Long ratingId) {
		return rrepo.findById(ratingId);
	}
	
	@RequestMapping(value="/create")
	public String createCategory(Model model) {
		model.addAttribute("category", new Rating());
		return "newrating";
	}
	
	@RequestMapping(value="/saverating", method = RequestMethod.POST)
	public String saveRating(Rating rating) {
		rrepo.save(rating);
		return "redirect:/ratinglist";
	}
	
}
