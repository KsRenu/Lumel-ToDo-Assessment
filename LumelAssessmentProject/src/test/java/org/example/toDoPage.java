package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Action;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class toDoPage {

    public toDoPage(WebDriver driver) {
        PageFactory.initElements(driver,this);
    }


    @FindBy(xpath = "//p[text()='Double-click to edit a todo']")
    WebElement txtToDo;

    @FindBy(xpath = "//input[contains(@class, 'new-todo ng')]")
    WebElement txtTaskinput;

    @FindBy(xpath = "(//input[@class='toggle'])[1]")
    WebElement radioComplete;

    @FindBy(xpath = "(//input[@class='toggle'])[2]")
    WebElement radio2Complete;

    @FindBy(xpath = "(//input[@class='toggle'])[1]/following::label[text()='Complete the Scenario 1 - Adding tasks']")
    WebElement labelValidateAddedItems;

    @FindBy(xpath = "(//input[@class='toggle'])[1]/following::label[text()='Complete the Scenario 2 - Adding tasks']")
    WebElement labelFilterCount;

    @FindBy(xpath = "//*[@class='completed']")
    WebElement txtCompleted;

    @FindBy(xpath = "(//*[@class='destroy'])[2]")
    WebElement btnDelete;

    @FindBy(linkText = "All")
    WebElement linkAll;

    @FindBy(linkText = "Active")
    WebElement linkActive;

    @FindBy(linkText = "Completed")
    WebElement linkCompleted;






   // 1.Add to-do is working. (Verify if the item is added to the list )
  public void addTask(WebDriver driver){

      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

      //wait till the page is loaded
      wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//h4[normalize-space()='Official Resources']")));


      //click the task textbox an add the items
      //wait.until(ExpectedConditions.elementToBeClickable(txtTaskinput));
      wait.until(ExpectedConditions.visibilityOf(txtTaskinput));
      txtTaskinput.click();
      txtTaskinput.sendKeys("Complete the Scenario 1 - Adding tasks");
      txtTaskinput.sendKeys(Keys.RETURN);
      txtTaskinput.sendKeys("Complete the Scenario 2 - Adding tasks");
      txtTaskinput.sendKeys(Keys.RETURN);
      txtTaskinput.sendKeys("Complete the Scenario 3 - Adding tasks");
      txtTaskinput.sendKeys(Keys.RETURN);
      Assert.assertEquals(labelValidateAddedItems.getText(),"Complete the Scenario 1 - Adding tasks");


      System.out.println("Task is added Successfully");
  }
//2.Marking an item as complete works. (Verify if the item is crossed out and verify the counter on the bottom-left)
  public void completeTask(WebDriver driver){
      //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));
      //wait.until(ExpectedConditions.elementToBeClickable(radioComplete));
      radioComplete.click();
      //wait.until(ExpectedConditions.elementToBeClickable(radio2Complete));
      radio2Complete.click();
      System.out.println("Task is marked as completed Successfully");

  }
  //3.Delete a to-do (Verify if the item is removed from the list)
  public void deleteTask(WebDriver driver) {
      Actions action = new Actions(driver);
      action.moveToElement(btnDelete).perform();
      btnDelete.click();
      System.out.println("Task is marked as deleted Successfully");
  }
//4.Filtering a to-do is working (Click on the Active button and verify if it shows the Active items. Click on Completed and verify if it shows the completed items
  public void filterTask(WebDriver driver) {
      WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(50));

      wait.until(ExpectedConditions.elementToBeClickable(linkAll));
        linkAll.click();
      wait.until(ExpectedConditions.elementToBeClickable(linkActive));
        linkActive.click();
      wait.until(ExpectedConditions.elementToBeClickable(linkCompleted));
        linkCompleted.click();
        wait.until(ExpectedConditions.invisibilityOf(labelFilterCount));
      List<WebElement> labels = driver.findElements(By.xpath("(//input[@class='toggle'])[1]/following::label"));




      Assert.assertEquals(labels.size(),1);
      System.out.println("Tasks are marked as filtered Successfully");
  }
}
