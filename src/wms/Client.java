package wms;

import java.util.Scanner;

/**
 * The class represents a user interface.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
public class Client {
  private static final Scanner sc = new Scanner(System.in);

  private static final ItemRegister register = new ItemRegister();
  private static final Client client = new Client();

  private static final int SEE_ALL_ITEMS = 1;
  private static final int FIND_ITEM_BY_ITEM_NR = 2;
  private static final int FIND_ITEMS_BY_CATEGORY = 3;
  private static final int REGISTER_ITEM = 4;
  private static final int INCREASE_STORAGE = 5;
  private static final int DECREASE_STORAGE = 6;
  private static final int REMOVE_ITEM_FROM_STORAGE = 7;
  private static final int ADD_DISCOUNT = 8;
  private static final int REMOVE_DISCOUNT = 9;
  private static final int CHANGE_PRICE = 10;
  private static final int CHANGE_DESCRIPTION = 11;
  private static final int CHECK_FOR_RESTOCK_OF_ITEMS = 12;
  private static final int EXIT = 13;

  /**
   * The main method is used to communicate with the user.
   *
   * @param args an array of String values.
   */
  public static void main(String[] args) {
    client.testData();
    client.runMenu();
    sc.close();
  }

  /**
   * The method holds test data.
   */
  private void testData() {
    register.registerItem("24GH", "Laminate flooring with extra moisture protection", 2000,
            "Pergo", 7.5, 0.3, 1.3, "Brown", 30, 1);
    register.registerItem("20GH", "Durable laminate flooring", 1500,
            "Champs Elysess", 6, 0.4, 0.9, "Brown", 0, 1);
    register.registerItem("32VD", "Two-way window", 1600,
            "Rehau", 8, 0.50, 0.50, "White", 15, 2);
    register.registerItem("42DS", "Outer door with glass", 6500,
            "Nordic Door", 12, 1.2, 2.2, "White", 23, 3);
    register.registerItem("40DS", "Wooden outer door", 6200,
            "Nordic Door", 10, 1.2, 2.2, "Black", 0, 3);
    register.registerItem("12TL", "Wood fiber insulation with natural wood fiber", 399,
            "Hunton", 3.4, 2, 5.5, "Light brown", 25, 4);
  }

  /**
   * The method displays a menu.
   */
  private void showMenu() {
    System.out.println("""
            
            Choose what you want to do, and enter the number.
            1.  See all items in storage.
            2.  Find item by item number.
            3.  Find items by category.
            4.  Register a new item.
            5.  Increase the storage of an item.
            6.  Decrease the storage of an item.
            7.  Remove an item from storage.
            8.  Add discount on an item.
            9.  Remove discount from an item.
            10. Change the price of an item.
            11. Change the description of an item.
            12. Check which Items need replenishment.
            13. Exit.
            """);
  }

  /**
   * The method runs the menu.
   */
  private void runMenu() {
    int choice = 0;
    try {
      while (choice != 13) {
        client.showMenu();

        choice = Integer.parseInt(sc.nextLine().trim());

        switch (choice) {
          case SEE_ALL_ITEMS -> seeAllItems();
          case FIND_ITEM_BY_ITEM_NR -> findItemByItemNr();
          case FIND_ITEMS_BY_CATEGORY -> findItemsByCategory();
          case REGISTER_ITEM -> registerItem();
          case INCREASE_STORAGE -> increaseStorage();
          case DECREASE_STORAGE -> decreaseStorage();
          case REMOVE_ITEM_FROM_STORAGE -> removeItemFromStorage();
          case ADD_DISCOUNT -> addDiscount();
          case REMOVE_DISCOUNT -> removeDiscount();
          case CHANGE_PRICE -> changePrice();
          case CHANGE_DESCRIPTION -> changeDescription();
          case CHECK_FOR_RESTOCK_OF_ITEMS -> checkForRestockOfItems();
          case EXIT -> exit();
          default -> invalidInput();
        }
      }
    } catch (NumberFormatException e) {
      System.out.println("\nYou must enter a valid number. Try again.");
      runMenu();
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
      runMenu();
    } catch (Exception e) {
      System.out.println("\nSomething went wrong. Try again.");
      runMenu();
    }
  }

  /**
   * The method displays all items.
   */
  private void seeAllItems() {
    if (register.toString().isEmpty()) {
      System.out.println("\nNo items registered.");
    } else {
      System.out.println("All items in storage:");
      System.out.println(register);
    }
  }

  /**
   * The method finds the desired item.
   */
  private void findItemByItemNr() {
    System.out.println("\nYou have chosen to find item by item number.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    if (register.getItemByItemNr(itemNumber) == null) {
      System.out.println("\nItem with that item number does not exist.");
    } else {
      System.out.println(register.getItemByItemNr(itemNumber));
    }
  }

  /**
   * The method finds all items by given category.
   */
  private void findItemsByCategory() {
    System.out.println("\nYou have chosen to find items by category.");
    System.out.println("""
              Type in the category number for the items.
               1. Floor laminates
               2. Windows
               3. Doors
               4. Lumber""");
    int categoryNr = Integer.parseInt(sc.nextLine().trim());
    if (register.getItemsByCategory(categoryNr).isEmpty()) {
      System.out.println("\nNo registered items with that category number.");
    } else {
      System.out.println("\nAll items with category number " + categoryNr + ".");
      System.out.println(register.getItemsByCategory(categoryNr));
    }
  }

  /**
   * The method registers an item.
   */
  private void registerItem() {
    System.out.println("\nYou have chosen to register an item.");
    System.out.println("Enter an item number.");
    final String itemNumber = sc.nextLine();
    System.out.println("Enter an item description.");
    final String description = sc.nextLine();
    System.out.println("Enter a price for the item as an integer.");
    final int price = Integer.parseInt(sc.nextLine().trim());
    System.out.println("Enter a brand name for the item.");
    final String brandName = sc.nextLine();
    System.out.println("Enter a weight for the item.");
    final double weight = Double.parseDouble(sc.nextLine().trim());
    System.out.println("Enter a length for the item.");
    final double length = Double.parseDouble(sc.nextLine().trim());
    System.out.println("Enter a height for the item.");
    final double height = Double.parseDouble(sc.nextLine().trim());
    System.out.println("Enter a color for the item.");
    final String color = sc.nextLine();
    System.out.println("Enter the amount in storage of the item.");
    final int amountInStorage = Integer.parseInt(sc.nextLine().trim());
    System.out.println("""
              Enter a category number between 1 and 4 for the item.
               1. Floor laminates
               2. Windows
               3. Doors
               4. Lumber""");
    final int category = Integer.parseInt(sc.nextLine().trim());
    register.registerItem(itemNumber, description, price, brandName, weight,
            length, height, color, amountInStorage, category);
    System.out.println("\nItem registered.");
  }

  /**
   * The method increase the storage of a specific item.
   */
  private void increaseStorage() {
    System.out.println("\nYou have chosen to increase the storage.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    System.out.println("Enter the amount you want to increase by.");
    int increase = Integer.parseInt(sc.nextLine().trim());
    if (register.increaseStorage(itemNumber, increase)) {
      System.out.println("\nStorage increased.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method decrease the storage of a specific item.
   */
  private void decreaseStorage() {
    System.out.println("\nYou have chosen to decrease the storage.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    System.out.println("Enter the amount you want to decrease by.");
    int decrease = Integer.parseInt(sc.nextLine().trim());
    if (register.decreaseStorage(itemNumber, decrease)) {
      System.out.println("\nStorage decreased.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method removes a specific item from the storage.
   */
  private void removeItemFromStorage() {
    System.out.println("\nYou have chosen to remove an item from storage.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    if (register.removeItemFromStorage(itemNumber)) {
      System.out.println("\nItem with item number " + itemNumber.toUpperCase()
              + " has been removed from storage.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method adds a discount to an item.
   */
  private void addDiscount() {
    System.out.println("\nYou have chosen to add a discount to an item.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    System.out.println("Enter the discount in percent.");
    int discount = Integer.parseInt(sc.nextLine().trim());
    if (register.addDiscount(itemNumber, discount)) {
      System.out.println("\nDiscount successful.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method removes discount from an item.
   */
  private void removeDiscount() {
    System.out.println("\nYou have chosen to remove discount from an item.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    if (register.resetPriceOfItemToBeforeDiscount(itemNumber)) {
      System.out.println("\nDiscount removed.");
    } else {
      System.out.println("\nItem with that item number has no discount to be removed.");
    }
  }

  /**
   * The method changes the price of an item.
   */
  private void changePrice() {
    System.out.println("\nYou have chosen to change the price of the item.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    System.out.println("Enter the new price as an integer.");
    int newPrice = Integer.parseInt(sc.nextLine().trim());
    if (register.changePrice(itemNumber, newPrice)) {
      System.out.println("\nPrice changed.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method changes the description of an item.
   */
  private void changeDescription() {
    System.out.println("\nYou have chosen to change the description of the item.");
    System.out.println("Type in the item number.");
    String itemNumber = sc.nextLine();
    System.out.println("Enter the new description.");
    String description = sc.nextLine();
    if (register.changeDescription(itemNumber, description)) {
      System.out.println("\nDescription changed.");
    } else {
      System.out.println("\nItem with that item number does not exist.");
    }
  }

  /**
   * The method shows all items that need replenishment.
   */
  private void checkForRestockOfItems() {
    System.out.println("\nYou have chosen to see all items that need replenishment.");
    if (register.checkForRestockOfItems().isEmpty()) {
      System.out.println("\nNo items need replenishment.");
    } else {
      System.out.println("\n" + register.checkForRestockOfItems().size()
              + " items need replenishment.");
      System.out.println(register.checkForRestockOfItems());
    }
  }

  /**
   * The method shows that you have exited the program.
   */
  private void exit() {
    System.out.println("\nYou have exited the program.\n");
  }

  /**
   * The method shows that you have entered an invalid number.
   */
  private void invalidInput() {
    System.out.println("\nYou have entered an invalid number. "
            + "Try again with a number between 1 and 13.");
  }
}


