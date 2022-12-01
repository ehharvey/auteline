package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {

    // @RequestMapping("/")
    // public ModelAndView welcome() {
    //     ModelAndView modelAndView = new ModelAndView();
    //     modelAndView.setViewName("login.html");
    //     return modelAndView;
    // } 

    // @GetMapping("/")
    // public String index() {
    //     return "index.html";
    // }

	@GetMapping("/")
	public String landing() {
		return "index";
	}

    @GetMapping("/login-m")
	public String manualLogin() {
		return "This is the manual login page";
        
	}

    @GetMapping("/menu")
	public String menu() {
		return "This is the menu page";
        
	}

    @GetMapping("/balance")
	public String balance() {
		return "This is the balance page";
        
	}

    @GetMapping("/withdraw")
	public String withdraw() {
		return "This is the withdraw page";
        
	}

    @GetMapping("/deposit")
	public String deposit() {
		return "This is the deposit page";
        
	}
    

}