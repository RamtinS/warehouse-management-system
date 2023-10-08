package wms;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * The class represents an item register,
 * and contains methods related to an object of the class Item.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
public class ItemRegister {
  private final List<Item> items;
  private final Map<String, Integer> priceOfItemsBeforeDiscount;

  /**
   * Constructor to create an object of ItemRegister.
   */
  public ItemRegister() {
    this.items = new ArrayList<>();
    this.priceOfItemsBeforeDiscount = new HashMap<>();
  }

  /**
   * The method finds an item by item number.
   *
   * @param itemNumber item number.
   * @return item by given item number.
   */
  private Item findItemByItemNr(String itemNumber) {
    for (Item item : items) {
      if (item.getItemNumber().equalsIgnoreCase(itemNumber.trim())) {
        return item;
      }
    }
    return null;
  }

  /**
   * The method sorts the list of items after category name.
   *
   * @return a sorted item list.
   */
  private List<Item> sortListAfterCategoryName() {
    List<Item> sortedItemList = items.stream()
            .sorted(Comparator.comparing(Item::getCategoryName))
            .collect(Collectors.toList());
    return sortedItemList;
  }

  /**
   * The method removes an item from the HashMap priceOfItemsBeforeDiscount.
   *
   * @param itemNumber item number.
   */
  private void removeItemFromMapPriceOfItemsBeforeDiscount(String itemNumber) {
    priceOfItemsBeforeDiscount.remove(itemNumber.toUpperCase().trim());
  }

  /**
   * The method registers an item using the object item, from the class Item.
   *
   * @param itemNumber item number.
   * @param description description of the item.
   * @param price price of the item.
   * @param brandName brand name of the item.
   * @param weight weight of the item.
   * @param length length of the item.
   * @param height height of the item.
   * @param color color of the item.
   * @param amountInStorage amount in storage of the item.
   * @param category category number of the item.
   * @throws IllegalArgumentException if an item with the same information already exists.
   */
  public void registerItem(String itemNumber, String description, int price,
                           String brandName, double weight, double length, double height,
                           String color, int amountInStorage, int category) {
    if (findItemByItemNr(itemNumber) != null) {
      throw new IllegalArgumentException("\nAn item with the same item number already exists.");
    }
    Item item = new Item(itemNumber, description, price, brandName, weight,
            length, height, color, amountInStorage, category);
    items.add(item);
  }

  /**
   * The method gets an item by item number.
   *
   * @param itemNumber item number.
   * @return item by given item number.
   */
  public Item getItemByItemNr(String itemNumber) {
    if (findItemByItemNr(itemNumber) == null) {
      return null;
    }
    return new Item(findItemByItemNr(itemNumber));
  }

  /**
   * The method increases the amount of an existing item.
   *
   * @param itemNumber item number.
   * @param increase the increase of the quantity.
   * @return a boolean to see if the increase was successful.
   * @throws IllegalArgumentException if the increase is less than zero.
   */
  public boolean increaseStorage(String itemNumber, int increase) {
    if (findItemByItemNr(itemNumber) == null) {
      return false;
    }
    if (increase < 0) {
      throw new IllegalArgumentException("\nThe increase can't be less than zero.");
    }
    findItemByItemNr(itemNumber)
            .setAmountInStorage(findItemByItemNr(itemNumber)
                    .getAmountInStorage() + increase);
    return true;
  }

  /**
   * The method reduces the amount of an existing item.
   *
   * @param itemNumber item number.
   * @param decrease the removal of the quantity.
   * @return a boolean to see if the decrease was successful.
   * @throws IllegalArgumentException if the decrease is less than zero.
   */
  public boolean decreaseStorage(String itemNumber, int decrease) {
    if (findItemByItemNr(itemNumber) == null) {
      return false;
    }
    if (decrease < 0) {
      throw new IllegalArgumentException("\nThe amount you want to remove, "
              + "can't be less than zero.");
    }
    findItemByItemNr(itemNumber)
            .setAmountInStorage(findItemByItemNr(itemNumber)
                    .getAmountInStorage() - decrease);
    return true;
  }

