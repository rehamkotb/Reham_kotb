package modules;

import bases.TestBase;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.RegisterationPage;
import utilities.ExcelConfg;
import utilities.ExcelReader;
import utilities.ExcelUtils;

import java.io.IOException;


public class RegisterationTest extends TestBase {
    RegisterationPage register;
    private int testDataCounter = 0;
    String passworfError="Your password must be at least 6 characters long. Please try another";

    @DataProvider(name="Registeration")
    public static Object[][] loginValidationData() throws IOException {
        return new ExcelReader().getExcelData(ExcelConfg.Path_ExcelSheets + ExcelConfg.File_Registration, 0, true);
    }

    @Test(dataProvider = "Registeration")
    public void validateregisteration(String FirstName, String LastName, String Maile_Mobile, String Password,
                                      String Day, String Month, String Year, String Gender, String expectedResult) throws Exception {
        testDataCounter++;

        register = new RegisterationPage(driver);
        register.createNewAccount(FirstName,LastName,Maile_Mobile,Password,Day,Month,Year,Gender);
        register.loadingWait();

    String    ActualResult= register.getErrorMessage();
        if(ActualResult.contains(expectedResult))
        {
            System.out.println("Passed and status is " + ActualResult);
            ExcelUtils.setCellData(ExcelConfg.File_Registration, ActualResult, testDataCounter, 8);
        }
        else
        {
            ActualResult=register.getErrorMessage();
            System.out.println("Failed and Error message is " +ActualResult);
            ExcelUtils.setCellData(ExcelConfg.File_Registration, ActualResult, testDataCounter, 8);

        }
        Assert.assertEquals(ActualResult,expectedResult);
    }
}
