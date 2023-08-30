public class Product{

  private String productId;
  private String price;
  private String name;
  private String description;

  public Product(String productId, String price, String name, String description){
    this.productId = productId.trim();
    this.price = price;
    this.name = name;
    this.description = description;
  }

  public String getProductId() {
  	System.out.println("productId : " + productId);
    return productId;
  }

  public void setProductId(String productId) {
    this.productId = productId.trim();
  }

  public String getPrice() {
  	System.out.println("price : " + price);
    return price;
  }

  public void setPrice(String price) {
    this.price = price;
  }

  public String getName() {
  	System.out.println("name : " + name);
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getDescription() {
  	System.out.println("description : " + description);
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

}
