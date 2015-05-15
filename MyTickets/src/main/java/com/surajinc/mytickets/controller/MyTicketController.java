package com.surajinc.mytickets.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MyTicketController {

	// @Autowired

	@RequestMapping(value = "/search/movieName/{text}", method = RequestMethod.GET)
	public String manageMovieForward(@PathVariable String text, Model model,
			HttpServletRequest request) {

		System.out.println(text);

		return "searchResult";
	}

	@RequestMapping(value = "/browsemovies")
	public String browseMovies(Model model) {

		return "browseMovies";
	}

	// @RequestMapping(value = "/news")
	// public String newsForward(Model model) {
	// List<Movie> movieList = movieService.listMovie();
	// model.addAttribute("movieShowingList", movieList);
	// return "movieNews";
	// }

	// @RequestMapping(value = "/movieInfo/{movieId}")
	// public String moviesInfo(@PathVariable int movieId, Model model) {
	// System.out.println(movieId);
	// Movie movie = movieService.getMovie(movieId);
	// System.out.println(movie.getName());
	// model.addAttribute("movieName", movie.getName());
	// return "movieInformation";
	// }
}
