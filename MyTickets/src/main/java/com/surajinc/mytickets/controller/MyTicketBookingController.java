package com.surajinc.mytickets.controller;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.surajinc.mytickets.form.BookMovieForm;
import com.surajinc.mytickets.form.LoginForm;
import com.surajinc.mytickets.form.UserForm;
import com.surajinc.mytickets.pojo.Cinema;
import com.surajinc.mytickets.pojo.City;
import com.surajinc.mytickets.pojo.Movie;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.Payment;
import com.surajinc.mytickets.pojo.Showtime;
import com.surajinc.mytickets.pojo.User;
import com.surajinc.mytickets.service.BookingService;
import com.surajinc.mytickets.service.CinemaService;
import com.surajinc.mytickets.service.CityService;
import com.surajinc.mytickets.service.MovieService;
import com.surajinc.mytickets.service.MovieShowingService;
import com.surajinc.mytickets.service.ShowtimeService;
import com.surajinc.mytickets.service.UserService;
import com.surajinc.mytickets.utility.BookingPdf;
import com.surajinc.mytickets.utility.SendEmail;

@Controller
public class MyTicketBookingController {

	// @Autowired
	// CityService cityService;

	// @Autowired
	// MovieService movieService;

	@Autowired
	MovieShowingService movieShowingService;

	@Autowired
	CinemaService cinemaService;

	@Autowired
	ShowtimeService showtimeService;

	@Autowired
	BookingService bookingService;

	@Autowired
	UserService userService;
	
	@Autowired
	CityService cityService;	
	
	@Autowired
	MovieService movieService;
	
//	 @InitBinder(value = "payment")
//	 protected void initBinder(WebDataBinder binder) {
//	  binder.setValidator(new PaymentValidator());
//	 }
//	
	@RequestMapping(value = "/showtime")
	public String displayShowtime(Model model) {
		
		List<MovieShowing> movieShowingList = movieShowingService
				.listMovieShowing();
		List<City> cityList = cityService.listCity();
		model.addAttribute("cityList", cityList);
		model.addAttribute("movieShowingList", movieShowingList);
		return "showtime";
	}

	@RequestMapping(value = "/getCinema")
	public String getCinema(Model model) {

		List<City> cityList = cityService.listCity();
		model.addAttribute("cityList", cityList);
		return "showtime";
	}
	
	@RequestMapping(value = "/getCinemaList", method= RequestMethod.POST, produces =
			"application/json", headers = "Accept=application/json")
	@ResponseBody public List<Cinema> sendCinemaList(HttpServletRequest request, Model model) {

		int cityId = Integer.parseInt(request.getParameter("pid"));
		System.out.println(cityId);
		List<Cinema> list= cinemaService.listCinemaByCity(cityId);
		System.out.println(list.get(0));
		return list;
	}
	
//	Send JSON Resposne to controller
	
//	 @RequestMapping(value = "/json", method = RequestMethod.GET, produces =
//	 "application/json", headers = "Accept=application/json ")
//	 @ResponseBody public List<User> getMovie() {
//	 User user= new User();
//     user.setFirstName("rjfgk)");
//     user.setLastName("kjrgf");
//     User user1 = new User();
//     user1.setFirstName("krfhgekjr");
//     user1.setLastName("erjklgel");
//     List<User> list= new ArrayList<User>();
//     list.add(user);
//     list.add(user1);
//     
//	 return list;
//	 }

	@RequestMapping(value="/getCinemaList",method=RequestMethod.GET)
	public void movieList(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		
		String cityId =  request.getParameter("cityName");
		List<Cinema> cinemaList = cinemaService.listCinemaByCity(Integer.parseInt(cityId));
		String cinemaString="";
		for(Cinema cinema:cinemaList)
			cinemaString+="<option>"+cinema.getName()+"</option>";
		System.out.println(cinemaString);
		if(cinemaString.equals(""))
			response.getWriter().println("<p>No Theatre for this city</p>");
		else
			response.getWriter().println("<select class='form-control' name='cinemaName' id='cinemaName' onChange='populateMovieList(this.value)'>"+cinemaString+"</select>");
	}
	
	@RequestMapping(value="/getMovieList",method=RequestMethod.GET)
	public void getMovieList(HttpServletRequest request,Model model,HttpServletResponse response) throws IOException{
		
		String cinemaName =  request.getParameter("cinemaName");
		
		Cinema cinema= cinemaService.getCinema(cinemaName);
		List<MovieShowing> movieList = movieShowingService.listMovieShowingByCinema(cinema.getCinemaId());
		
		String returnString="";
		for(MovieShowing movieShowing:movieList)
			returnString+="<option>"+movieShowing.getMovie().getName()+"</option>";
		System.out.println(returnString);
		if(returnString.equals(""))
			response.getWriter().println("<p>No Movies for this Cinema</p>");
		else
			response.getWriter().println("<select class='form-control' name='movieName' id='movieName'>"+returnString+"</select>");
		
	}

