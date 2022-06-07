package com.example.myapplication.data.api

object ApiRoutes {
    const val baseurl="https://ribuiuat.icicibank.com"
    const val encKey="7061737323313233"
    const val bankID="ICI"
    const val currency="INR"

    const val  bankIConUrl = "$baseurl/assets/img/bank_logos/"
    const val login="loginservice"
    const val netBalance="dashboardAPI/netBalance"
    const val oneTimeFT="fundtransfer/oneTimeFundsTransfer"
    const val impsIfscFundTransfer="fundtransfer/impsIfscFundTransfer"
    const val ftCheckBalance="fundtransfer/checkBalanceList"
    const val recentTransactions="fundtransfer/recentTransactions"
    const val ftPayeeList="fundtransfer/ftPayeeList"
    const val scheduleTransaction="payeeaccount/scheduleTransactionList"
    const val completedTransaction="fundtransfer/completedTransactionList"
    const val getBankBranchDetails="payeeaccount/getBankBranchDetails"
    const val codeReference="depositAPI/codeReference"
    const val bankAccountList="bankaccount/bankAccountList"
    const val accountDetail="fundtransfer/debitAccountList"
    const val iciciQuickFT="fundtransfer/iciciQuickFT"
}