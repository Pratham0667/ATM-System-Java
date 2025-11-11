/*
===============================================================
                  PROJECT DOCUMENTATION
===============================================================
Project Name   : ATM System Simulation
Author          : Pratham
Language        : Java
Version         : 2.0 (Stable Build)
Date Completed  : November 2025
Platform        : Cross-Platform (Windows / Linux)
IDE Used        : IntelliJ IDEA / Eclipse / VS Code

---------------------------------------------------------------
OBJECTIVE:
---------------------------------------------------------------
To simulate the basic operations of an Automated Teller Machine (ATM)
such as balance inquiry, cash withdrawal, deposit, and PIN change.
The system provides a secure and user-friendly console interface
with timestamps and session summary.

---------------------------------------------------------------
FEATURES:
---------------------------------------------------------------
- 3 Login attempts for PIN verification
- Balance inquiry and withdrawal limit validation
- Deposit and PIN change operations
- Real-time timestamps for all transactions
- Session summary at program exit
- Cross-platform console screen clearing

---------------------------------------------------------------
KEY CONCEPTS USED:
---------------------------------------------------------------
- Loops and conditional statements
- Modular programming with static methods
- Scanner class for user input
- LocalDateTime for timestamps
- ProcessBuilder for console management

---------------------------------------------------------------
LIMITATIONS:
---------------------------------------------------------------
- Single-user account (no database storage)
- Console-based (no GUI yet)

---------------------------------------------------------------
FUTURE IMPROVEMENTS:
---------------------------------------------------------------
- Add transaction history tracking
- Multi-account support (OOP)
- File-based or database storage
- GUI implementation using JavaFX
===============================================================
*/

// ===============================================================
//                      START OF PROGRAM
// ===============================================================

// adding more soon !!!!!
import java.util.Scanner;
import java.time.LocalDateTime;

public class Atmsystem {
    static  int realpin = 667;
    static long balance = 100000 ;
    static int transactionCount = 0;
    static Scanner input = new Scanner(System.in);
    public static void main (String[] args){
        int enteredpin = 0, attempts = 0;
        while (attempts < 3) {
            clearScreen();
            System.out.println("===========================");
            System.out.println("WELCOME TO PRATHAM'S BANK ");
            System.out.println("===========================");
            System.out.print("ENTER THE PIN :");
            enteredpin = input.nextInt();

            if (enteredpin == realpin) {
                atmOperations();
                
                return; // exit main after successful session
            } else {
                attempts++;
                System.out.println("====================================");
                System.out.println("INCORRECT PIN! Attempts left: " + (3 - attempts));
                System.out.println("====================================");
                waitForEnter();
            }
        }
        System.out.println("Too many incorrect attempts. Account locked temporarily!");
    }

