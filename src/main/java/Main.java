import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static final String PRODUCTS_FILE_NAME = "src/main/resources/products.csv";
    static Scanner scanner = new Scanner(System.in);

    static ArrayList<Product> products = loadProducts(PRODUCTS_FILE_NAME);
    static ArrayList<Product> cartItems = new ArrayList<>();

    public static void main(String[] args) {
        mainMenu();
        System.out.println("Thanks for using my app.  Bye!!");
    }

    private static void mainMenu() {
        String prompt = """
                    Main Menu
                1. Products menu
                2. Display shopping cart
                X. Exit the application
                
                Select one of the options above:
                """;

        boolean running = true;
        do {
            System.out.print(prompt);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    productsMenu();
                    break;
                case "2":
                    displayCart();
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.err.println("Invalid input.  Please try again.");
            }
        } while (running);
    }

    private static void displayCart() {
        for (Product p: cartItems) {
            p.displayProduct();
        }
    }


    /**
     * Load products from file
     * @param fileName
     * @return
     */
    private static ArrayList<Product> loadProducts(String fileName) {
        ArrayList<Product> products = new ArrayList<Product>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(fileName);
            bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            // skip the header row
            line = bufferedReader.readLine();

            while (line != null) {
                Product product = parseProduct(line);
                products.add(product);
                line = bufferedReader.readLine();
            }
            bufferedReader.close();
        }
        catch (FileNotFoundException fne) {
            System.err.println("File not found: " + fileName);
        }
        catch (IOException ex) {
            System.out.println("Error loading inventory file: " + ex);
        }

        return products;
    }

    private static Product parseProduct(String line) {
        String[] parts = line.split("\\|");

        String sku = parts[0];
        String productName = parts[1];
        double price = Double.parseDouble(parts[2]);
        String department = parts[3];

        return new Product(sku, productName, price, department);
    }

    private static void productsMenu() {
        String prompt = """
                    Products Menu
                1. Search by product name
                2. Search by department
                3. Search by price
                4. Add product to shopping cart
                X. Exit to Main Menu
                
                Select one of the options above:
                """;
        boolean running = true;
        do {
            displayAllProducts();

            System.out.print(prompt);
            String userInput = scanner.nextLine();

            switch (userInput) {
                case "1":
                    searchByName();
                    break;
                case "2":
                    searchByDepartment();
                    break;
                case "3":
                    searchByPrice();
                    break;
                case "4":
                    addToCart();
                    break;
                case "X":
                    running = false;
                    break;
                default:
                    System.err.println("Invalid input.  Please try again.");
            }
        } while (running);
    }

    private static void searchByPrice() {
        // TODO: Implement method
    }

    private static void searchByDepartment() {
        // TODO: Implement method

    }

    private static void searchByName() {
        System.out.print("Enter a string to search product names: ");
        String userInput = scanner.nextLine();
        userInput = userInput.toLowerCase();

        for(Product p: products) {
            String productName = p.getProductName();
            productName = productName.toLowerCase();

            // "JBL Bluetooth Headphones" contains "Bluetooth" also contains "Headphones", also "JBL Bl"
            if (productName.contains(userInput)) {
                p.displayProduct();
            }
        }
        System.out.println("\n\n");
    }

    private static void displayAllProducts() {
        for (Product p: products) {
            p.displayProduct();
        }
    }

    private static void addToCart() {
        System.out.print("Enter the SKU of the product you want to add to your cart: ");
        String userInput = scanner.nextLine();
        // hopefully userInput will be like: GM3004

        int foundProducts = 0;
        for (Product p: products) {
            String sku = p.getSku();
            if(  userInput.equalsIgnoreCase(sku) ){
                cartItems.add(p);
                foundProducts++;
            }
        }
        if (foundProducts > 1) {
            throw new RuntimeException("Duplicate SKUs in products");
        }
        if (foundProducts == 0) {
            System.err.println("Sorry, could not find product with SKU: " + userInput);
        }
    }
}
