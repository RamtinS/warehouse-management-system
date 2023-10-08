package wms;

/**
 * The class contains a set of categories.
 *
 * @author Ramtin Samavat.
 * @version 1.0
 * @since Dec 12, 2022.
 */
public enum Category {
  /**
   * Floor laminates.
   */
  FLOOR_LAMINATES(1, "Floor laminates"),
  /**
   * Windows.
   */
  WINDOWS(2, "Windows"),
  /**
   * Doors.
   */
  DOORS(3, "Doors"),
  /**
   * Lumber.
   */
  LUMBER(4, "Lumber");

  private final int categoryNr;
  private final String categoryName;

  /**
   * The constructor sets an integer and a string value for the enum.
   *
   * @param categoryNr category number/enum value.
   * @param categoryName category name/enum value.
   */
  Category(int categoryNr, String categoryName) {
    this.categoryNr = categoryNr;
    this.categoryName = categoryName;
  }

  /**
   * The method retrieves category names with category numbers.
   *
   * @param categoryNr category number/enum value.
   * @return category name.
   */
  public static String getCategoryNameByCategoryNr(int categoryNr) {
    for (Category category : Category.values()) {
      if (category.getCategoryNr() == categoryNr) {
        return category.getCategoryName();
      }
    }
    return null;
  }

  /**
   * The method retrieves the category number assigned to the enum.
   *
   * @return the category number/value of the enum.
   */
  public int getCategoryNr() {
    return categoryNr;
  }

  /**
   * The method retrieves the category name assigned to the enum.
   *
   * @return the category name of the enum.
   */
  public String getCategoryName() {
    return categoryName;
  }
}

