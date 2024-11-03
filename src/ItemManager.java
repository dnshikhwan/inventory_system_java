import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ItemManager {
    private ArrayList<Item> items;

    public ItemManager() {
        items = new ArrayList<>();
    }

    public void createItem(Scanner scanner) {
        System.out.println("Please enter item's name: ");
        String name = scanner.nextLine();

        //request uuid, if not present generate our uuid

        int quantity = 0;
        boolean isQuantityOkay = false;
        while (!isQuantityOkay) {
            try {
                System.out.println("Please enter item's quantity: ");
                quantity = Integer.parseInt(scanner.nextLine());
                isQuantityOkay = true;
            } catch (NumberFormatException e) {
                System.out.println("---INVALID INPUT---");
            }
        }

        // validate price is just two decimal place
        float price = 0;
        boolean isPriceOkay = false;
        while (!isPriceOkay) {
            try {
                System.out.println("Please enter item's price: ");

                String regex = "^([\\d]+).(\\d){2}$";
                String tempPrice = scanner.nextLine();
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(tempPrice);

                if (!matcher.matches()) {
                    System.out.println("---INVALID INPUT---");
                } else {
                    price = Float.parseFloat(tempPrice);
                    isPriceOkay = true;
                }

            } catch (NumberFormatException e) {
                System.out.println("---INVALID INPUT---\n");
            }
        }

        System.out.println("Please enter item's category: ");
        String category = scanner.nextLine();

        Item item = new Item(name, quantity, price, category);
        items.add(item);
    }

    public void removeById(Scanner scanner) {
        if (!items.isEmpty()) {
            displayAllItem();
            System.out.println("Please enter item's UUID: ");
            String uuid = scanner.nextLine();

            List<Item> foundItem = findByField("uuid", uuid);
            for (Item item : foundItem) {
                items.remove(item);
                break;
            }

            System.out.println("---ITEM WITH UUID " + uuid + " REMOVED---\n");
        } else {
            System.out.println("No item data, please add one.\n");
        }
    }

    public void updateItem(Scanner scanner) {
        if (!items.isEmpty()) {
            displayAllItem();

            System.out.println("Please enter item's uuid: ");
            String uuid = scanner.nextLine();

            List<Item> foundItem = findByField("uuid", uuid);
            System.out.println("Update item by quantity or price? ");
            String choice = scanner.nextLine();

            if (choice.equals("quantity")) {
                System.out.println("Update quantity to: ");
                int quantity = 0;

                try {
                    quantity = Integer.parseInt(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("---INVALID INPUT---\n");
                }

                for (Item item : foundItem) {
                    item.setQuantity(quantity);
                    break;
                }

                System.out.println("---QUANTITY UPDATED---\n");
            } else if (choice.equals("price")) {
                System.out.println("Update price to: ");
                float price = 0;

                try {
                    price = Float.parseFloat(scanner.nextLine());
                } catch (NumberFormatException e) {
                    System.out.println("---INVALID INPUT---\n");
                }

                for (Item item : foundItem) {
                    item.setPrice(price);
                    break;
                }

                System.out.println("---PRICE UPDATED---\n");
            } else {
                System.out.println("---INVALID INPUT---\n");
            }

        } else {
            System.out.println("No item data, please add one.\n");
        }
    }

    public void searchItem(Scanner scanner) {
        if (!items.isEmpty()) {
            System.out.println("Search for item by name or category? ");
            String choice = scanner.nextLine();

            String param = "";
            if (choice.equals("name")) {
                System.out.println("Please enter item's name: ");
                param = scanner.nextLine();
            } else if (choice.equals("category")) {
                System.out.println("Please enter item's category: ");
                param = scanner.nextLine();
            }

            System.out.println("\n---FOUND ITEMS---");
            List<Item> foundItem = findByField(choice, param);
            for (Item item : foundItem) {
                displayItem(item);
            }

        } else {
            System.out.println("No item data, please add one.\n");
        }
    }

    public List<Item> findByField(String field, String param) {
        List<Item> foundItems = new ArrayList<>();

        switch (field) {
            case "uuid":
                for (Item item : items) {
                    if (item.getUuid().equals(param)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "name":
                for (Item item : items) {
                    if (item.getName().equals(param)) {
                        foundItems.add(item);
                    }
                }
                break;
            case "category":
                for (Item item : items) {
                    if (item.getCategory().equals(param)) {
                        foundItems.add(item);
                    }
                }
                break;
        }

        return foundItems;
    }

    public float totalInventory() {
        float total = 0;

        for (Item item : items) {
            total += (item.getPrice() * item.getQuantity());
        }

        return total;
    }

    public void displayItem(Item item) {
        System.out.println("Name: " + item.getName());
        System.out.println("Item Id: " + item.getUuid());
        System.out.println("Quantity: " + item.getQuantity());
        System.out.println("Price per unit: $" + item.getPrice());
        System.out.println("Category: " + item.getCategory());
        System.out.println();
    }

    public void displayAllItem() {
        if (!items.isEmpty()) {
            for (Item item : items) {
                System.out.println("Name: " + item.getName());
                System.out.println("Item Id: " + item.getUuid());
                System.out.println("Quantity: " + item.getQuantity());
                System.out.println("Price per unit: $" + item.getPrice());
                System.out.println("Category: " + item.getCategory());
                System.out.println();
            }
        } else {
            System.out.println("No item data, please add one.\n");
        }
    }
}
