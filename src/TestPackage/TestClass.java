package TestPackage;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

import org.testng.Reporter;
import org.testng.annotations.Test;

import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

public class TestClass {

	public static ValidatableResponse getRequest(String endpoint) {
		return given()
				.get(endpoint).then().contentType(ContentType.JSON);
	}

	
	@Test
	public void testContains5Comment() {
		ValidatableResponse response = getRequest("https://jsonplaceholder.typicode.com/comments");
				
		response.assertThat().body("findAll { it.postId == 40 }.size()", is(5));	
		Reporter.log("Make sure that post with id 40 contains 5 comments");
	}
	
	@Test
	public void testContainsExpectedComment() {
		String name = "pariatur aspernatur nam atque quis";
		String email = "Cooper_Boehm@damian.biz";
		String body = "veniam eos ab voluptatem in fugiat ipsam quis\nofficiis non qui\nquia ut id voluptates et a molestiae commodi quam\ndolorem enim soluta impedit autem nulla";
		
		ValidatableResponse response = getRequest("https://jsonplaceholder.typicode.com/comments");
		
		response.assertThat().body("findAll { it.postId == 40 && it.id ==199 }.name[0]", equalTo(name));	
		response.assertThat().body("findAll { it.postId == 40 && it.id ==199 }.email[0]", equalTo(email));	
		response.assertThat().body("findAll { it.postId == 40 && it.id ==199 }.body[0]", equalTo(body));	
	
		Reporter.log("Make sure that post with id 40 contains the expected comment");
	}
	
}
