package BestBuyAPIplaygroung;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class CreateProductActions {
	
	Response response;
	CreateProductResponse createProductResponse;
	
	public CreateProductResponse createProduct(CreateProductRequest createcreateProductRequest)
	{
		RestAssured.baseURI = Data.BASE_URL;
		RestAssured.basePath= Data.CREATE_BOOKING_BASEPATH;

		
		response = RestAssured
			.given()
			.body(createcreateProductRequest)
			.contentType(ContentType.JSON)
			.log()
			.all()
			.post().then().log().all().extract().response();

		
		createProductResponse = response.as(CreateProductResponse.class);
		return createProductResponse;
	}
	
	public boolean verifyStatusCodeAs(int expectedStatusCode)
	{
		int actualCode = response.getStatusCode();
		boolean flag = expectedStatusCode == actualCode;
		return flag;
	}
	
	public boolean verifyBookingIdGenerated() {
		int bookingId = createProductResponse.getProductid();
		boolean flag = bookingId > 0;
		return flag;
		
	}

}
