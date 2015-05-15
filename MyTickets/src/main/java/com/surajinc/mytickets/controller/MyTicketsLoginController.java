package com.surajinc.mytickets.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.surajinc.mytickets.exception.AccountNotFoundException;
import com.surajinc.mytickets.exception.UserExistsException;
import com.surajinc.mytickets.form.BookMovieForm;
import com.surajinc.mytickets.form.LoginForm;
import com.surajinc.mytickets.form.UserForm;
import com.surajinc.mytickets.pojo.Bookings;
import com.surajinc.mytickets.pojo.MovieShowing;
import com.surajinc.mytickets.pojo.Payment;
import com.surajinc.mytickets.service.BookingService;
import com.surajinc.mytickets.service.CityService;
import com.surajinc.mytickets.service.MovieShowingService;
import com.surajinc.mytickets.service.UserService;
import com.surajinc.mytickets.validator.LoginValidator;
import com.surajinc.mytickets.validator.UserValidator;

@Controller
public class MyTicketsLoginController {

	@Autowired
	UserService userService;
	
	@Autowired
	MovieShowingService movieShowingService;

	@Autowired
	CityService cityService;
	
	@Autowired
	BookingService bookingService;
	
	private static final String DEFAULT_ROLE="customer";
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<MovieShowing> movieCurrentList= movieShowingService.listCurrentMovies();
		List<MovieShowing> movieUpcomingList= movieShowingService.listUpcomingMovie();
		String movieNameList= "";
		for (int i=0;i<movieCurrentList.size();i++){
			movieNameList += movieCurrentList.get(i).getMovie().getName()+", ";
		}
		model.addAttribute("currentMovieList", movieCurrentList);
		model.addAttribute("upcomingMovieList", movieUpcomingList);
		model.addAttribute("movieNameList", movieNameList);
		return "home";
	}
	
	@RequestMapping(value = "/logout")
	public String logoutForward(Model model, HttpServletRequest request) {
		request.getSession().invalidate();
		LoginForm user = new LoginForm();
		model.addAttribute("user", user);
		return "loginView";
	}
	
	@RequestMapping(value = "/login")
	public String loginForward(Model model) {
		LoginForm user = new LoginForm();
		model.addAttribute("user", user);
		return "loginView";
	}
	
	@RequestMapping("/createAccount")
	public String createAccountForward(Model model) {
		UserForm user = new UserForm();
		model.addAttribute("user", user);
		return "createAccount";
	}

	@RequestMapping(value = "/loginUser")
	public String loginUser(@Valid @ModelAttribute("user") LoginForm loginForm,
			BindingResult result, Model model, HttpServletRequest request) {
		
		LoginValidator validator= new LoginValidator();
		validator.validate(loginForm, result);
		if(result.hasErrors())
			return "loginView";
		try{
		UserForm getUser= userService.authenticateUser(loginForm);
		model.addAttribute("user", getUser);
		request.getSession().setAttribute("user", getUser);
		if(getUser.getRole().equals(DEFAULT_ROLE))
		 	{
				List<Bookings> bookingList=  bookingService.listBooking(getUser.getEmail());
				model.addAttribute("bookingList", bookingList);
				return "userDash";
		 	}
		else {
			model.addAttribute("cityList",cityService.listCity());
			return "admin/adminDash";
		}
		}catch(AccountNotFoundException e){
			model.addAttribute("message", "Please check credentials!");
			return "loginView";
		}
	}
	
	@RequestMapping(value = "/loginUserWhilePaying")
	public String loginUserWhilePaying(@Valid @ModelAttribute("user") LoginForm loginForm,
			BindingResult result, Model model, HttpServletRequest request) {
		
		LoginValidator validator= new LoginValidator();
		validator.validate(loginForm, result);
		if(result.hasErrors()){
			return "loginWhilePaying";
			}
		try{
		UserForm getUser= userService.authenticateUser(loginForm);
		model.addAttribute("user", getUser);
		request.getSession().setAttribute("user", getUser);
		if(getUser.getRole().equals(DEFAULT_ROLE))
		 	{
				BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
						"bookMovieForm");
				double total = form.getShowtime().getTicketPrice() * form.getNumberOfTickets();
				model.addAttribute("totalAmount", total);
				model.addAttribute("payment", new Payment());
				return "bookingPayment";
		 	}
		else {
			 throw new AccountNotFoundException("Invalid Accessibility");
		}
		}catch(AccountNotFoundException e){
			model.addAttribute("message", "Please check credentials!");
			return "loginWhilePaying";
		}
	}

	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
	public String createUser(@Valid @ModelAttribute("user") UserForm userForm,
			BindingResult result, Model model, HttpServletRequest request) {
		
		UserValidator validator= new UserValidator();
		validator.validate(userForm, result);
		
		if(result.hasErrors())
			return "createAccount";
		
			userForm.setRole(DEFAULT_ROLE);
		try {
			userService.checkUser(userForm);
		} catch (UserExistsException e) {
			model.addAttribute("errormessage", "User Already Exists!");
			return "createAccount";
		}
			
			
			userService.addOrUpdateUser(userForm);
			request.getSession().setAttribute("user", userForm);
			model.addAttribute("message", "Your Account is created Successfully!");
			return "userCreatedSuccess";
	}
	
	@RequestMapping(value = "/createUserForward")
	public String createUserForward( Model model) {
		model.addAttribute("user", new UserForm());
		return "signUpWhilePaying";
	}
	
	
	@RequestMapping(value = "/createUserWhilePaying", method = RequestMethod.POST)
	public String createUserWhilePaying(@Valid @ModelAttribute("user") UserForm userForm,
			BindingResult result, Model model, HttpServletRequest request) {
		
		if(result.hasErrors())
			return "signUpWhilePaying";
		try{
			userService.checkUser(userForm);
			userForm.setRole(DEFAULT_ROLE);
			userService.addOrUpdateUser(userForm);
			request.getSession().setAttribute("user", userForm);
			
			BookMovieForm form = (BookMovieForm) request.getSession().getAttribute(
					"bookMovieForm");
			double total = form.getShowtime().getTicketPrice() * form.getNumberOfTickets();
			model.addAttribute("totalAmount", total);
			model.addAttribute("payment", new Payment());
			model.addAttribute("accountCreatedMessage", "Your Account is created Successfully!");
			return "bookingPayment";
		}catch(UserExistsException e){
			model.addAttribute("message", "User Already Exists!");
			return "loginWhilePaying";
		}
	}
	
	@RequestMapping(value = "/userDash")
	public String userDashForward(Model model, HttpServletRequest request) {
		
		UserForm getUser =(UserForm) request.getSession().getAttribute("user");
		List<Bookings> bookingList=  bookingService.listBooking(getUser.getEmail());
		model.addAttribute("bookingList", bookingList);
		return "userDash";
	}
	
	@RequestMapping(value = "/adminDash")
	public String adminDash(Model model, HttpServletRequest request) {
		
		model.addAttribute("cityList",cityService.listCity());
		return "admin/adminDash";
	}
	
	@RequestMapping(value = "/help")
	public String helpForward(Model model) {
		return "helpView";
	}
	
	@RequestMapping(value = "/contactUs")
	public String contactUs(Model model) {
		return "contactUsView";
	}
	
//	 @InitBinder(value = "user")
//	 protected void initBinder(WebDataBinder binder) {
//	  binder.setValidator(new UserValidator());
//	 }
}
