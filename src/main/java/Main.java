import java.util.Scanner;

public class Main {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        mainMenu();
        System.out.println("Thanks for using my app.  Bye!!");
    }

    private static void mainMenu() {
        String userInput;
        String prompt = """
                        Main Menu
                    1. Search for product submenu
                    2. Display all products
                    3. You guessed it, option three
                    X. Exit the application
                    
                    Select one of the options above
                    """;
        boolean done = false;
        do {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    searchMenu();
                    break;
                case "2":
                    displayProducts();
                    break;
                case "3":
                    done = true;
                default:
                    System.err.println("Invalid input.  Please try again.");
            }
        } while(!done);
    }

    private static void displayProducts() {
        String userInput;
        String prompt = """
                    Search Menu
                1. Search by product name
                2. Search by price
                X. Exit search menu
                
                Select one of the options above
                """;
        boolean done = false;
        do {
            System.out.println(prompt);
            userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    searchByName();
                    break;
                case "2":
                    searchByPrice();
                    break;
                case "3":
                    done = true;
                default:
                    System.err.println("Invalid input.  Please try again.");
            }
        } while(!done);
    }

    private static void searchByPrice() {

    }

    private static void searchByName() {

    }

    private static void searchMenu() {

    }
}
