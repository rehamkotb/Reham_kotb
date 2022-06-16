package herokuappAPI.Actions;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateBookingActions {
	
	Response response;
	CreateBookingResponse createBookingResponse;
	
	public CreateBookingResponse createBooking(CreateBookingRequest createBookingRequest)
	{
		RestAssured.baseURI = Data.BASE_URL;
		RestAssured.basePath= Data.CREATE_BOOKING_BASEPATH;

		
		response = RestAssured
			.given()
			.body(createBookingRequest)
			.contentType(ContentType.JSON)
			.log()
			.all()
			.post().then().log().all().extract().response();

		
		createBookingResponse = response.as(CreateBookingResponse.class);
		return createBookingResponse;
	}
	
	public boolean verifyStatusCodeAs(int expectedStatusCode)
	{
		int actualCode = response.getStatusCode();
		boolean flag = expectedStatusCode == actualCode;
		return flag;
	}
	
	public boolean verifyBookingIdGenerated() {
		int bookingId = createBookingResponse.getBookingid();
		boolean flag = bookingId > 0;
		return flag;
		
	}

}
