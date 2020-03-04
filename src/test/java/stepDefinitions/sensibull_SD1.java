package stepDefinitions;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.Date;
import java.util.concurrent.TimeUnit;

public class sensibull_SD1 {

    public WebDriver driver;

    @Given("User is on \"(.*?)\" homepage")
    public void user_on_homepage(String baseURL) throws Throwable {


        System.setProperty("webdriver.gecko.driver", "src/test/java/drivers/linux/geckodriver");
        //driver=new FirefoxDriver();

        System.setProperty("webdriver.chrome.driver","src/test/java/drivers/linux/chromedriver");
        driver=new ChromeDriver();



        Date dateSelector = new Date();
        Date dayAfter = new Date(dateSelector.getTime() + TimeUnit.DAYS.toMillis(10));
        String accessToken =
                "\"2|1:0|10:1583298902|12:access_token|88:MDJjNjc5NzcyNjg1N2E2YzkxZDI4YjRkZjgxYjQ2NjY2YTBmZTU3ZmQ0NzUxMTBhNzAxYzYwYmQ4ZjZjYjE4Yg==|98f8e2b38cb0571966cb96f7fa69560b63933ce355e4b38a11cac3121f05bd2a\"";
        String apiKey = "uf8cguv719djhxfc";
        String publicToken = "not_applicable";

        // Set Cookie code
        Cookie publicToken_Cookie = new Cookie("public_token", publicToken, "/", dayAfter);
        Cookie apiKey_Cookie = new Cookie("api_key", apiKey, "/", dayAfter);
        Cookie builder_Cookie = new Cookie.Builder("access_token", accessToken).domain(".sensibull.com").build();


        driver.get(baseURL);

        driver.manage().addCookie(builder_Cookie);
        driver.manage().addCookie(publicToken_Cookie);
        driver.manage().addCookie(apiKey_Cookie);
        Thread.sleep(1000);
        driver.navigate().refresh();
        driver.manage().window().maximize();
    }

    @Then("Click on strategies wizard")
    public void strategies_Wizard()throws Throwable{
        driver.manage().timeouts().implicitlyWait(10,TimeUnit.SECONDS) ;
        driver.findElement(By.xpath("//div[contains(text(),'Strategies Wizard')]")).click();

    }
    @Then("^Add stock as \"(.*?)\" and View as \"(.*?)\"$")
    public void addValue(String stock, String view) throws Throwable{
        Thread.sleep(1000);
        driver.findElement(By.xpath ("//div[contains(@name,'instrumentSymbol')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body[contains(@class,'sn-l fontLoaded')]//div//div//div//div//div[1]//span[1]//div[1]//div[1]//div[contains(text(),"+stock+")]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@name,'marketView')]")).click();
        Thread.sleep(1000);
        driver.findElement(By.xpath("//body[contains(@class,'sn-l fontLoaded')]/div/div/div/div/div[2]/span[1]/div[1]/div[1]/div[contains(text(),"+view+")]")).click();
    }
    @When("User clicks on Go button")
    public void goButton() throws Throwable{
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'style__StyledButtonContainer-cRdqaf clILod')]//div//button//div//span[contains(text(),'Go')]")).click();

    }
    @Then("User need to click on first \"(.*?)\" Trade button from displayed result")
    public void clickOnTrade_Button(String orderTradeLink) throws Throwable{
        Thread.sleep(1000);
        driver.findElement(By.xpath("//div[contains(@class,'rt-table')]/div[2]/div["+orderTradeLink+"]/div[1]//div[7]/div[1]//div//div/div/span[contains(text(),'Trade')]")).click();
    }
    @Then("User need to fill lots as \"(.*?)\" and Click on AnalyseOption Button")
    public void fillLots(String lotsCount) throws Throwable{

        System.out.println("lotscount =" +lotsCount);

        Thread.sleep(1000);
     /*   WebElement lotsValue=driver.findElement(By.xpath("//input[@value='NaN']"));
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].value= "+lotsCount+";", lotsValue);

       */
        Thread.sleep(5000);
        driver.findElement(By.xpath("//input[@value='NaN']")).sendKeys(lotsCount);

       Thread.sleep(5000);
       driver.findElement(By.xpath("//div[contains(@class,'reactTableStyle__DataMetric-eFwWHS gTAouA')]//div//button//div//div/span[contains(text(),'Analyse Options')]")).click();
    }
    @Then("Verify option details are same for lots as \"(.*?)\" on both pages and confirm quantity matches")
    public void compareQuantity(String updatedLots) throws Throwable{
        String winHandleBefore = driver.getWindowHandle();
        for(String winHandle:driver.getWindowHandles()) {
            System.out.println(winHandle);
            driver.switchTo().window(winHandle);

        }
            Thread.sleep(1000);
            String firstValue = driver.findElement(
                    By.xpath("(//div[@class='style__LegInstrumentWrapper-uLbkk jYRQQT']/div[2])[1]")).getText();

            String newVal1 = "";
            for (int i = 0; i < firstValue.length() - 1; i++) {
                char ch1 = firstValue.charAt(i);
                newVal1 = "" + ch1;
            }
            //System.out.println(newVal1);

            Thread.sleep(1000);
            String secondValue = driver.findElement(
                    By.xpath("(//div[@class='style__LegInstrumentWrapper-uLbkk jYRQQT']/div[2])[2]")).getText();
            String newVal2 = "";
            for (int j = 0; j < secondValue.length() - 1; j++) {
                char ch2 = firstValue.charAt(j);
                newVal2 = "" + ch2;
            }
            //  System.out.println(newVal2);

            if ((updatedLots.equals(newVal1)) && (updatedLots.equals(newVal2))) {
                System.out.println("Test Scenarios Passed");
            } else {
                System.out.println("Test Scenarios Failed");
            }
        driver.close();
        driver.switchTo().window(winHandleBefore);

            System.out.println(driver.getTitle());

    }




}