	@RequestMapping(value = "/movieInfo{movieId}")
	public String movieInformation(Model model) {

		// handle request form home page

		return "movieInformation";
	}

	@RequestMapping(value = "/buyTickets",method=RequestMethod.POST)
	public String buyTicket(Model model,
			HttpServletRequest request) {
		
		String cinemaName= request.getParameter("cinemaName");
		String movieName= request.getParameter("movieName");
		if(cinemaName==null||movieName==null)
			return "redirect: /showtime";
		Cinema cinema= cinemaService.getCinema(cinemaName);
		Movie movie= movieService.getMovie(movieName);
		
		MovieShowing movieShowing = movieShowingService.getMovieShowing(movie.getMovieId(), cinema.getCinemaId());
		BookMovieForm form = new BookMovieForm();
		form.setCityName(movieShowing.getCinema().getCity().getCityName());
		form.setCinemaName(cinemaName);
		form.setMovieShowing(movieShowing);
		request.getSession().setAttribute("bookMovieForm", form);
		return "buyTicket";
	}

	@RequestMapping(value = "/buyTicketForward",method=RequestMethod.POST)
	public String buyTicketForward(Model model, HttpServletRequest request) {

		String dateString = request.getParameter("datePicked");
		BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
				"bookMovieForm");
		List<Showtime> showtimes = showtimeService.listShowtimes(dateString,
				form.getMovieShowing());
		if (showtimes.size() == 0) {
			model.addAttribute("message1", "No showtimes available");
			model.addAttribute("message2", "Please try any other date.");
			return "buyTicket";
		}

		model.addAttribute("showtimes", showtimes);
		return "buyTicketRedirect";
	}

	@RequestMapping(value = "/confirmBooking",method=RequestMethod.POST)
	public String confirmTicketForward(Model model, HttpServletRequest request) {
		String showtimeId = request.getParameter("showtime");
//		if(showtimeId==null)
//			return "redirect: /buyTicketForward";
		Showtime showtime = showtimeService.getShowtime(Integer
				.parseInt(showtimeId));
		((BookMovieForm) request.getSession().getAttribute("bookMovieForm"))
				.setShowtime(showtime);
		return "confirmBooking";
	}

	@RequestMapping(value = "/customer/bookingPayment")
	public String bookingPayment(Model model, HttpServletRequest request) {
		
		int numberOfTickets =0;
		try{
			numberOfTickets = Integer.parseInt(request.getParameter("numberOfSeats"));
		}catch(NumberFormatException e){}
		BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
				"bookMovieForm");
		form.setNumberOfTickets(numberOfTickets);
		
		if (((UserForm) request.getSession().getAttribute("user")) == null) {
			model.addAttribute("user", new LoginForm());
			return "loginWhilePaying";
		}
		
		double total = form.getShowtime().getTicketPrice() * numberOfTickets;
		model.addAttribute("totalAmount", total);
		model.addAttribute("payment", new Payment());
		return "bookingPayment";
	}

	@RequestMapping(value = "/customer/paymentSuccess",method = RequestMethod.POST)
	public String bookingPaymentSucess(@ModelAttribute @Valid Payment payment,
			BindingResult result, Model model, HttpServletRequest request) {

		PaymentValidator validator= new PaymentValidator();
		validator.validate(payment, result);
		
//		if (result.hasErrors()) {
//			BookMovieForm form= (BookMovieForm) request.getSession().getAttribute("bookMovieForm");
//			double total = form.getShowtime().getTicketPrice() * form.getNumberOfTickets();
//			model.addAttribute("totalAmount", total);
//			System.out.println("error");
//			return "bookingPayment";
//		}
		
		if (result.hasErrors()) {
			request.getSession().setAttribute("errormessage", "Invalid Entries");
			return "redirect:/customer/bookingPayment";
		}
		
		request.getSession().removeAttribute(" message");
		
		String userId = ((UserForm) request.getSession().getAttribute("user"))
				.getEmail();
		User user = userService.getUser(userId);
		BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
				"bookMovieForm");
		form.setPayment(payment);
		// booking, ticket, payment all added in single call
		bookingService.addBooking(form, user);

		// send email to user
		SendEmail.sendEmail(userId, user.getFirstName(), form);
		model.addAttribute("email", userId);
		model.addAttribute("message", "Your booking is confirmed");
		return "bookingPaymentSuccess";
	}

	@RequestMapping(value = "/customer/printTicket", method = RequestMethod.GET)
	public ModelAndView printTcicket(Locale locale, ModelMap model,
			HttpServletRequest request) {

		BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
				"bookMovieForm");

		model.addAttribute("printInfo", form);
		return new ModelAndView(new BookingPdf(), model);
	}
}
