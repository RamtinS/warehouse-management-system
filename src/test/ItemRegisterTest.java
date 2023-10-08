package test;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wms.Item;
import wms.ItemRegister;

/**
 * The class tests the ItemRegister class.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
class ItemRegisterTest {

  private ItemRegister register;

  @BeforeEach
  void setup() {
    register = new ItemRegister();
  }

  @Test
  void shouldRegisterItem() {
    Item item = new Item("24GH", "Laminate flooring with extra moisture protection",
            2000, "Pergo", 7.5, 0.3, 1.3, "Brown", 30,
            1);
    register.registerItem("24GH", "Laminate flooring with extra moisture protection",
            2000, "Pergo", 7.5, 0.3, 1.3, "Brown", 30,
            1);
    Assertions.assertEquals(item, register.getItemByItemNr("24GH"));
  }

  @Test
  void shouldNotRegisterItem() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.registerItem("24GH",
                    "Laminate flooring with extra moisture protection", 2000, "Pergo",
                    7.5, 0.3, 1.3, "Brown", 30, 1));
  }

  @Test
  void shouldGetItemByItemNr() {
    addDefaultItem();
    Item item = register.getItemByItemNr("24GH");
    Assertions.assertEquals("24GH", item.getItemNumber());
  }

  @Test
  void shouldNotGetItemByItemNr() {
    addDefaultItem();
    Assertions.assertNull(register.getItemByItemNr("34GH"));
  }

  @Test
  void shouldIncreaseStorage() {
    addDefaultItem();
    register.increaseStorage("24GH", 10);
    Assertions.assertEquals(40, register.getItemByItemNr("24GH").getAmountInStorage());
  }

  @Test
  void shouldNotIncreaseStorage() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.increaseStorage("24GH", -10));
  }

  @Test
  void shouldDecreaseStorage() {
    addDefaultItem();
    register.decreaseStorage("24GH", 10);
    Assertions.assertEquals(20, register.getItemByItemNr("24GH").getAmountInStorage());
  }

  @Test
  void shouldNotDecreaseStorage() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.decreaseStorage("24GH", -10));
  }

  @Test
  void shouldRemoveItemFromStorage() {
    addDefaultItem();
    Assertions.assertTrue(register.removeItemFromStorage("24GH"));
    Assertions.assertNull(register.getItemByItemNr("24GH"));
  }

  @Test
  void shouldAddDiscount() {
    addDefaultItem();
    register.addDiscount("24GH", 60);
    Assertions.assertEquals(800, register.getItemByItemNr("24GH").getPrice());
  }

  @Test
  void shouldNotAddDiscount() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.addDiscount("24GH", 110));
  }

  @Test
  void shouldResetPriceOfItemToBeforeDiscount() {
    shouldAddDiscount();
    Assertions.assertTrue(register.resetPriceOfItemToBeforeDiscount("24GH"));
    Assertions.assertEquals(2000, register.getItemByItemNr("24GH").getPrice());
  }

  @Test
  void shouldNotResetPriceOfItemToBeforeDiscount() {
    shouldAddDiscount();
    Assertions.assertFalse(register.resetPriceOfItemToBeforeDiscount("23GH"));
  }

  @Test
  void shouldChangePrice() {
    addDefaultItem();
    register.changePrice("24GH", 300);
    Assertions.assertEquals(300, register.getItemByItemNr("24GH").getPrice());
  }

  @Test
  void shouldNotChangePrice() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.changePrice("24GH", -300));
  }

  @Test
  void shouldChangeDescription() {
    addDefaultItem();
    register.changeDescription("24GH", "Test");
    Assertions.assertEquals("Test", register.getItemByItemNr("24GH").getDescription());
  }

  @Test
  void shouldNotChangeDescription() {
    addDefaultItem();
    Assertions.assertThrows(IllegalArgumentException.class,
            () -> register.changeDescription("24GH", " "));
  }

  @Test
  void shouldGetItemsByCategory() {
    addDefaultItem();
    List<Item> itemsByCategory = register.getItemsByCategory(1);
    Assertions.assertEquals(1, itemsByCategory.size());
  }

  @Test
  void shouldCheckForRestockOfItems() {
    register.registerItem("24GH", "Laminate flooring with extra moisture protection", 2000,
            "Pergo", 7.5, 0.3, 1.3, "Brown", 0, 1);
    List<Item> listItemsWithEmptyStock = register.checkForRestockOfItems();
    Assertions.assertEquals(1, listItemsWithEmptyStock.size());
  }

  @Test
  void testToString() {
    addDefaultItem();
    Assertions.assertEquals(register.getItemByItemNr("24GH").toString(), register.toString());
  }

  private void addDefaultItem() {
    register.registerItem("24GH", "Laminate flooring with extra moisture protection", 2000,
            "Pergo", 7.5, 0.3, 1.3, "Brown", 30, 1);
  }
}
