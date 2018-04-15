import java.io.File;
import java.nio.charset.Charset;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import static org.junit.Assert.*;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import com.csvreader.CsvReader;
public class check {
    static Thread th = new Thread(); 
    public static void main(String[] args) throws Exception {
        WebDriver driver = new FirefoxDriver();        
        CsvReader r = new CsvReader("D://info.csv", ',',Charset.forName("utf-8"));
        r.readHeaders();
        while (r.readRecord()) {
            String name = r.get("id");
            String password = name.substring(4);
            String email = r.get("e-mail");
            driver.get("https://psych.liebes.top/st");  
            th.sleep(100);
            WebElement txtbox1 = driver.findElement(By.id("name"));
            txtbox1.sendKeys(name);
            WebElement txtbox2 = driver.findElement(By.id("pwd"));
            txtbox2.sendKeys(password);
            WebElement btn = driver.findElement(By.id("submit"));
            btn.click();
            th.sleep(100);
            WebElement text = driver.findElement(By.cssSelector("#table-main tr:first-child td:last-child"));
            String email2 = text.getText();
            assertEquals(email,email2);
        }
        r.close();
    }

}