/*
1. Add a new item with details (name, item ID, quantity, price per unit, category) [X]
2. Remove existing item by item ID [X]
3. Update an item by quantity or price [X]
4. View all items with their details [X]
5. Search for an item by name or category [X]
6. View total inventory value (sum of quantity * price per unit) [X]
 */

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ItemManager itemManager = new ItemManager();

        boolean flag = true;
        while (flag) {
            printMenu();
            // command validation
            int command = 0;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("---INVALID INPUT---\n");
            }

            // switch case
            switch (command) {
                case 1:
                    System.out.println("\n---ADD A NEW ITEM---");
                    itemManager.createItem(scanner);
                    System.out.println("---ITEM CREATED---\n");
                    break;
                case 2:
                    System.out.println("\n---REMOVE EXISTING ITEM---");
                    itemManager.removeById(scanner);
                    break;
                case 3:
                    System.out.println("\n---UPDATE AN ITEM---");
                    itemManager.updateItem(scanner);
                    break;
                case 4:
                    System.out.println("\n---VIEW ALL ITEMS---");
                    itemManager.displayAllItem();
                    break;
                case 5:
                    System.out.println("\n---SEARCH FOR ITEM---");
                    itemManager.searchItem(scanner);
                    break;
                case 6:
                    System.out.println("\n---VIEW TOTAL INVENTORY VALUE---");
                    System.out.println("Total: $" + itemManager.totalInventory() + "\n");
                    break;
                case 7:
                    System.out.println("\n---GOODBYE---");
                    flag = false;
                    break;
                default:
                    System.out.println("---INVALID INPUT---\n");
                    break;
            }
        }

        scanner.close();
    }

    public static void printMenu() {
        System.out.println("Please choose a command: ");
        System.out.println("1. Add a new item");
        System.out.println("2. Remove existing item");
        System.out.println("3. Update an item");
        System.out.println("4. View all items");
        System.out.println("5. Search for an item");
        System.out.println("6. View total inventory value");
        System.out.println("7. Exit");
    }
}