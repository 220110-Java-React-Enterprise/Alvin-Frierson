package Views;
import Persistence.AccountModel;
import Persistence.AccountRepo;
import Persistence.AssociateModel;
import Utils.ViewManager;


import java.io.IOException;
import java.sql.SQLException;


/**
 * Create an Account view using the view manager
 * */
public class AccountView extends View{
    public AccountView(){
        viewName = "account";
        viewManager = ViewManager.getViewManager();
    }


    // override for the renderView method of the View super class
    @Override
    public void renderView() throws SQLException, IOException {
        float balance;

        AssociateModel am = AssociateModel.getUserModel();
        AccountModel acctModel = new AccountModel(null, 0, am.getUserId());

            System.out.printf("Welcome %s, what would you like to do?\n", am.getFirstName());

        System.out.println(
                "========================\n" +
                "1. Create an Account\n" +
                "2. View Account\n" +
                "========================\n");

        String input = viewManager.getScanner().nextLine();

        switch (input){
            case "1" :
                AccountRepo newAccount = new AccountRepo();
                System.out.println("Your new account has been created! Press option 2 to view your account.");
                renderView();
                newAccount.create(acctModel);
                break;
            case "2" :
                BankAccount newBankAccount = new BankAccount(am.getFirstName(), am.getAccountId());
                newBankAccount.showMenu();
                break;
            default:
                System.out.println("Try again!");
                break;
        }



        viewManager.quit();
//         class BankAccount{
//            // setup instance variables
//            int balance;
//            int previousTransaction;
//            String customerName;
//            int customerId;
//
//            public BankAccount(){
//
//            }
//
//            //constructor used to initiate customer name and ID
//            public BankAccount(String cname, int cid){
//                customerName = cname;
//                customerId = cid;
//            }
//            // method for depositing into account
//            void deposit(int amount){
//                if(amount != 0){
//                    balance = balance + amount;
//                    previousTransaction = amount;
//                }
//            }
//            //method for withdrawing from account
//            void withdraw(int amount){
//                if(amount != 0){
//                    balance = balance - amount;
//                }
//            }
//            //method to display previous transactions
//            void getPreviousTransaction(){
//                if(previousTransaction > 0){
//                    System.out.println("Deposited: " + previousTransaction);
//                } else {
//                    if(previousTransaction < 0){
//                        System.out.println("Withdrawn: " + Math.abs(previousTransaction));
//                    } else {
//                        System.out.println("No transaction occurred");
//                    }
//                }
//            }
//            //shows initial user interaction menu to collect user input
//            void showMenu(){
//                char option = '\0';
//                Scanner scanner = new Scanner(System.in);
//
//                System.out.println("Welcome " + customerName);
//                System.out.println("Your ID is " + customerId);
//                System.out.println("\n");
//                System.out.println("A. Check Balance");
//                System.out.println("B. Deposit");
//                System.out.println("C. Withdraw");
//                System.out.println("D. Previous transaction");
//                System.out.println("E. Exit");
//
//                do{
//                    System.out.println("==============================================================================");
//                    System.out.println("Enter an option");
//                    System.out.println("==============================================================================");
//                    option = scanner.next().charAt(0);
//                    System.out.println("\n");
//                    // switch to opt between user options
//                    switch(option){
//                        case 'A':
//                            System.out.println("----------------------------------");
//                            System.out.println("Balance = "+balance);
//                            System.out.println("----------------------------------");
//                            System.out.println('\n');
//                            break;
//
//                        case 'B' :
//                            System.out.println("----------------------------------");
//                            System.out.println("Enter amount to deposit");
//                            System.out.println("----------------------------------");
//                            int amount = scanner.nextInt();
//                            deposit(amount);
//                            System.out.println('\n');
//                            break;
//
//                        case 'C':
//                            System.out.println("----------------------------------");
//                            System.out.println("Enter amount to withdraw:");
//                            System.out.println("----------------------------------");
//                            int amount2 = scanner.nextInt();
//                            withdraw(amount2);
//                            System.out.println('\n');
//                            break;
//
//                        case 'D':
//                            System.out.println("----------------------------------");
//                            getPreviousTransaction();
//                            System.out.println("----------------------------------");
//                            System.out.println('\n');
//                            break;
//
//                        case 'E':
//                            System.out.println("***********************************");
//                            break;
//
//                        default:
//                            System.out.println("Invalid Option!. Please enter again");
//                            break;
//                    }
//                }while(option != 'E');
//
//                System.out.println("Thank you for using our services");
//            }
//
//
//        }
    }
}
