package fi.hh.swd20.theMovieListing.domain;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.CascadeType;

import java.util.List;

@Entity
public class Rating {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long ratingid;
	private String name;
	
	@OneToMany(cascade=CascadeType.PERSIST, mappedBy = "rating")
	@JsonIgnoreProperties("rating")
	private List<Movie> movies;

	// CONSTRUCTORS
	public Rating() {
		super();
		this.name = null;
	}

	public Rating(String name) {
		super();
		this.name = name;
	}

	//GETTERS N SETTERS
	public Long getRatingid() {
		return ratingid;
	}

	public void setRatingid(Long ratingid) {
		this.ratingid = ratingid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Movie> getMovies() {
		return movies;
	}

	public void setMovies(List<Movie> movies) {
		this.movies = movies;
	}

	@Override
	public String toString() {
		return "Rating [ratingid=" + ratingid + ", name=" + name + "]";
	}
	
	
}
