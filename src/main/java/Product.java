public class Product {
    private String sku;
    private String productName;
    private double price;
    private String department;

    public Product(String sku, String productName, double price, String department) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.department = department;
    }

    public String getSku() {
        return sku;
    }

    public String getProductName() {
        return productName;
    }

    public double getPrice() {
        return price;
    }

    public String getDepartment() {
        return department;
    }

    public void displayProduct() {
        System.out.printf("%-8s %-32s $%8.2f  %-20s%n", sku, productName, price, department);
//        System.out.printf("%s %s $%f  %s%n", sku, productName, price, department);
    }

}