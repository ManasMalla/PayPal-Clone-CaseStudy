import java.util.*;

class Currency {
    String currencyCode;
    String currencySymbol;
    double rupeeEquivalent;

    public Currency(String currencyCode, String currencySymbol, double rupeeEquivalent) {
        this.currencyCode = currencyCode;
        this.currencySymbol = currencySymbol;
        this.rupeeEquivalent = rupeeEquivalent;
    }
}

enum CardType {
    DEBIT, CREDIT
}

class Card {
    String cardNumber;
    String cardHolderName;
    String expiryDate;
    String cvv;
    CardType cardType;

    public Card(String cardNumber, String cardHolderName, String expiryDate, String cvv, CardType cardType) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expiryDate = expiryDate;
        this.cvv = cvv;
        this.cardType = cardType;
    }
}

class Bank {
    String bankName;
    String ifscCode;
    String branchName;

    public Bank(String bankName, String ifscCode, String branchName) {
        this.bankName = bankName;
        this.ifscCode = ifscCode;
        this.branchName = branchName;
    }

}

class User {
    String id;
    String name;
    String email;
    String password;
    Bank bank;
    String accountNumber;
    double balance;
    Currency currency;
    Card card;

    public User(String id, String password, String name, String email, Card card, Bank bank, String accountNumber,
            double balance,
            Currency currency) {
        this.id = id;
        this.password = password;
        this.name = name;
        this.email = email;
        this.card = card;
        this.bank = bank;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.currency = currency;
    }
}

class Payment {
    String paymentId;
    double amount;
    Currency currency;
    Currency convertedCurrency;
    User recepient;
    User sender;

    public Payment(String paymentId, User recepient, double amount, User sender) {
        this.paymentId = paymentId;
        this.amount = amount;
        this.currency = sender.currency;
        this.recepient = recepient;
        this.sender = sender;
        this.convertedCurrency = recepient.currency;
    }

    public void initiateTransaction(TransactionCallback callback) {
        System.out.println(paymentId + " initiated for " + amount + " " + currency.currencyCode + " from "
                + sender.name + " to " + recepient.name);
        System.out.println("Proccess transaction (Y/N): ");
        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");
        String choice = sc.next();
        if (choice.equals("Y") || choice.equals("y")) {
            System.out.println("Transaction successful");
            sender.balance -= amount;
            recepient.balance += amount;
            System.out.println("Amount debited from " + sender.name + " account number " + sender.accountNumber
                    + " with " + amount + " " + currency.currencyCode);
            System.out.println("Amount credited to " + recepient.name + " account number " + recepient.accountNumber
                    + " with " + (amount * currency.rupeeEquivalent) + " " + convertedCurrency.currencyCode);
            callback.onSuccess(this);
        } else {
            System.out.println("Transaction cancelled");
            callback.onFailure(new Exception("Transaction cancelled"));
        }
    }
}

/**
 * TransactionCallback
 */
interface TransactionCallback {

    void onSuccess(Payment token);

    void onFailure(Exception exception);

}

public class PaymentManagementSystem {

