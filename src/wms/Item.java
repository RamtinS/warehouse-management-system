package wms;

/**
 * The class represents an item.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
public class Item {
  private final String itemNumber;
  private String description;
  private int price;
  private final String brandName;
  private final double weight;
  private final double length;
  private final double height;
  private final String color;
  private int amountInStorage;
  private final int category;

  /**
   * Constructor to create an object of the type item,
   * with the object variables below.
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
   * @param category category of the item.
   * @throws IllegalArgumentException if input is invalid.
   */
  public Item(String itemNumber, String description, int price, String brandName, double weight,
              double length, double height, String color, int amountInStorage, int category) {
    if (itemNumber.isBlank()) {
      throw new IllegalArgumentException("\nYou must enter an item number.");
    }
    this.itemNumber = itemNumber.toUpperCase().trim();

    if (description.isBlank()) {
      throw new IllegalArgumentException("\nYou must enter a description for the item.");
    }
    this.description = description.trim();

    if (price < 0) {
      throw new IllegalArgumentException("\nPrice can't be less than zero.");
    }
    this.price = price;

    if (brandName.isBlank()) {
      throw new IllegalArgumentException("\nYou must enter a brand name for the item.");
    }
    this.brandName = brandName.trim();

    if (weight <= 0) {
      throw new IllegalArgumentException("\nWeight can't be less than or equal to zero.");
    }
    this.weight = weight;

    if (length <= 0) {
      throw new IllegalArgumentException("\nLength can't be less than or equal to zero.");
    }
    this.length = length;

    if (height <= 0) {
      throw new IllegalArgumentException("\nHeight can't be less than or equal to zero.");
    }
    this.height = height;

    if (color.isBlank()) {
      throw new IllegalArgumentException("\nYou must enter a color for the item.");
    }
    this.color = color.trim();

    if (amountInStorage < 0) {
      throw new IllegalArgumentException("\nAmount in storage can't be less than zero.");
    }
    this.amountInStorage = amountInStorage;

    if (category < 1 || category > 4) {
      throw new IllegalArgumentException("\nYou must enter a category number between 1 and 4.");
    }
    this.category = category;
  }

  /**
   * Deep copy of constructor.
   *
   * @param item deep copy object.
   */
  public Item(Item item) {
    this(item.getItemNumber(), item.getDescription(), item.getPrice(), item.getBrandName(),
            item.getWeight(), item.getLength(), item.getHeight(), item.getColor(),
            item.getAmountInStorage(), item.getCategory());
  }

  /**
   * The method sets a new description of the item.
   *
   * @param description new description for the item.
   * @throws IllegalArgumentException if description is blank.
   */
  public void setDescription(String description) {
    if (description.isBlank()) {
      throw new IllegalArgumentException("\nYou must enter a description for the item. Try again");
    }
    this.description = description.trim();
  }

  /**
   * The method sets a new price for the item.
   *
   * @param price new price for the item.
   * @throws IllegalArgumentException if price is less than zero.
   */
  public void setPrice(int price) {
    if (price < 0) {
      throw new IllegalArgumentException("\nPrice can't be less than zero. Try again");
    }
    this.price = price;
  }

  /**
   * The method sets a new amount of the item in storage.
   *
   * @param amountInStorage new amount of the item in storage.
   * @throws IllegalArgumentException if amount in storage is less than zero.
   */
  public void setAmountInStorage(int amountInStorage) {
    if (amountInStorage < 0) {
      throw new IllegalArgumentException("\nAmount in storage can't be less than zero. Try again");
    }
    this.amountInStorage = amountInStorage;
  }

  /**
   * The method retrieves category name with help of enums.
   *
   * @return category name of the item.
   */
  public String getCategoryName() {
    return Category.getCategoryNameByCategoryNr(category);
  }

  /**
   * The method retrieves the item number of the item.
   *
   * @return the value of the variable itemNumber.
   */
  public String getItemNumber() {
    return itemNumber;
  }

  /**
   * The method retrieves the description of the item.
   *
   * @return the value of the variable description.
   */
  public String getDescription() {
    return description;
  }

  /**
   * The method retrieves the price of the item.
   *
   * @return the value of the variable price.
   */
  public int getPrice() {
    return price;
  }

  /**
   * The method retrieves the brand name of the item.
   *
   * @return the value of the variable brandName.
   */
  public String getBrandName() {
    return brandName;
  }

  /**
   * The method retrieves the weight of the item.
   *
   * @return the value of the variable weight.
   */
  public double getWeight() {
    return weight;
  }

  /**
   * The method retrieves the length of the item.
   *
   * @return the value of the variable length.
   */
  public double getLength() {
    return length;
  }

  /**
   * The method retrieves the height of the item.
   *
   * @return the value of the variable height.
   */
  public double getHeight() {
    return height;
  }

  /**
   * The method retrieves the color of the item.
   *
   * @return the value of the variable color.
   */
  public String getColor() {
    return color;
  }

  /**
   * The method retrieves the amount in storage of the item.
   *
   * @return the value of the variable amountInStorage.
   */
  public int getAmountInStorage() {
    return amountInStorage;
  }

  /**
   * The method retrieves the category number of the item.
   *
   * @return the value of the variable category.
   */
  public int getCategory() {
    return category;
  }

  /**
   * The method collects all the information about the item,
   * and displays it.
   *
   * @return information about the item.
   */
  @Override
  public String toString() {
    return "\nItem number: " + getItemNumber() + "."
            + "\nDescription: " + getDescription() + "."
            + "\nPrice: " + getPrice() + " kr."
            + "\nBrand name: " + getBrandName() + "."
            + "\nWeight: " + getWeight() + " kg."
            + "\nLength: " + getLength() + " m."
            + "\nHeight: " + getHeight() + " m."
            + "\nColor: " + getColor() + "."
            + "\nAmount in storage: " + getAmountInStorage() + "."
            + "\nCategory name: " + getCategoryName() + ".\n";
  }

  /**
   * The method checks for equality between objects.
   *
   * @param o the object to which it is being compared.
   * @return a boolean value which indicate whether they are equal or not.
   */
  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Item item)) {
      return false;
    }
    return getPrice() == item.getPrice()
            && Double.compare(item.getWeight(), getWeight()) == 0
            && Double.compare(item.getLength(), getLength()) == 0
            && Double.compare(item.getHeight(), getHeight()) == 0
            && getAmountInStorage() == item.getAmountInStorage()
            && getCategory() == item.getCategory()
            && getItemNumber().equals(item.getItemNumber())
            && getDescription().equals(item.getDescription())
            && getBrandName().equals(item.getBrandName())
            && getColor().equals(item.getColor());
  }
}