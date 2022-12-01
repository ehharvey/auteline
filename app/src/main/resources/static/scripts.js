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