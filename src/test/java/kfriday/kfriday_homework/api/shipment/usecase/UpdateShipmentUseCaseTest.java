package kfriday.kfriday_homework.api.shipment.usecase;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import java.util.List;

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

import kfriday.kfriday_homework.api.image.request.PostImageRequest;
import kfriday.kfriday_homework.api.shipment.request.PostShipmentRequest;
import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class UpdateShipmentUseCaseTest {

	@Autowired
	private ShipmentStore shipmentStore;

	@Autowired
	private UpdateShipmentUseCase updateShipmentUseCase;

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

	@DisplayName("update 테스트")
	@Test
	void updateTest() {
	    //given
		Long testId = 1L;
		String updateTrackingNo = "updateTestNo";
		String testFilename= "testFilename";
		List<PostImageRequest> requests = Arrays.asList(
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG)
		);

		PostShipmentRequest request = new PostShipmentRequest(updateTrackingNo, requests);

	    //when
		GetShipmentResponse result = updateShipmentUseCase.update(testId, request);

		//then
		assertNotNull(result);
		assertEquals(result.getTrackingNo(), updateTrackingNo);
		assertEquals(result.getImages().size(), request.getImages().size());
	}


}
