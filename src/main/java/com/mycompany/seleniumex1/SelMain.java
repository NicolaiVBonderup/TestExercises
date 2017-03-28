
package com.mycompany.seleniumex1;

import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class SelMain {
    
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver","C:\\Users\\bepis\\Documents\\SeleniumEx1\\driver\\chromedriver.exe");
        
        // Create a new instance of the Firefox driver
        // Notice that the remainder of the code relies on the interface, 
        // not the implementation.
        WebDriver driver = new ChromeDriver();

        // And now use this to visit Google
        driver.get("http://www.polyteknisk.dk/home");
        // Alternatively the same thing can be done like this
        // driver.navigate().to("http://www.google.com");

        // Find the text input element by its name
        WebElement element = driver.findElement(By.name("q"));
        WebElement button = driver.findElement(By.className("super_search_submit_button"));

        // Enter something to search for
        element.sendKeys("Silberschatz");

        // Now submit the form. WebDriver will find the form for us from the element
        button.click();
        
        List<WebElement> imprints = driver.findElements(By.className("imprint"));
        
        for(WebElement el : imprints) {
            System.out.println(el.getText());
        }
        
        WebElement databaseBook = driver.findElement(By.xpath("//*[contains(text(), 'Database System Concepts')]"));
        
        System.out.println(databaseBook.getText());

        
        //Close the browser
        driver.quit();
    }
    
}
