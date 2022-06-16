package pages;

import base.PageBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.ref.SoftReference;
import java.util.List;

public class RegisterationPage extends PageBase {

    private WebDriverWait wait;

    public RegisterationPage(WebDriver driver) {
        super(driver);
        wait = new WebDriverWait(driver, 60, 500);
    }


    @FindBy(xpath = "//*[@class='_42ft _4jy0 _16jx _4jy6 _4jy2 selected _51sy']")
    public static WebElement creataNewAccountBt;
    @FindBy(name = "firstname")
    public static WebElement fisrNameField;
    @FindBy(name = "lastname")
    public static WebElement surnameField;
    @FindBy(name = "reg_email__")
    public static WebElement mobilt_mailField;
    @FindBy(name = "reg_email_confirmation__")
    public static WebElement reenter_mailField;
    @FindBy(id = "password_step_input")
    public static WebElement password;

    @FindBy(id = "day")
   public static WebElement day;
    @FindBy(id = "month")
    public static WebElement month;
    @FindBy(id = "year")
    public static WebElement year;

    @FindBy(id = "u_0_6_LT")
    public static WebElement femal;
    @FindBy(id = "u_0_7_1m")
    public static WebElement male;
    @FindBy(id = "u_0_8_WH")
    public static WebElement custom;

    @FindBy(xpath = "//label[@class='_58mt']")
    public static List<WebElement> genders;
    @FindBy(name = "websubmit")
    public static WebElement signupField;

    // ========= Validations
    @FindBy(id = "reg_error_inner")
    public static WebElement errors;
    @FindBy(xpath = "//h2[@class='uiHeaderTitle']")
    public static WebElement confirmationMessage;

    //========= Loading
    @FindBy(xpath = "//img[@class='img']")
    public static WebElement Loading;

    public void createNewAccount(String firstName, String lastName, String Mobile_email, String pass, String Day, String Month, String Year, String Gender)
    {
        creataNewAccountBt.click();
        fisrNameField.sendKeys(firstName);
        surnameField.sendKeys(lastName);
        mobilt_mailField.sendKeys(Mobile_email);

        // In case on customer enter Email
        if(Mobile_email.contains("@") && Mobile_email.contains(".com") ||  Mobile_email.contains(".net"))
        {   System.out.println("Re-enter Email");
            reenter_mailField.sendKeys(Mobile_email); }
              password.sendKeys(pass);

        Select DAY = new Select(day);
        DAY.selectByVisibleText(Day);
        Select MONTH = new Select(month);
        MONTH.selectByVisibleText(Month);
        Select YEAR = new Select(year);
        YEAR.selectByVisibleText(Year);



        //for selecting gender
        int gender_count = genders.size();
        for(int index = 0; index < gender_count; index++)
        {
            System.out.println("Gender= " + genders.get(index).getText().toString());
            if (genders.get(index).getText().equalsIgnoreCase(Gender)) {
                genders.get(index).click();
            }
        }

        signupField.click();
    }

    public String  getErrorMessage()
    {
        return errors.getText();
    }

    public String getConfirmatioMessage(){
       return confirmationMessage.getText();
    }

    public void loadingWait()
    {
            wait.until(ExpectedConditions.visibilityOf(Loading));
            wait.until((ExpectedConditions.invisibilityOf(Loading)));
            System.out.println(Loading.isDisplayed());
    }

}