    public static void clearConsole() {
        try {
            final String os = System.getProperty("os.name");
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                Runtime.getRuntime().exec("clear");
            }
        } catch (final Exception e) {
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Currency usd = new Currency("USD", "$", 74.5);
        Currency eur = new Currency("EUR", "€", 88.5);
        Currency gbp = new Currency("GBP", "£", 102.5);
        Currency jpy = new Currency("JPY", "¥", 0.67);
        Currency inr = new Currency("INR", "₹", 1);

        Bank bank1 = new Bank("citi Bank", "CITI001", "United States of America");
        Bank bank2 = new Bank("Women's Bank", "FEM002", "Vizag");
        Bank bank3 = new Bank("SBI Bank", "SBI003", "Hyderabad");
        Bank bank4 = new Bank("Fi Bank", "FED006", "Delhi");
        Bank bank5 = new Bank("ICICI Bank", "ICICI005", "Mumbai");
        Bank bank6 = new Bank("Union Bank Of India", "UBIN004", "Bangalore");
        Bank bank7 = new Bank("Axis Bank", "AXIS007", "Chennai");
        Bank bank8 = new Bank("Kotak Mahindra Bank", "KOTAK008", "Pune");
        Bank bank9 = new Bank("Karur Vysya Bank", "KVBC009", "Hyderabad");

        Card card1 = new Card("1234 5678 9012 3456", "Manas Malla", "12/25", "123", CardType.DEBIT);
        Card card2 = new Card("1176 1111 7552 8921", "Geethika Chadaram", "12/25", "123", CardType.CREDIT);
        Card card3 = new Card("1234 5678 9012 3456", "Akhila Ravipati", "12/25", "123", CardType.DEBIT);
        Card card4 = new Card("9876 5432 1098 7654", "Lochan Mathukumilli", "12/25", "123", CardType.CREDIT);
        Card card5 = new Card("1234 5678 9012 3456", "Satwik Varma", "12/25", "123", CardType.DEBIT);
        Card card6 = new Card("9876 5432 1098 7654", "Sree Teja Dusi", "12/25", "123", CardType.CREDIT);
        Card card7 = new Card("1234 5678 9012 3456", "Ujwal Chowdary", "12/25", "123", CardType.DEBIT);
        Card card8 = new Card("9876 5432 1098 7654", "Sidharth Philkhana", "12/25", "123", CardType.CREDIT);
        Card card9 = new Card("1234 5678 9012 3456", "Kavya Chandana", "12/25", "123", CardType.DEBIT);

        User user1 = new User("manasmalla", "aarogya@041004", "Manas Malla", "manas@google.com", card1, bank1,
                "1234567890", 10000, usd);
        User user2 = new User("geethikachadaram", "Geethika@1176", "Geethika Mahitha Chadaram", "geethika@theananta.in",
                card2, bank2, "1176691111", 15000, eur);
        User user3 = new User("akhilaravipati", "akhila@1108", "Akhila Ravipati", "akhila@theananta.in", card3, bank3,
                "7207703408", 20000, gbp);
        User user4 = new User("lochanmathukumilli", "chowdary@556", "Lochan Mathukumilli", "neekuenduku@theananta.in",
                card4, bank4, "3001239493", 25000, jpy);
        User user5 = new User("satwikv", "satwik@123", "Satwik Varma", "satwik@theananta.in", card5, bank5,
                "7569672800", 2000, gbp);
        User user6 = new User("sreetejadusi", "sreeteja@123", "Sree Teja Dusi", "sreeteja@theantanta.in", card6, bank6,
                "630556551", 10000, jpy);
        User user7 = new User("ujwalchowdary", "ujwal@0570", "Ujwal Chowdary", "ujwal@microsoft.com", card7, bank7,
                "4398420943", 5000, inr);
        User user8 = new User("sphilkha", "jaishreekrishna", "Sidharth Philkhana", "sidharth@google.com", card8, bank8,
                "8545849584", 10000, eur);
        User user9 = new User("kavyachandana", "kavya@123", "Kavya Chandana", "kavya@apple.com", card9, bank9,
                "3280248548", 500, jpy);
        User[] users = { user1, user2, user3, user4, user5, user6, user7, user8, user9 };

        ArrayList<Payment> transactions = new ArrayList<Payment>();

        System.out.println("Welcome to PayPal. \nLogin: ");

        boolean isUsernameMatched = false;
        int passwordMisMatchedAttempts = 3;
        String username = "";
        User selectedUser = null;

        Scanner sc = new Scanner(System.in);
        sc.useDelimiter("\n");

        while (!isUsernameMatched) {
            System.out.print("Username: ");
            String usernameInput = sc.next();
            if (!Arrays.asList(users).stream().filter((user) -> user.id.equals(usernameInput)).findFirst()
                    .isPresent()) {
                System.out.println("Oops. We don't seem to find you on our database. Recheck your username.");
            } else {
                isUsernameMatched = true;
                username = usernameInput;
            }
        }

        while (passwordMisMatchedAttempts > 0) {
            String finalUsername = username;
            User tempUser = Arrays.asList(users).stream().filter((user) -> user.id.equals(finalUsername)).findFirst()
                    .get();
            System.out.print("Password: ");
            String password = sc.next();
            if (!tempUser.password.equals(password)) {
                System.out.println(
                        "Oops! Looks like your password took a detour through the land of typos. Please try again.");
                passwordMisMatchedAttempts--;
                if (passwordMisMatchedAttempts == 0) {
                    System.out.println("You have exceeded the maximum number of attempts. Please try again later.");
                    System.exit(0);
                }
            } else {
                selectedUser = tempUser;
                break;
            }
        }

        clearConsole();
        System.out.println("===========================\n\n\n\n\n\n\n\n\n\n");

        System.out.println("Welcome " + selectedUser.name + "!");
        initiateUserInteraction(selectedUser, sc, users, transactions);

        // Payment payment = new Payment("6196de26cce54d2c8cfc4bc05074c2e", user2, 500,
        // user1);
        // payment.initiateTransaction();

    }

