package com.example.bankaccountprogram

fun main(){
//    testing

    var david = BankAccount("David Becham")
//    david.getDetails()

    david.deposit(100.0)
    david.withdraw(110.0)
    david.withdraw(20.0)
    david.getTransactionHistory()
}

