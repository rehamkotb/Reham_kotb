package modules;


import bases.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.LoginPage;
import pages.RegisterationPage;
import utilities.ExcelConfg;
import utilities.ExcelReader;
import utilities.ExcelUtils;

import java.io.IOException;


public class LoginTest extends TestBase {
//WebDriver driver;
    LoginPage login;

    private int testDataCounter = 0;

   @DataProvider(name="FileLogin")
    public static Object[][] loginValidationData() throws IOException {
        return new ExcelReader().getExcelData(ExcelConfg.Path_ExcelSheets + ExcelConfg.File_Login, 0, true);
    }

    @Test(dataProvider = "FileLogin")
    public void validateLogin(String username, String password, String ExpectedMessage, String ActualMessage) throws Exception {
        testDataCounter++;
        login = new LoginPage(driver);
        login.userLogin(username, password);

        ActualMessage = login.getWelcomeMessage();
        System.out.println("Welcome Message is:   " + ActualMessage);
        ExcelUtils.setCellData(ExcelConfg.File_Login, ActualMessage, testDataCounter, 3);

        if(ActualMessage.contains(ExpectedMessage))
            ExcelUtils.setCellData(ExcelConfg.File_Login, "Passed", testDataCounter, 4);
        else
            ExcelUtils.setCellData(ExcelConfg.File_Login, "Failed", testDataCounter, 4);

        Assert.assertEquals(ActualMessage,ExpectedMessage);

    }


}
