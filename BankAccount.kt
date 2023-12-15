package com.example.bankaccountprogram

class BankAccount (var name:String, var balanceAmt:Double=0.0){
    private var accountNumber = ""
    private val transactionHistory = mutableListOf<Map<String, String>>()
//    better way is to define nested data class for transact history
    private data class transactionHistoryClass(
        var type: String,
        var amount: Double
    ){
        override fun toString(): String {
            return "type=$type\n amount=$amount\n"
        }
    }
    private val transactionHistory_ClassList = mutableListOf<transactionHistoryClass>()

    init{
        generateAccNumber()
    }
    private fun generateAccNumber(){
        val Alphabets='A'..'Z'
        val Numbers = '0'..'9'
        accountNumber=""
        for (i in 1..4){
            accountNumber+=Alphabets.random()
        }
        for (i in 1..4){
            accountNumber+=Numbers.random()
        }
    }

    fun getDetails(){
        println("Bank Account Details:\n" +
                "Name of Acc Holder: $name\n" +
                "Account Number: $accountNumber\n" +
                "Balance Amt: $balanceAmt\n")
    }
    fun deposit(depMoney:Double){
        balanceAmt+=depMoney
        println("$depMoney/- deposited\n" +
                "New Balance: $balanceAmt\n");

//        update history
        {
            val transactionDetails = mutableMapOf<String, String>(
                "Type" to "deposit",
                "Amount" to "$depMoney"
            )
            transactionHistory.add(transactionDetails)
        }

//        with data class
        val transaction = transactionHistoryClass("Deposit", depMoney)
        transactionHistory_ClassList.add(transaction)
    }
    fun withdraw(amount:Double){
        if(amount > balanceAmt){
            println("Cannot withdraw $amount/-" +
                    "balance amount has ${amount-balanceAmt}/- " +
                    "lesser than required withdrawal amount \n")
        }
        else {
            balanceAmt -= amount
            println(
                "$amount/- withdrew\n" +
                        "New Balance: $balanceAmt"
            );

//        update history
            {
                val transactionDetails = mutableMapOf<String, String>(
                    "Type" to "withdraw",
                    "Amount" to "$amount"
                )
                transactionHistory.add(transactionDetails)
            }
            //        with data class
            val transaction = transactionHistoryClass("Withdraw", amount)
            transactionHistory_ClassList.add(transaction)
        }
    }

    fun getTransactionHistory(){
        /*println("Transaction History:")
        for (i in transactionHistory){
            for ( j in i){
                println(j)
            }
            println()
        }*/
//        with data class
        println("Transaction History:")
        for (i in transactionHistory_ClassList){
            println(i.toString())
        }
    }

}