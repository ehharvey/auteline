package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import auteline.BankDatabase;

@RestController
public class Routes {

	BankDatabase bankDatabase = new BankDatabase();
	boolean isAuthenticated = false;
	int account;

    @PostMapping("/manual-login")                     
    public String manualLogin(@RequestParam("account") int account, @RequestParam("pin") int pin) {
		
		if(bankDatabase.authenticateUser(account, pin)){
			this.account = account;
			this.isAuthenticated = true;
        	return "<script>location.href='menu.html'</script>";  
		}         
        return "<script>alert('Incorrect Credentials');location.href='login.html';</script>";           
    }

	@PostMapping("/deposit")
	public String deposit(@RequestParam("deposit") int amount) {

		if(isAuthenticated){
			bankDatabase.credit(this.account, amount); 
			return "<script>alert('Money desposited');location.href='menu.html';</script>";

		}
			return authenicationError();
	} 

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam("amount") int amount){

		if(isAuthenticated){
			bankDatabase.debit(this.account, amount); 
			return "<script>alert('Money withdrawn');location.href='menu.html';</script>";

		}
			return authenicationError();
	}

	@PostMapping("/logout")
	public String logout(){
		if(isAuthenticated){
			return "<script>alert('Logging out');location.href='index.html';</script>";

		}
			return authenicationError();
	}

	String authenicationError(){

		this.account = 0;
		this.isAuthenticated = false;
		return "<script>alert('Error');location.href='login.html';</script>";
	}

}