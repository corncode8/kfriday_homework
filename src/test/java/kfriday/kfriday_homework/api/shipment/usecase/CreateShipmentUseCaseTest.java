package kfriday.kfriday_homework.api.shipment.usecase;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.DUPLICATE_TRACKING_NUMBER;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.Arrays;
import java.util.List;

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
import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class CreateShipmentUseCaseTest {

	@Autowired
	private CreateShipmentUseCase createShipmentUseCase;

	@Autowired
	private ShipmentStore shipmentStore;

	@Container
	private static GenericContainer mySqlContainer = new MySQLContainer("mysql:8.0")
		.withReuse(true);

	@DisplayName("create 테스트")
	@Test
	void createTest() {
	    //given
		String testTrackingNo = "test";
		String testFilename= "testFilename";

		List<PostImageRequest> requests = Arrays.asList(
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG)
		);

		PostShipmentRequest request = new PostShipmentRequest(testTrackingNo, requests);

	    //when
		GetShipmentResponse result = createShipmentUseCase.create(request);

		//then
		assertNotNull(request);
		assertEquals(result.getTrackingNo(), testTrackingNo);
		assertEquals(result.getImages().size(), requests.size());
	}

	@DisplayName("create 실패 테스트")
	@Test
	void createFailTest() {
		//given
		String testFilename= "testFilename";
		String testTrackingNo = "testNo";

		Shipment shipment = Shipment.builder()
			.trackingNo(testTrackingNo)
			.build();
		shipmentStore.save(shipment);

		List<PostImageRequest> requests = Arrays.asList(
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG)
		);

		PostShipmentRequest request = new PostShipmentRequest(testTrackingNo, requests);

		//when & then
		BaseException exception = assertThrows(BaseException.class, () -> createShipmentUseCase.create(request));
		assertEquals(DUPLICATE_TRACKING_NUMBER.getMessage(), exception.getMessage());
	}

}
