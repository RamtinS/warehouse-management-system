package test;

import wms.Item;

/**
 * The class tests the Item class.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
public class ItemTest {

  /**
   * The main method is used to test the Item class.
   *
   * @param args an array of String values.
   */
  public static void main(String[] args) {
    Item item = new Item("24GH", "Laminate flooring with extra moisture protection",
            2000, "Pergo", 7.5, 0.3, 1.3, "Brown", 30,
            1);

    System.out.println(item);

    item.setPrice(500);
    item.setDescription("Test");
    item.setAmountInStorage(25);

    System.out.println(item);

    try {
      item.setPrice(-100);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    try {
      item.setDescription("");
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    try {
      item.setAmountInStorage(-20);
    } catch (IllegalArgumentException e) {
      System.out.println(e.getMessage());
    }

    System.out.println(item);
  }
}
