import java.util.*;

class Users {
    private String name;
    private int pin;
    private double balance;
    private ArrayList<String> transaction_history = new ArrayList<>();

    public Users(String name, int pin, double balance) {
        this.name = name;
        this.pin = pin;
        this.balance = balance;
        if (balance > 0) {
            transaction_history.add("Account created by adding initial amount : " + balance);
        }
    }

    public String getname() {
        return name;
    }

    public void change_pin(int pin) {
        this.pin = pin;
        System.out.println("Pin Changed!!!");
    }

    public double getbalance() {
        return this.balance;
    }

    public void deposit(double amount) {
        if (amount > 0) {
            this.balance += amount;
            this.transaction_history.add("Credited by : " + balance);
            System.out.println("Payment successful .");
        } else {
            System.out.println("Invalid Input !!!");
        }
    }

    public void withdraw(double amount) {
        if (amount <= this.balance && amount > 0) {
            this.balance -= amount;
            this.transaction_history.add("Withdrawn : " + amount);
        } else {
            System.out.println("Insufficient balance or invalid input");
        }
    }

    public boolean check_pin(int pin) {
        return (this.pin == pin);
    }

    public void print_transaction_history() {
        System.out.println("The Transaction history of " + this.name + " is:  ");
        for (String transaction : this.transaction_history) {
            System.out.println(transaction);
        }
    }
}

public class ATM {

    private static ArrayList<Users> users = new ArrayList<>();
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        users.add(new Users("xxx", 1211, 100.00));
        boolean exit = false;
        while (!exit) {
            System.out.println("Choose any of following option");
            System.out.println("1. Login ");
            System.out.println("2. Add New User");
            System.out.println("3. Exit");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    Users test = Aunthenticate();
                    if (test != null)
                        show_menu(test);
                    else
                        System.out.println("Authentication Failed !!!");
                    break;
                case 2:
                    Add_User();
                    break;

                case 3:
                    exit=true;
                    break;

                default:
                    System.out.println("Entered option is Incorrect.");
                    break;
            }
        }
    }

    public static  void show_menu(Users user) {
        boolean exit = false;
        while (!exit) {
            System.out.println("Please enter any option: ");
            System.out.println("1.Check balance");

            System.out.println("2.Deposit Money");
            System.out.println("3.Withdraw Money");
            System.out.println("4.Check pin");
            System.out.println("5.Print transaction history");
            System.out.println("6.Logout");
            int option = sc.nextInt();
            sc.nextLine();
            switch (option) {
                case 1:
                    System.out.println("Your current balance is : "+user.getbalance());
                    break;

                case 2 :
                    System.out.println("Enter the amount of Deposit money.");
                    double amount =sc.nextDouble();
                    user.deposit(amount );
                    break;

                case 3:
                    System.out.println("Enter the amount of Withdraw money.");
                    amount =sc.nextDouble();
                    user.withdraw(amount);
                    break;

                case 4:
                    System.out.println("Please enter your pin ");
                    int pin =sc.nextInt();
                    boolean bool = user.check_pin(pin);
                    if(bool)
                        System.out.println("Pin Correct.");
                    else
                        System.out.println("Pin Incorrect.");
                    break;

                case 5:
                    user.print_transaction_history();
                    break;

                case 6:
                    exit=true;
                    break;

                default:
                    System.out.println("Entered option is Incorrect");

            }
        }
    }

    public static Users Aunthenticate() {
        System.out.println("Please enter User name.");
        String name = sc.nextLine();
        System.out.println("Please Enter the pin.");
        int pin = sc.nextInt();
        sc.nextLine();
        for (Users user : users) {
            if (user.getname().equals(name) && user.check_pin(pin)) {
                return user;
            }
        }
        return null;
    }

    public static void Add_User() {
        System.out.println("Enter the name of the new User.");
        String name = sc.nextLine();
        System.out.println("Enter the pin of the new User.");
        int pin = sc.nextInt();
        System.out.println("Enter the initial sum to open the Account.");
        double amount = sc.nextDouble();
        sc.nextLine(); // Consume the newline character

        users.add(new Users(name, pin, amount));
        System.out.println("New User added successfully.");
    }
}
