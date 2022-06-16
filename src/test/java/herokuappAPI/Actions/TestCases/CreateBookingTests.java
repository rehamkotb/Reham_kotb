package herokuappAPI.Actions.TestCases;


import herokuappAPI.Actions.BookingDates;
import herokuappAPI.Actions.CreateBookingActions;
import herokuappAPI.Actions.CreateBookingRequest;
import Utilities.ExcelUtils;
import org.apache.poi.EncryptedDocumentException;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.io.IOException;

public class CreateBookingTests {
	
	@DataProvider
	public Object[][] getDataForCreateBooking() throws EncryptedDocumentException, IOException
	{
		ExcelUtils excelUtils = new ExcelUtils("CreateBooking.xlsx");
		return excelUtils.getDataForDataProvider("Sheet1");
	}


	@Test(dataProvider = "getDataForCreateBooking") 
	public void createBooking(String firstName, String lastName, String totalPrice, String depositPaid, String checkIn, String checkOut, String addNeeds, String scenarioName)
	{
		

		// Create payload
		CreateBookingRequest createBookingRequest = new CreateBookingRequest();
		createBookingRequest.setFirstname(firstName);
		createBookingRequest.setLastname(lastName);
		createBookingRequest.setAdditionalneeds(addNeeds);
		createBookingRequest.setDepositpaid(Boolean.parseBoolean(depositPaid));
		createBookingRequest.setTotalprice(Integer.parseInt(totalPrice));
		
		BookingDates bookingdates = new BookingDates();
		bookingdates.setCheckin(checkIn);
		bookingdates.setCheckout(checkOut);
		createBookingRequest.setBookingdates(bookingdates);
		
		CreateBookingActions createBookingActions = new CreateBookingActions();
		createBookingActions.createBooking(createBookingRequest);
		
		Assert.assertTrue(createBookingActions.verifyStatusCodeAs(200));
		Assert.assertTrue(createBookingActions.verifyBookingIdGenerated());	
		
	}
	
	

}
