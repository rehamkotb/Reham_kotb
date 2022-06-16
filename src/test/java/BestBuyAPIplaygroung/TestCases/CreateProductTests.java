package BestBuyAPIplaygroung.TestCases;


import BestBuyAPIplaygroung.CreateProductActions;
import BestBuyAPIplaygroung.CreateProductRequest;
import Utilities.ExcelUtils;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateProductTests {
	
	@DataProvider
	public Object[][] getDataForCreateBooking() throws EncryptedDocumentException, IOException
	{
		ExcelUtils excelUtils = new ExcelUtils("CreateBooking.xlsx");
		return excelUtils.getDataForDataProvider("Sheet1");
	}


	@Test(dataProvider = "getDataForCreateBooking") 
	public void createBooking(String name, String type, String UCP, String Price, String description, String model)
	{
		

		// Create payload
		CreateProductRequest createcreateProductRequestRequest = new CreateProductRequest();
		createcreateProductRequestRequest.setName(name);
		createcreateProductRequestRequest.setType(type);
		createcreateProductRequestRequest.setUpc(Integer.parseInt(UCP));
		createcreateProductRequestRequest.setPrice(Integer.parseInt(Price));
		createcreateProductRequestRequest.setDescription(description);
		createcreateProductRequestRequest.setModel(model);

		
		CreateProductActions createProductActions = new CreateProductActions();
		createProductActions.createProduct(createcreateProductRequestRequest);
		
		Assert.assertTrue(createProductActions.verifyStatusCodeAs(200));
		Assert.assertTrue(createProductActions.verifyBookingIdGenerated());
		
	}
	
	

}
