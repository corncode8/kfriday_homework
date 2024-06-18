package kfriday.kfriday_homework.api.shipment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class GetShipmentUseCaseTest {

	@Autowired
	private GetShipmentUseCase getShipmentUseCase;

	@Autowired
	private ShipmentStore shipmentStore;

	@Container
	private static GenericContainer mySqlContainer = new MySQLContainer("mysql:8.0")
		.withReuse(true);

	@BeforeEach
	void setUp() {
		String testTrackingNo = "testNo";
		Shipment shipment = Shipment.builder()
			.trackingNo(testTrackingNo)
			.build();
		shipmentStore.save(shipment);
	}

	@DisplayName("get 테스트")
	@Test
	void getTest() {
	    //given
		Long testId = 1L;

		//when
		GetShipmentResponse result = getShipmentUseCase.get(testId);

	    //then
		assertNotNull(result);
		assertEquals(result.getId(), testId);
	}

}
