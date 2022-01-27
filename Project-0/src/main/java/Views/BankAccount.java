package Views;

import Utils.ViewManager;

import java.util.Scanner;

class BankAccount {
    // setup instance variables
    float balance;
    float previousTransaction;
    String customerName;
    int customerId;


    //constructor used to initiate customer name and ID
    BankAccount(String cname, int cid){
        customerName = cname;
        customerId = cid;
    }
    // method for depositing into account
    void deposit(float amount){
        if(amount != 0){
            balance = balance + amount;
            previousTransaction = amount;
        }
    }
    //method for withdrawing from account
    void withdraw(float amount){
        if(amount != 0){
            balance = balance - amount;
        }
    }
    //method to display previous transactions
    void getPreviousTransaction(){
        if(previousTransaction > 0){
            System.out.println("Deposited: " + previousTransaction);
        } else {
            if(previousTransaction < 0){
                System.out.println("Withdrawn: " + Math.abs(previousTransaction));
            } else {
                System.out.println("No transaction occurred");
            }
        }
    }
    //shows initial user interaction menu to collect user input
    void showMenu(){
        char option = '\0';
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome " + customerName);
        System.out.println("Your ID is " + customerId);
        System.out.println("\n");
        System.out.println("A. Check Balance");
        System.out.println("B. Deposit");
        System.out.println("C. Withdraw");
        System.out.println("D. Previous transaction");
        System.out.println("E. Exit");

        do{
            System.out.println("==============================================================================");
            System.out.println("Enter an option");
            System.out.println("==============================================================================");
            option = scanner.next().charAt(0);
            System.out.println("\n");
            // switch to opt between user options
            switch(option){
                case 'A':
                    System.out.println("----------------------------------");
                    System.out.println("Balance of $"+balance);
                    System.out.println("----------------------------------");
                    System.out.println('\n');
                    break;

                case 'B' :
                    System.out.println("----------------------------------");
                    System.out.println("Enter amount to deposit");
                    System.out.println("----------------------------------");
                    float amount = scanner.nextFloat();
                    deposit(amount);
                    System.out.println('\n');
                    break;

                case 'C':
                    System.out.println("----------------------------------");
                    System.out.println("Enter amount to withdraw:");
                    System.out.println("----------------------------------");
                    float amount2 = scanner.nextFloat();
                    withdraw(amount2);
                    System.out.println('\n');
                    break;

                case 'D':
                    System.out.println("----------------------------------");
                    getPreviousTransaction();
                    System.out.println("----------------------------------");
                    System.out.println('\n');
                    break;

                case 'E':
                    System.out.println("***********************************");
                    break;

                default:
                    System.out.println("Invalid Option!. Please enter again");
                    break;
            }
        }while(option != 'E');

        System.out.println("Thank you for using our services");
    }




}

