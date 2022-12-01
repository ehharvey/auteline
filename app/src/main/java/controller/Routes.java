package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Routes {

    @GetMapping("/login")                     // it only support port method
    public String saveDetails(@RequestParam("account") int account, @RequestParam("pin") int pin) {
		
		// TODO account verifiaction with database
		
		if(account == 1234 && pin == 4321)
        	return "<script>location.href='menu.html'</script>";           
        return "<script>alert('Incorrect Credentials');location.href='login.html';</script>";           
    }

	@GetMapping("/login?account=<int>&pin=<int>")
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