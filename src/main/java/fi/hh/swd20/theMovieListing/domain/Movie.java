package fi.hh.swd20.theMovieListing.domain;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
public class Movie {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String title;
	private String director;
	private long year;
	private long length;
	private String genre;
	
	@ManyToOne
	@JoinColumn(name = "ratingid")
	private Rating rating;
	
	// CONST
	public Movie() {
		super();
		this.title = null;
		this.director = null;
		this.year = 0L;
		this.length = 0L;
		this.genre = null;
		this.rating = null;
	}

	public Movie(String title, String director, long year, long length, String genre, Rating rating) {
		super();
		this.title = title;
		this.director = director;
		this.year = year;
		this.length = length;
		this.genre = genre;
		this.rating = rating;
	}

	// GETTERS N SETTERS
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public long getYear() {
		return year;
	}

	public void setYear(long year) {
		this.year = year;
	}

	public long getLength() {
		return length;
	}

	public void setLength(long length) {
		this.length = length;
	}
	
	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	
	public Rating getRating() {
		return rating;
	}

	public void setRating(Rating rating) {
		this.rating = rating;
	}

	@Override
	public String toString() {
		if (this.rating != null)
		return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", length=" + length + ", genre=" + genre + ", rating =" + this.getRating() +"]";
		else
			return "Movie [id=" + id + ", title=" + title + ", director=" + director + ", year=" + year + ", length=" + length + ", genre=" + genre + "]";
	}
	
}
