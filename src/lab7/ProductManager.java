package lab7;

public class ProductManager {
	Product[] products = new Product[100];
    private int count = 0;
    public void addProduct(String name, double price){
        addProduct(name, price, 0);
    }
    public void addProduct(String name, double price, double discount){
        if(findProduct(name) >= 0) {
            System.out.println("Can't add, product already exists.");
            return; 
        }
        Product prod = new Product();
        prod.setName(name.trim());
        prod.setPrice(price);
        prod.setDiscount(discount);
        products[count++] = prod;
    }
   
    public int findProduct(String name) {
        name = name.trim();
        for (int i=0;i<count;i++) {
            if(products[i].getName().equalsIgnoreCase(name)) {
                return i;
            }
        }
        return -1;
    }
   
    public Product removeProduct(String name) {
        int foundIndex = findProduct(name);
        if(foundIndex == -1) {
            System.out.println("Product not found!");
            return null;
        }
        Product prod = products[foundIndex];
        for(int i=foundIndex;i<count;i++) {
            products[i] = products[i+1];
        }
        count--;
        return prod;
    }
   
    public boolean updateProduct(String oldName, String newName, double newPrice, double newDiscount) {
        int foundIndex = findProduct(oldName);
        if(foundIndex == -1) {
            System.out.println("Product not found!");
            return false;
        }
        products[foundIndex].setName(newName.trim());
        products[foundIndex].setPrice(newPrice);
        products[foundIndex].setDiscount(newDiscount);
        return true;
    }
   
}
