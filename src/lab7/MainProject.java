package lab7;

import java.util.Scanner;

public class MainProject{
	private static ProductManager productManager = new ProductManager();
    private static Scanner scanner = new Scanner(System.in);
	private static void addProduct() {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter product price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter product discount (0 if none): ");
        double discount = scanner.nextDouble();
        scanner.nextLine(); 

        productManager.addProduct(name, price, discount);
        System.out.println("Product added successfully!");
    }

    private static void removeProduct() {
        System.out.print("Enter the name of the product to remove: ");
        String name = scanner.nextLine();
        Product removedProduct = productManager.removeProduct(name);

        if (removedProduct != null) {
            System.out.println("Removed product: " + removedProduct);
        }
    }

    private static void updateProduct() {
        System.out.print("Enter the name of the product to update: ");
        String oldName = scanner.nextLine();
        System.out.print("Enter new name: ");
        String newName = scanner.nextLine();
        System.out.print("Enter new price: ");
        double newPrice = scanner.nextDouble();
        System.out.print("Enter new discount: ");
        double newDiscount = scanner.nextDouble();
        scanner.nextLine(); 

        boolean updated = productManager.updateProduct(oldName, newName, newPrice, newDiscount);
        if (updated) {
            System.out.println("Product updated successfully!");
        }
    }

    private static void viewProducts() {
        System.out.println("\n--- All Products ---");
        boolean hasProducts = false;
        for (int i = 0; i < productManager.products.length; i++) {
            Product product = productManager.products[i];
            if (product != null) {
                hasProducts = true;
                System.out.println(product);
            }
        }
        if (!hasProducts) {
            System.out.println("No products available.");
        }
    }
    public static void main(String[] args) {
        int choice;
        do {
            System.out.println("\n--- Product Manager Menu ---");
            System.out.println("1. Add Product");
            System.out.println("2. Remove Product");
            System.out.println("3. Update Product");
            System.out.println("4. View All Products");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); 
            switch (choice) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    removeProduct();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    viewProducts();
                    break;
                case 5:
                    System.out.println("Exiting... Thank you!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 5);
    }

}

