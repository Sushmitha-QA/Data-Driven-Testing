import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.io.IOException;
import java.time.Duration;
import java.util.List;

public class FD_Calculator {

    public static void main(String[] args) throws IOException {

        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        driver.get("https://www.cit.com/cit-bank/resources/calculators/certificate-of-deposit-calculator");

        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("document.body.style.zoom='65%'");

        WebElement cookie = driver.findElement(By.xpath(" //button[@id ='onetrust-reject-all-handler']"));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(cookie)).click();

        String file = System.getProperty("user.dir") + "\\src\\testdata\\calculate data.xlsx";
        int rows = ExcelUtils.getRowCount(file, "Sheet1");
        //reading data from excel
        for (int i = 1; i <= rows; i++) {

            String depositAmount = ExcelUtils.getCellData(file, "Sheet1", i, 0);
            System.out.println("depositAmount = " + depositAmount);
            String length = ExcelUtils.getCellData(file, "Sheet1", i, 1);
            String interestRate = ExcelUtils.getCellData(file, "Sheet1", i, 2);
            String compounding = ExcelUtils.getCellData(file, "Sheet1", i, 3);
            System.out.println("compounding = " + compounding);
            String total = ExcelUtils.getCellData(file, "Sheet1", i, 4);

            //passing data to application
            WebElement da = driver.findElement(By.xpath("//input[@id='mat-input-0']"));
            da.clear();
            da.sendKeys(depositAmount);
            WebElement len = driver.findElement(By.xpath("//input[@id='mat-input-1']"));
            len.clear();
            len.sendKeys(length);
            WebElement ir = driver.findElement(By.xpath("//input[@id='mat-input-2']"));
            ir.clear();
            ir.sendKeys(interestRate);
            WebElement element = driver.findElement(By.xpath("//div[@class='mat-select-arrow ng-tns-c109-4']"));
            js.executeScript("arguments[0].click();", element);
            List<WebElement> options = driver.findElements(By.xpath("//span[@class='mat-option-text']"));
            for (WebElement option : options) {

                if (option.getText().equalsIgnoreCase(compounding)) {
                    option.click();

                }
            }

            //validation
            WebElement button = driver.findElement(By.xpath("//button[@id='CIT-chart-submit']//div[@class='mdc-button__ripple']"));
            js.executeScript("arguments[0].click();", button);
            String calvalue = driver.findElement(By.xpath("//span[@id='displayTotalValue']")).getText();

            String value = calvalue.replaceAll("[$,]", "");
            System.out.println("value = " + value);
            System.out.println("total = " + total);
            if (value.equalsIgnoreCase(total)) {
                ExcelUtils.setCellData(file, "Sheet1", i, 6, "pass");
                ExcelUtils.fillGreenColor(file, "Sheet1", i, 6);
            } else {
                ExcelUtils.setCellData(file, "Sheet1", i, 6, "Fail");
                ExcelUtils.fillRedColor(file, "Sheet1", i, 6);
            }


        }


        driver.close();

    }
}
