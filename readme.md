# Case Study

### PayPal

#### Payment Management System

A simple payment management system, inspired by PayPal, in the exact language in which the fin-tech giant started out.

A few classes that are encapsulated for the project:

1. `Currency`: A custom interface that defines and holds the data about the currency, like the:

   i. `currencyCode` (String): The international code used to identify the currency, like for eg. INR for Indian Rupee

   ii. `currencySymbol` (String): The international symbol used along with the currency like ₹ for Indian Rupee

   iii. `rupeeEquivalent` (double): The present-day Indian Rupee equivalent of the currency

2. `CardType` is an enum class which differentiates if the card is debit (`.DEBIT`) or credit (`.CREDIT`)

3. `Card`: A custom class that holds confidential information regarding a banking card used for online transactions. It orchestrates the following details:

   i. `cardNumber` (String): The sixteen digit card number passed as a text String object

   ii. `cardHolderName` (String): The card holders name

   iii. `expiryDate` (String): The expiry date of the banking card in the format (MM/YYYY)

   iv. `cvv` (String): The 3 digit Card Verification Value of the banking card (Confidential)

   v. `cardType` (CardType): Indicates whether the card is a debit card or a credit card

4. `Bank`: A custom class that stores the publicly available details of a bank, that includes the following:

   i. `bankName` (String): The public registered name of the entity bank

   ii. `ifscCode` (String): The Indian Financial System code of the operating bank, used to instantiate transactions

   iii. `branchName` (String): The name of the branch of the bank entity is operating from

5. `User`: A custom class that encapsulates the details of the user who has logged in.

   i. `id` (String): The uniquely identified username of the user

   ii. `name` (String): The official name of the user, as per governement ID proof

   iii. `email` (String): The verififed email address of the user, used for all official communication.

   iv. `password` (String): The password used for logging into the service, created by the user at the time of account opening

   v. `bank` (Bank): The bank account of the user that is linked to his/her/their PayPal account, and is to be used for all transactions

   vi. `accountNumber` (String): The bank account number of the linked bank account

   vii. `balance` (double): The current available balance of the user (in currency units)

   viii. `currency` (Currency): The currency in which the specified user operates.

   ix. `card` (Card): The banking card linked to the user's bank and PayPal account, to be used for transactional purposes

6. Payment: a basic class to encapsulate the transaction related properties, methods and functions.

   i. `paymentId` (String): A random unique identifier (UUID) that is used to tag a particular transaction

   ii. `amount` (double): the actual amount to be transferred from the sender to the recepient, to be entered in the currency (Currency)

   iii. `currency` (Currency): the currency being used to handle the amount, used by the sender

   iv. `convertedCurrency` (Currency): the currency being used by the recepient

   v. `recepient` (User): the user who is recieving the funds, i.e, the user whose bank account is going to credited with the funds in the `convertedCurrency`

   vi. `sender` (User): the user who is sending the funds, i.e, the user whose bank account is going to debited with the funds in the `currency`

   vii. `approved` (boolean): a boolean flag to identify is the transaction is compliant
