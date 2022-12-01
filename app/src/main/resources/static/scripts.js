

// Number Pad
function insertNumber(digit){
    var textbox = document.getElementById("input");
    textbox.value += digit;
}

function clearInput(){
    var textbox = document.getElementById("input");
    textbox.value = "";

}

// Deposit

//TODO deposit funds
function depositFunds(){
    alert("Help!");
}

function depositError(){
    alert("Error! Funds cannot be deposited. Please return to the main menu.");
}

function depositSuccess(){
    alert("Please place your deposit items into the deposit slot.")
}

//Withdraw 

function withdrawError(){
    alert("Error! Funds cannot be withdrawn. Please return to the main menu.");
}

function insufficientFunds(){
    alert("Error! Insufficient funds available. Please enter an amount equal or lower than your available balance.");
}

function withdrawSuccess(){
    alert("Success! Please take your withdraw funds from the slot.")
}

// Login

function loginManual(){
    console.log(1);
    var account = document.getElementById("account");
    var pin = document.getElementById("pin");
    location.href = '/login?account=' + account.value + '&pin=' + pin.value;

}