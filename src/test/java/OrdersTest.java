import com.revature.amazon.controller.Orders;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;
 
import org.junit.Test;

public class OrdersTest { 
    
    @Test
    public void testOrderFetchesSuccess() {
        get("http://localhost:4321/orders?id=1")
        .then()
        .body("id", equalTo(1));
    }

}