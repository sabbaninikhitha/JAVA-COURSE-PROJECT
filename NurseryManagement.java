import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class NurseryManagement {
    private static final Scanner scanner = new Scanner(System.in);
    private static List<Plant> plants = new ArrayList<>();
    private static List<Order> orders = new ArrayList<>();
    private static List<Complaint> complaints = new ArrayList<>();

    public static void main(String[] args) {
        boolean exit = false;

        while (!exit) {
            System.out.println("Welcome to the Plant Nursery Management System");
            System.out.println("1. Admin Login");
            System.out.println("2. Customer Portal");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    adminLogin();
                    break;
                case 2:
                    customerPortal();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Exiting the program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void adminLogin() {
        System.out.print("Enter admin username: ");
        String username = scanner.next();
        System.out.print("Enter admin password: ");
        String password = scanner.next();

        if (username.equals("admin") && password.equals("password")) {
            adminPortal();
        } else {
            System.out.println("Invalid username or password. Login failed.");
        }
    }

    private static void adminPortal() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nAdmin Portal");
            System.out.println("1. See Collection of Plants");
            System.out.println("2. Add Plant");
            System.out.println("3. Delete Plant");
            System.out.println("4. View Orders");
            System.out.println("5. View Complaints/Feedback");
            System.out.println("6. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    seePlants();
                    break;
                case 2:
                    addPlant();
                    break;
                case 3:
                    deletePlant();
                    break;
                case 4:
                    viewOrders();
                    break;
                case 5:
                    viewComplaints();
                    break;
                case 6:
                    exit = true;
                    System.out.println("Going back to the main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void seePlants() {
        System.out.println("Collection of Plants:");
        for (Plant plant : plants) {
            System.out.println(plant);
        }
    }

    private static void addPlant() {
        scanner.nextLine(); // Clear the scanner buffer
        System.out.print("Enter plant name: ");
        String name = scanner.nextLine();
        System.out.print("Enter plant quantity: ");
        int quantity = scanner.nextInt();
        System.out.print("Enter plant price: ");
        double price = scanner.nextDouble();

        Plant plant = new Plant(name, quantity, price);
        plants.add(plant);
        System.out.println("Plant added successfully.");
    }

    private static void deletePlant() {
        System.out.print("Enter plant ID to delete: ");
        int plantId = scanner.nextInt();

        Plant plantToDelete = getPlantById(plantId);
        if (plantToDelete != null) {
            plants.remove(plantToDelete);
            System.out.println("Plant deleted successfully.");
        } else {
            System.out.println("Failed to delete plant. Invalid plant ID.");
        }
    }

 private static void viewOrders() {
    if (orders.isEmpty()) {
        System.out.println("No orders available.");
    } else {
        System.out.println("Orders:");
        double totalBill = 0;

        for (Order order : orders) {
            System.out.println(order);
            double orderAmount = order.getPlant().getPrice() * order.getQuantity();
            totalBill += orderAmount;
        }

        System.out.println("Total Bill: $" + totalBill);
    }
}

    private static void viewComplaints() {
        if (complaints.isEmpty()) {
            System.out.println("No complaints/Feedback available.");
        } else {
            System.out.println("Complaints:");
            for (Complaint complaint : complaints) {
                System.out.println(complaint);
            }
        }
    }

    private static Plant getPlantById(int plantId) {
        for (Plant plant : plants) {
            if (plant.getId() == plantId) {
                return plant;
            }
        }
        return null;
    }

    private static void customerPortal() {
        boolean exit = false;

        while (!exit) {
            System.out.println("\nCustomer Portal");
            System.out.println("1. Order Plants");
            System.out.println("2. Register Complaint/Feedback");
            System.out.println("3. Back");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    orderPlants();
                    break;
                case 2:
                    registerComplaint();
                    break;
                case 3:
                    exit = true;
                    System.out.println("Going back to the main menu.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void orderPlants() {
        scanner.nextLine(); // Clear the scanner buffer
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter plant ID: ");
        int plantId = scanner.nextInt();
        System.out.print("Enter quantity: ");
        int quantity = scanner.nextInt();

        Plant plant = getPlantById(plantId);
        if (plant != null) {
            if (quantity <= plant.getQuantity()) {
                Order order = new Order(customerName, plant, quantity);
                orders.add(order);
                plant.setQuantity(plant.getQuantity() - quantity);
                System.out.println("Order placed successfully.");
            } else {
                System.out.println("Insufficient quantity available for the selected plant.");
            }
        } else {
            System.out.println("Invalid plant ID.");
        }
    }

    private static void registerComplaint() {
        scanner.nextLine(); // Clear the scanner buffer
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter your complaint/Feedback: ");
        String complaint = scanner.nextLine();

        Complaint newComplaint = new Complaint(customerName, complaint);
        complaints.add(newComplaint);
        System.out.println("Thank you");
    }

    private static class Plant {
        private static int count = 0;

        private int id;
        private String name;
        private int quantity;
        private double price;

        public Plant(String name, int quantity, double price) {
            this.id = ++count;
            this.name = name;
            this.quantity = quantity;
            this.price = price;
        }

        public int getId() {
            return id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getQuantity() {
            return quantity;
        }

        public void setQuantity(int quantity) {
            this.quantity = quantity;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        @Override
        public String toString() {
            return "Plant{" +
                    "id=" + id +
                    ", name='"

 + name + '\'' +
                    ", quantity=" + quantity +
                    ", price=" + price +
                    '}';
        }
    }

    private static class Order {
        private String customerName;
        private Plant plant;
        private int quantity;

        public Order(String customerName, Plant plant, int quantity) {
            this.customerName = customerName;
            this.plant = plant;
            this.quantity = quantity;
        }

        public String getCustomerName() {
            return customerName;
        }

        public Plant getPlant() {
            return plant;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Order{" +
                    "customerName='" + customerName + '\'' +
                    ", plant=" + plant +
                    ", quantity=" + quantity +
                    '}';
        }
    }

    private static class Complaint {
        private String customerName;
        private String complaint;

        public Complaint(String customerName, String complaint) {
            this.customerName = customerName;
            this.complaint = complaint;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getComplaint() {
            return complaint;
        }

        @Override
        public String toString() {
            return "Complaint{" +
                    "customerName='" + customerName + '\'' +
                    ", complaint='" + complaint + '\'' +
                    '}';
        }
    }
}