package dev.main;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;
//  java -jar target/Lab-jar-with-dependencies.jar 123 "jdbc:mysql://localhost:3306/java_lab_2?user=root"

public class Program {
    private String menu = """
                1. Read all products
                2. Read detail of a product by id
                3. Add a new product
                4. Update a product
                5. Delete a product
                6. Exit
            """;
    private static ProductDAO productDAO;
    private void readAll() {
        List<Product> list = productDAO.readAll();
        for (Product product : list) {
            System.out.println(product);
        }
    }

    private void read() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id of the product: ");
        long id = scanner.nextLong();
        Product product = productDAO.read(id);
        if (product != null) {
            System.out.println(product);
        } else {
            System.out.println("Product not found");
        }
    }

    private void add() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the name of the product: ");
        String name = scanner.nextLine();
        System.out.print("Enter the price of the product: ");
        long price = scanner.nextLong();
        Product product = new Product(name, price);
        ResultSet id = productDAO.add(product);
        if (id != null) {
            System.out.println("Product added with id: " + id);
        } else {
            System.out.println("Product not added");
        }
    }

    private void update() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id of the product you want to update: ");
        long id = scanner.nextLong();
        scanner.nextLine();
        System.out.print("Enter the name of the product you want to update: ");
        String name = scanner.nextLine();
        System.out.print("Enter the price of the product you want to update: ");
        long price = scanner.nextLong();
        Product product = new Product(id, name, price);
        boolean updated = productDAO.update(product);
        if (updated) {
            System.out.println("Product updated successfully");
        } else {
            System.out.println("Product not updated");
        }
    }

    private void delete() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter the id of the product you want to delete: ");
        long id = scanner.nextLong();
        boolean deleted = productDAO.delete(id);
        if (deleted) {
            System.out.println("Product deleted successfully");
        } else {
            System.out.println("Product not deleted");
        }
    }
    private void menu() {
        System.out.println(menu);
    }
    private void run() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            menu();

            System.out.print("Enter your choice (1-6): ");
            String choice = scanner.nextLine();

            try {
                int option = Integer.parseInt(choice);
                switch (option) {
                    case 1:
                        // TODO: Implement Read all products
                        System.out.println("You chose option 1:");
                        readAll();
                        break;
                    case 2:
                        // TODO: Implement Read detail of a product by id
                        System.out.println("You chose option 2.");
                        read();
                        break;
                    case 3:
                        // TODO: Implement Add a new product
                        System.out.println("You chose option 3.");
                        add();
                        break;
                    case 4:
                        // TODO: Implement Update a product
                        System.out.println("You chose option 4.");
                        update();
                        break;
                    case 5:
                        // TODO: Implement Delete a product
                        System.out.println("You chose option 5.");
                        delete();
                        break;
                    case 6:
                        System.out.println("Exiting the program. Goodbye!");
                        return;
                    default:
                        System.out.println("Invalid choice. Please enter a number between 1 and 6.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number between 1 and 6.");
            }
        }
    }

    public static void main(String[] args) throws SQLException {
        String connectionString = args[0];
        productDAO = new ProductDAO(connectionString);
        Program program = new Program();
        program.run();
    }
}
