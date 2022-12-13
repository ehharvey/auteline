package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import auteline.BankDatabase;
import auteline.CashDispenser;
import auteline.DepositSlot;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

@Controller
public class MainContoller {

    public BankDatabase bankDatabase = new BankDatabase();
    public CashDispenser cashDispenser = new CashDispenser();
    public DepositSlot depositSlot = new DepositSlot();
    public boolean isAuthenticated = false;
    public int account;

    @RequestMapping(value = "/balance.html", method = RequestMethod.GET)
    public String getBalance(Model model) {

        if (isAuthenticated) {
            String avaliable = "$" + bankDatabase.getAvailableBalance(account);
            String total = "$" + bankDatabase.getTotalBalance(account);

            model.addAttribute("avaliable", avaliable);
            model.addAttribute("total", total);

            return "balance";
        }

        return "login";
    }

    @RequestMapping(value = "/manual-login.html", method = RequestMethod.GET)
    public String getManualLogin(Model model) {
        return "manual-login";

    }

    @RequestMapping(value = "/menu.html", method = RequestMethod.GET)
    public String getMenu(Model model) {
        if (isAuthenticated) {
            return "menu";
        }

        return "login";
    }

    @RequestMapping(value = "/deposit.html", method = RequestMethod.GET)
    public String getDeposit(Model model) {
        if (isAuthenticated) {
            return "deposit";
        }

        return "login";
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String getIndex(Model model) {

        return "index";

    }

    @RequestMapping(value = "/login.html", method = RequestMethod.GET)
    public String getLogin(Model model) {

        return "login";

    }

    @RequestMapping(value = "/withdraw.html", method = RequestMethod.GET)
    public String getwithdraw(Model model) {
        if (isAuthenticated) {
            return "withdraw";
        }

        return "login";
    }

    @RequestMapping(value = "/manual-login", method = RequestMethod.POST)
    public String postManualLogin(@RequestParam(value = "account", required = true) int account,
            @RequestParam(value = "pin", required = true) int pin) {

        if (bankDatabase.authenticateUser(account, pin)) {
            this.account = account;
            isAuthenticated = true;
            return "menu";
        }
        return "index";
    }

    @RequestMapping(value = "/deposit", method = RequestMethod.POST)
    public String deposit(@RequestParam(value = "deposit", required = true) int amount) {

        if (isAuthenticated) {
            bankDatabase.credit(account, amount);
            System.out.println(bankDatabase.getTotalBalance(account));
            return "menu";

        }
        return authenicationError();
    }

    @RequestMapping(value = "/withdraw", method = RequestMethod.POST)
    public String withdraw(@RequestParam(value = "amount", required = true) int amount, Model model) {
        String message;

        if (isAuthenticated) {

            double avalBalance = bankDatabase.getAvailableBalance(account);
            if (avalBalance >= amount) {
                if (cashDispenser.isSufficientCashAvailable(amount)) {
                    bankDatabase.debit(account, amount);
                    System.out.println(bankDatabase.getTotalBalance(account));
                    return "menu";
                }else{
                    message = "Error: Cash Dispenser does not have enough funds.";
                }
            }else{
                message = "Error: Insufficent funds in your account. Please choose a different amount.";
            }

            model.addAttribute("transactionMessage", message);
            return "withdraw";
        }
        return authenicationError();
    }

    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public String logout() {
        if (isAuthenticated) {
            return "index";

        }
        return authenicationError();
    }

    private String authenicationError() {

        account = 0;
        isAuthenticated = false;
        return "login";
    }

}