    static void initiateUserInteraction(User selectedUser, Scanner sc, User[] users, ArrayList<Payment> transactions) {
        System.out.println("\n1. Instantiate Transaction");
        System.out.println("2. View Transaction History");
        System.out.println("3. View Account Details");
        System.out.println("4. View Card Details");
        System.out.println("5. View Bank Details");
        System.out.println("6. View Balance");
        System.out.println("7. View Currency Details");
        System.out.println("8. Logout\n");
        System.out.print("Enter your choice: ");

        try {
            int choice = sc.nextInt();
            switch (choice) {
                case 1:
                    User recepient = null;
                    while (recepient == null) {
                        System.out.println("\nEnter recepient username: ");
                        String recepientUsername = sc.next();
                        if (recepientUsername.equals(selectedUser.id)) {
                            System.out.println(
                                    "You cannot send money to yourself. Please enter a valid recepient username.");
                            continue;
                        } else if (!Arrays.asList(users).stream().filter((user) -> user.id.equals(recepientUsername))
                                .findFirst().isPresent()) {
                            System.out.println("\nCouldn't find your friend/family there. Do you need help?");
                            System.out.println("If you do need help, type 'help'. Else, try again.");
                            String help = sc.next();
                            if (help.equals("help")) {
                                System.out.println("\nHere are the list of users: \n");
                                Arrays.asList(users).stream().forEach((user) -> System.out.println(user.id));
                            }
                        } else {
                            recepient = Arrays.asList(users).stream()
                                    .filter((user) -> user.id.equals(recepientUsername))
                                    .findFirst().get();
                        }
                    }
                    System.out.println("\nEnter amount: ");
                    double amount = sc.nextDouble();
                    Payment payment = new Payment(UUID.randomUUID().toString(), recepient, amount, selectedUser);
                    payment.initiateTransaction(new TransactionCallback() {
                        @Override
                        public void onSuccess(Payment token) {
                            transactions.add(token);
                        }

                        @Override
                        public void onFailure(Exception exception) {
                            System.out.println(exception.getMessage());
                        }
                    });
                    break;
                case 2:
                    System.out.println("\nTransaction History: \n");
                    transactions.stream().forEach((transaction) -> {
                        System.out.println("Transaction ID: " + transaction.paymentId);
                        System.out.println("Amount: " + transaction.amount + " " + transaction.currency.currencyCode);
                        System.out.println("Sender: " + transaction.sender.name + " (" + transaction.sender.id + ")");
                        System.out.println("Sender Account Number: " + transaction.sender.accountNumber);
                        System.out.println("Recepient: " + transaction.recepient.name + " (" + transaction.recepient.id
                                + ")");
                        System.out.println("Recepient Account Number: " + transaction.recepient.accountNumber);
                        System.out.println(
                                "Credited Amount: " + (transaction.amount * transaction.currency.rupeeEquivalent)
                                        + " " + transaction.convertedCurrency.currencyCode);
                        System.out.println("\n");
                    });
                    break;
                case 3:
                    System.out.println("\nAccount Details: \n");
                    System.out.println(selectedUser.name);
                    System.out.println("@" + selectedUser.id);
                    System.out.println("Email: " + selectedUser.email);

                    // System.out.println("Account Number: " + selectedUser.accountNumber);
                    break;
                case 4:
                    System.out.println("\n-----------------------------------------");
                    System.out.print("| " + selectedUser.bank.bankName);
                    for (int a = 0; a < 38 - selectedUser.bank.bankName.length(); a++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    System.out.println("| " + selectedUser.card.cardType
                            + (selectedUser.card.cardType == CardType.DEBIT ? " Card                            |"
                                    : " Card                           |"));
                    System.out.println("|                                       |");
                    System.out.println("| " + selectedUser.card.cardNumber + "                   |");
                    System.out.println("|                                       |");
                    System.out.println("| CVV: " + selectedUser.card.cvv + "         " + "Valid Upto: "
                            + selectedUser.card.expiryDate + "    |");
                    System.out.print("| " + selectedUser.card.cardHolderName);
                    for (int a = 0; a < 38 - selectedUser.card.cardHolderName.length(); a++) {
                        System.out.print(" ");
                    }
                    System.out.println("|");
                    System.out.println("-----------------------------------------");
                    break;
                case 5:
                    System.out.println("\nBank Details: ");
                    System.out.println("Account Number: " + selectedUser.accountNumber);
                    System.out.println("Bank Name: " + selectedUser.bank.bankName);
                    System.out.println("IFSC Code: " + selectedUser.bank.ifscCode);
                    System.out.println("Branch Name: " + selectedUser.bank.branchName);
                    break;
                case 6:
                    System.out.println("\nBalance: " + selectedUser.balance + " " + selectedUser.currency.currencyCode);
                    break;
                case 7:
                    System.out.println("\nCurrency Details: ");
                    System.out.println("Currency Code: " + selectedUser.currency.currencyCode);
                    System.out.println("Currency Symbol: " + selectedUser.currency.currencySymbol);
                    System.out.println("Rupee Equivalent: " + selectedUser.currency.rupeeEquivalent);
                    break;
                case 8:
                    System.out.println("Logging out...\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
            initiateUserInteraction(selectedUser, sc, users, transactions);
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
        }
    }
}