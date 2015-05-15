package com.surajinc.mytickets.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.surajinc.mytickets.form.AddShowtimeForm;
import com.surajinc.mytickets.pojo.Cinema;
import com.surajinc.mytickets.pojo.City;
import com.surajinc.mytickets.pojo.Movie;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.User;
import com.surajinc.mytickets.service.CinemaService;
import com.surajinc.mytickets.service.CityService;
import com.surajinc.mytickets.service.MovieService;
import com.surajinc.mytickets.service.MovieShowingService;
import com.surajinc.mytickets.service.ShowtimeService;
import com.surajinc.mytickets.service.UserService;

@Controller

//@PreAuthorize("hasRole('admin')")
public class MyTicketAdminController {

	@Autowired
	CityService cityService;

	@Autowired
	MovieService movieService;

	@Autowired
	MovieShowingService movieShowingService;

	@Autowired
	CinemaService cinemaService;

	@Autowired
	ShowtimeService showtimeService;

	@Autowired
	UserService userService;

	@RequestMapping(value = "/admin/adminDashForward", method = RequestMethod.POST)
	public String adminDashForward(Model model, HttpServletRequest request) {
		
		String adminCity= request.getParameter("adminCity");
		if(adminCity==null) {
			model.addAttribute("message", "Please select a city!");
			return "admin/adminDash";
		}
		request.getSession().setAttribute("adminCity", adminCity);
		return "admin/adminDashForward";
	}
	
	@RequestMapping(value = "/admin/manageMovies")
	public String manageMovieForward(Model model) {

		List<Movie> movieList = movieService.listMovie();
		Movie movie = new Movie();
		model.addAttribute("movieList", movieList);
		model.addAttribute("movie", movie);
		return "admin/manageMovies";
	}

	@RequestMapping(value = "/admin/addMovie", method = RequestMethod.POST)
	public String addMovie(@Valid @ModelAttribute("movie") Movie movie,
			BindingResult result, Model model, HttpServletRequest request) {
		movieService.addOrUpdateMovie(movie);
		System.out.println("movie added");
		String categoryList = request.getParameter("categoryList");
		System.out.println(categoryList);

		List<Movie> movieList = movieService.listMovie();
		Movie movieNew = new Movie();
		model.addAttribute("movie", movieNew);
		model.addAttribute("movieList", movieList);
		model.addAttribute("message", "Movie Added Successfully");
		return "admin/manageMovies";
	}

	@RequestMapping(value = "/admin/manageMovieShowings")
	public String manageMovieShowingForward(Model model,
			HttpServletRequest request) {

		List<Movie> movieList = movieService.listMovie();
		String cityName = (String) request.getSession().getAttribute(
				"adminCity");
		City city = cityService.getCity(cityName);
		List<Cinema> cinemaList = cinemaService.listCinemaByCity(city
				.getCityId());

		MovieShowing movieShowing = new MovieShowing();
		model.addAttribute("movieList", movieList);
		model.addAttribute("cinemaList", cinemaList);
		model.addAttribute("movieShowing", movieShowing);
		return "admin/addMovieShowing";
	}
	
	
	@RequestMapping(value = "/admin/addMovieShowing", method = RequestMethod.POST)
	public String addMovieShowing(
			@Valid @ModelAttribute("movieShowing") MovieShowing movieShowing,
			BindingResult result, Model model, HttpServletRequest request) {

//		if(result.hasErrors()){
//			request.getSession().setAttribute("addingMovieShowingError", "Please make a valid selection");
//			return "redirect:/admin/manageMovieShowings";
//			}
		
		request.getSession().removeAttribute("addingMovieShowingError");
		String movieName = request.getParameter("movie");
		String cinemaName = request.getParameter("cinema");
		Movie movie = movieService.getMovie(movieName);
		Cinema cinema = cinemaService.getCinema(cinemaName);
		movieShowing.setCinema(cinema);
		movieShowing.setMovie(movie);
		movieShowingService.addMovieShowing(movieShowing);
		
		List<Movie> movieList = movieService.listMovie();
		String cityName = (String) request.getSession().getAttribute(
				"adminCity");
		
		City city = cityService.getCity(cityName);
		List<Cinema> cinemaList = cinemaService.listCinemaByCity(city
				.getCityId());
		
		
		model.addAttribute("movieList", movieList);
		model.addAttribute("cinemaList", cinemaList);
		model.addAttribute("movieShowing", new MovieShowing());
		model.addAttribute("successMessage", "Movie Showing added Successfully!");
		return "admin/addMovieShowing";
	}
	
	@RequestMapping(value = "/admin/manageShowTimings")
	public String manageShowtimeForward(Model model) {

		List<MovieShowing> movieShowingList = movieShowingService
				.listMovieShowing();

		// MovieShowing[] movie = new MovieShowing[movieShowingList.size()];
		// for(int i=0;i<movieShowingList.size();i++)
		// movie[i]= movieShowingList.get(i);

		AddShowtimeForm showtime = new AddShowtimeForm();
		model.addAttribute("showtime", showtime);
		model.addAttribute("movieShowingList", movieShowingList);
		return "admin/addMovieShowTimings";
	}

	
	@RequestMapping(value = "/admin/addMovieShowtime", method = RequestMethod.POST)
	public String addMovieShowtime(
			@Valid @ModelAttribute("showtime") AddShowtimeForm addShowtimeForm,
			BindingResult result, Model model, HttpServletRequest request) {

		if(result.hasErrors()){
			request.getSession().setAttribute("addingMovieShowtimeError", "Please make valid selection");
			return "redirect:/admin/manageShowTimings";
			}
		
		request.getSession().removeAttribute("addingMovieShowtimeError");
		String movieShowingId = request.getParameter("movieShowingId");
		if(movieShowingId==null){
			List<MovieShowing> movieShowingList = movieShowingService
					.listMovieShowing();
			AddShowtimeForm showtime = new AddShowtimeForm();
			model.addAttribute("showtime", showtime);
			model.addAttribute("movieShowingList", movieShowingList);
			model.addAttribute("message", "Please select a movie!");
			return "/admin/manageShowTimings";
		}
		MovieShowing movieShowing = movieShowingService.getMovieShowing(Integer
				.parseInt(movieShowingId));
		addShowtimeForm.setMovieShowing(movieShowing);
		showtimeService.addShowtime(addShowtimeForm);
		List<MovieShowing> movieShowingList = movieShowingService
				.listMovieShowing();
		AddShowtimeForm showtime = new AddShowtimeForm();
		model.addAttribute("showtime", showtime);
		model.addAttribute("movieShowingList", movieShowingList);
		model.addAttribute("message", "Showtimings created Successfully!");
		return "admin/addMovieShowTimings";
	}

	@RequestMapping(value = "/admin/manageUser")
	public String getUsers(Model model) {

		List<User> users = userService.listUser();
		model.addAttribute("users", users);
		return "admin/viewUsers";
	}

	@RequestMapping(value = "/admin/deleteUser/{firstName}")
	public String deleteUser(@PathVariable String firstName, Model model,
			HttpServletRequest request) {
		
		System.out.println(firstName);
		userService.removeUser(firstName);
		List<User> users = userService.listUser();
		model.addAttribute("users", users);
		model.addAttribute("message", "User Removed Successfully");
		return "admin/viewUsers";
	}

}
