import org.example.toDoPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BasePage {

    WebDriver driver;
    @BeforeTest
    public void setup() throws InterruptedException {
     driver = new ChromeDriver();
     driver.manage().window().maximize();
     driver.get("https://todomvc.com/examples/angular/dist/browser");
     //Thread.sleep(10000);

    }

    @Test
    public void toDo(){
        toDoPage toDo = new toDoPage(driver);
        toDo.addTask(driver);
        toDo.completeTask(driver);
        toDo.deleteTask(driver);
        toDo.filterTask(driver);
    }

    @AfterTest
    public void tearDown(){
        driver.quit();
    }
}