  /**
   * The method removes an item from the storage.
   *
   * @param itemNumber item number.
   * @return a boolean to see if the removal was successful.
   */
  public boolean removeItemFromStorage(String itemNumber) {
    return items.removeIf(item -> item.getItemNumber().equalsIgnoreCase(itemNumber.trim()));
  }

  /**
   * The method adds discount to an item.
   *
   * @param itemNumber item number.
   * @param discount desired discount.
   * @return a boolean to see if the discount was successful.
   * @throws IllegalArgumentException if discount is not a number between 0 and 100.
   */
  public boolean addDiscount(String itemNumber, int discount) {
    if (findItemByItemNr(itemNumber) == null) {
      return false;
    }
    if (discount < 0 || discount > 100) {
      throw new IllegalArgumentException("\nMake sure the discount is between 0 and 100 percent.");
    }
    if (!priceOfItemsBeforeDiscount.containsKey(itemNumber.toUpperCase().trim())) {
      priceOfItemsBeforeDiscount
              .put(itemNumber.toUpperCase().trim(), findItemByItemNr(itemNumber).getPrice());
    }
    findItemByItemNr(itemNumber)
            .setPrice((int) Math.round(findItemByItemNr(itemNumber)
                    .getPrice() * (100 - discount) / 100.0));
    return true;
  }

  /**
   * The method resets the price, to the price before the discount on item.
   *
   * @param itemNumber item number.
   * @return a boolean to see if the reset was successful.
   */
  public boolean resetPriceOfItemToBeforeDiscount(String itemNumber) {
    if (priceOfItemsBeforeDiscount.get(itemNumber.toUpperCase().trim()) == null) {
      return false;
    }
    findItemByItemNr(itemNumber)
            .setPrice(priceOfItemsBeforeDiscount.get(itemNumber.toUpperCase().trim()));
    removeItemFromMapPriceOfItemsBeforeDiscount(itemNumber);
    return true;
  }

  /**
   * The method changes the price of an item.
   *
   * @param itemNumber item number.
   * @param newPrice new price.
   * @return a boolean to see if the price was changed.
   */
  public boolean changePrice(String itemNumber, int newPrice) {
    if (findItemByItemNr(itemNumber) == null) {
      return false;
    }
    findItemByItemNr(itemNumber).setPrice(newPrice);
    removeItemFromMapPriceOfItemsBeforeDiscount(itemNumber);
    return true;
  }

  /**
   * The method changes the description of an item.
   *
   * @param itemNumber item number.
   * @param newDescription new description.
   * @return a boolean to see if the description was changed.
   */
  public boolean changeDescription(String itemNumber, String newDescription) {
    if (findItemByItemNr(itemNumber) == null) {
      return false;
    }
    findItemByItemNr(itemNumber).setDescription(newDescription);
    return true;
  }

  /**
   * The method gets items by category number.
   *
   * @param category the category number.
   * @return a list of all items with given category.
   */
  public List<Item> getItemsByCategory(int category) {
    List<Item> listItemsByCategory = new ArrayList<>();
    for (Item item : items) {
      if (item.getCategory() == category) {
        listItemsByCategory.add(new Item(item));
      }
    }
    return listItemsByCategory.stream()
            .sorted(Comparator.comparing(Item::getPrice).reversed())
            .collect(Collectors.toList());
  }

  /**
   * The method checks for which items need replenishment.
   *
   * @return a list of all items that need replenishment.
   */
  public List<Item> checkForRestockOfItems() {
    List<Item> listItemsWithEmptyStock = new ArrayList<>();
    for (Item item : sortListAfterCategoryName()) {
      if (item.getAmountInStorage() == 0) {
        listItemsWithEmptyStock.add(new Item(item));
      }
    }
    return listItemsWithEmptyStock;
  }

  /**
   * The method gives each registered item in
   * the list their descriptions, and displays it.
   *
   * @return all the items with their descriptions.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    Iterator<Item> iterator = sortListAfterCategoryName().iterator();
    while (iterator.hasNext()) {
      sb.append(iterator.next().toString());
    }
    return sb.toString();
  }
}