    public static void atmOperations() {
        int choice;
        while (true) {
            clearScreen();
            System.out.println("====================================");
            System.out.println("Welcome back, valued customer!");
            System.out.println("1.CHECK BALANCE\n2.WITHDRAW MONEY\n3.DEPOSIT MONEY\n4.CHANGE PIN\n5.EXit");
            choice = input.nextInt();
            try { Thread.sleep(1000); } catch (Exception e) {}
            switch (choice) {
                case 1:
                    System.out.println("====================================");
                    System.out.println("Your Current Balance: ₹" + balance);
                    transactionCount++;
                    System.out.println("---------------------------------------");
                    System.out.println("Transaction Time: " + LocalDateTime.now());
                    System.out.println("====================================");
                    waitForEnter();
                    break;
                case 2:
                    withdraw();
                    break;

                case 3:
                    deposit();
                    break;
                case 4:
                    changePin();
                    break;
                case 5:
                    EXIT();
                    return;
            }
        }

    }
    public  static void withdraw(){
        int amount;

        System.out.println("====================================");
        System.out.println("HOW MUCH YOU WANT TO WITHDRAW :");
        amount = input.nextInt();
        if (amount <= 0) {
            System.out.println("====================================");
            try { Thread.sleep(1000); } catch (Exception e) {}
            System.out.println("INVALID AMOUNT!");
            System.out.println("---------------------------------------");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            System.out.println("====================================");
            waitForEnter();

        } else if (amount > balance) {
            System.out.println("====================================");
            try { Thread.sleep(1000); } catch (Exception e) {}
            System.out.println("INSUFFICIENT BALANCE!");
            System.out.println("---------------------------------------");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            waitForEnter();
            System.out.println("====================================");
        } else {
            balance -= amount;
            System.out.println("====================================");
            try { Thread.sleep(1000); } catch (Exception e) {}
            System.out.println("WITHDRAWAL SUCCESSFUL!");
            System.out.println("Your Current Balance: ₹" + balance);
            transactionCount++;
            System.out.println("---------------------------------------");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            System.out.println("====================================");
            waitForEnter();
        }
        try { Thread.sleep(1000); } catch (Exception e) {}
        System.out.println("---------------------------------------");
        System.out.println("Thank you for banking with Pratham Bank!");
        waitForEnter();

    }
    public  static void deposit(){
        int amount;
        System.out.println("====================================");
        System.out.println("Enter amount to deposit (₹):");
        amount = input.nextInt();
        if (amount <= 0) {
            try { Thread.sleep(1000); } catch (Exception e) {}
            System.out.println("INVALID AMOUNT!");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            waitForEnter() ;

            return;
        }else {
            balance += amount;
            System.out.println("====================================");
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
            }
            System.out.println("You deposited ₹" + amount + " successfully!");
            System.out.println("Updated Balance: ₹" + balance);
            transactionCount++;
            System.out.println("---------------------------------------");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            System.out.println("Thank you for banking with Pratham Bank!");
            waitForEnter();
        }
    }
    public static void changePin(){
        int enterPin;
        System.out.println("====================================");
        System.out.println("ENTER YOUR PIN TO PROCEED !");
        enterPin = input.nextInt();
        if (enterPin != realpin) {
            System.out.println("====================================");
            System.out.println("INCORRECT PIN!");
            System.out.println("---------------------------------------");
            System.out.println("Transaction Time: " + LocalDateTime.now());
            waitForEnter();
            return;
        }
        while (true) {
            System.out.println("====================================");
            System.out.print("ENTER NEW PIN: ");
            int newPin = input.nextInt();
            System.out.println("====================================");
            System.out.print("CONFIRM NEW PIN: ");
            int confirmPin = input.nextInt();

            if (newPin == confirmPin && newPin > 99 && newPin < 9999) {
                realpin = newPin;
                System.out.println("====================================");
                try { Thread.sleep(1000); } catch (Exception e) {}
                System.out.println("✅ PIN CHANGED SUCCESSFULLY!");
                System.out.println("---------------------------------------");
                System.out.println("Transaction Time: " + LocalDateTime.now());
                waitForEnter();
                System.out.println("====================================");
                break;
            } else {
                System.out.println("====================================");
                System.out.println("❌ PINS DO NOT MATCH! TRY AGAIN.\n");
                System.out.println("---------------------------------------");
                System.out.println("Transaction Time: " + LocalDateTime.now());
                waitForEnter();
                System.out.println("====================================");
            }
        }

        }
    public static void clearScreen() {
        try {
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                System.out.print("\033[H\033[2J");
                System.out.flush();
            }
        } catch (Exception e) {
            System.out.println("⚠️ Unable to clear screen!");
        }
    }

    public static void waitForEnter(){
        System.out.println("\nPress Enter to return to main menu...");
        input.nextLine();
        input.nextLine();
    }
    public static void EXIT(){
        System.out.println("====================================");
        System.out.println("---------------------------------------");
        System.out.println("Thank you for banking with Pratham Bank!");
        System.out.println("====================================");
        System.out.println("\n========== SESSION SUMMARY ==========");
        System.out.println("Total Transactions: " + transactionCount);
        System.out.println("Final Balance: ₹" + balance);
        System.out.println("Session Closed at: " + LocalDateTime.now());
        System.out.println("====================================");
        waitForEnter();

    }
}

