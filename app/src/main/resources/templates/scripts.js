// Number Pad
function insertNumber(digit){
    console.log(digit);
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

}

function depositError(){
    alert("Error! Funds cannot be deposited. Please return to the main menu.");
}

//Withdraw 

function withdrawError(){
    alert("Error! Funds cannot be withdrawn. Please return to the main menu.");
}

function insufficentFunds(){
    alert("Error! Insufficient funds available. Please enter an amount equal or lower than your available balance.");
}