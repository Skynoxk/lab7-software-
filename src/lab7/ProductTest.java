/*
 * Author: Testing is created by Meas ratanakviphou
 * Date: 24/01/2025
 * ProductTest
 */

package lab7;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.concurrent.CountDownLatch;
import java.lang.module.FindException;
import java.lang.reflect.Field;
import org.junit.jupiter.api.DisplayName;
public class ProductTest {
	
    @Test @DisplayName("Set name correctly (name is not blank)")
    void testNameNotBlank() {
        Product product = new Product();
        product.setName("Grape");
        assertEquals("Grape", product.getName());
        product.setName("	Pap aya		");
        assertEquals("Pap aya", product.getName().trim());
    }
    @Test @DisplayName("Set name incorrectly (name is blank)")
    void testNameBlank() {
        Product product = new Product();
        product.setName("               ");
        assertNull(product.getName());
    }
    @Test @DisplayName("Set price greater than zero")
    void SetPriceCorrectly() {
    	Product product = new Product();
    	product.setPrice(200);
    	assertEquals(200, product.getPrice());
    }
    @Test @DisplayName("Set price is less than zero")
    void SetPriceIncorrectly() {
    	Product product = new Product();
    	product.setPrice(-12);
    	assertEquals(0.0, product.getPrice());
    }
    @Test @DisplayName("Discount is between 0 and 100")
    void SetDiscountCorrectly () {
    	Product product = new Product();
    	product.setDiscount(23.3);
    	assertEquals(23.3, product.getDiscount());
    }
    @Test @DisplayName("Discount is less than 0 or Greater than 100")
    void SetDiscountIncorrectly() {
    	Product product = new Product ();
    	product.setDiscount(-12);
    	assertEquals(0, product.getDiscount());
    	product.setDiscount(1000);
    	assertEquals(0, product.getDiscount());
    }
    //=============================================Task 2 =============================================
    @Test @DisplayName("Product name not exist")
    void addProductCorrectly() {
    	ProductManager product = new ProductManager();
    	product.addProduct("null", 0);
    	product.addProduct("Ball", 12.32);
    	assertEquals(1, product.findProduct("Ball"));    
 
    }
    @Test @DisplayName("Product name is exist")
    void addProductIncorrectly () {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Ball", 12.32);
    	String consoleOutput = outContent.toString();
    	boolean result = true;
    	assertEquals(result, consoleOutput.contains("Can't add, product already exists."));
    }
    @Test @DisplayName("Check for count is decreased by 1 or not")
    void checkForCount() {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//it should be in index 2 
    	assertEquals(2, product.findProduct("TV"));
    	
    }
  //=============================================Task 3 =============================================
    @Test @DisplayName("Remove product correctly (product name exists)s")
    void removeCorrectly() {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12.32);
    	product.addProduct("Bag", 1213.32);
    	//Removing TV 
    	product.removeProduct("TV");
    	//Removing TV in index 2, so Bag will be in index 2 instead of TV
    	assertEquals(2, product.findProduct("Bag"));
    }
    @Test @DisplayName("Remove product incorrectly (product name not exists)")
    void removeIncorrectly() {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("Bag", 1213.32);
    	//Removing TV, which doesn't contain 
//    	product.removeProduct("TV");
    	assertEquals(null, product.removeProduct("TV"));
    	String consoleOutput = outContent.toString();
    	boolean result = true;
    	assertEquals(result, consoleOutput.contains("Product not found!"));
    	
    }
    @Test @DisplayName("Check for count is decreased by 1 or not")
    void checkCountRemove() {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//it should be in index 1 because we removed Flower and count -1;
    	product.removeProduct("Flower");
    	assertEquals(1, product.findProduct("TV"));
    }
  //=============================================Task 4 =============================================
    @Test @DisplayName ("Update product correctly (product name exists, new name is not blank)")
    void updateCorrectly () {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//Update Flower in index 1 
    	product.updateProduct("Flower", "Orange", 0, 0);
    	//Find Orange and Result should be 1 because it replaced flower in index 1
    	assertEquals(1, product.findProduct("Orange"));
    }
    @Test @DisplayName("Update product incorrectly (product name not exists)")
    void updateIncorrectly () {
    	ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 12.32);
    	product.updateProduct("Orange", "Flower", 0, 0);
    	String consoleOutput = outContent.toString();
    	assertEquals("Product not found!", consoleOutput.trim());
    }
    @Test @DisplayName("Update product incorrectly (product name exists but new name is blank)")
    void updateBlankName () {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 12.32);
    	product.updateProduct("Flower", "     Orange     ", 0, 0);
    	assertEquals(1, product.findProduct("Orange"));
    }
    @Test @DisplayName("Update product incorrectly (product name exists but new price is less than 0)")
    void updatePriceIncorrectly() {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//Update Flower in index 1 
    	product.updateProduct("Flower", "Orange", -100, 0);
    	//Change price to -100 and Find Orange and Result should be 1 because it replaced flower in index 1
    	assertEquals(1, product.findProduct("Orange"));
    }
    @Test @DisplayName("Update product incorrectly (product name exists but new discount is less than 0 or greater than 100)")
    void updateDiscountIncorrectly() {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//Update Flower in index 1 
    	product.updateProduct("Flower", "Orange", 10, 12321);
    	//Change Discount to 12321 and Find Orange and Result should be 1 because it replaced flower in index 1
    	assertEquals(1, product.findProduct("Orange"));
    }
    @Test @DisplayName ("Check for count is stay the same as before the update")
    void checkCount () {
    	ProductManager product = new ProductManager();
    	product.addProduct("Ball", 12.32);
    	product.addProduct("Flower", 1213.32);
    	product.addProduct("TV", 12);
    	//Update Flower in index 1 
    	product.updateProduct("Flower", "Orange", 10, 12321);
    	//Find Orange and Result should be 1 because it replaced flower in index 1
    	assertEquals(1, product.findProduct("Orange"));
    }
}

