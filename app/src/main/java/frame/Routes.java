package frame;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {

	@GetMapping("/")
	public String index() {
		return "This is the index page";
	}

	@GetMapping("/login")
	public String login() {
		return "This is the login page";
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