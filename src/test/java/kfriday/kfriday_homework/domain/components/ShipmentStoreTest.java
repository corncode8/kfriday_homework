package kfriday.kfriday_homework.domain.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.NOT_FIND_SHIPMENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentStoreRepository;

@ExtendWith(MockitoExtension.class)
public class ShipmentStoreTest {

	@Mock
	private ShipmentStoreRepository shipmentStoreRepository;

	@InjectMocks
	private ShipmentStore shipmentStore;

	@DisplayName("save 테스트")
	@Test
	void saveTest() {
	    //given
		String testTrackingNo = "111122223333";
		Shipment shipment = Shipment.builder()
			.trackingNo(testTrackingNo)
			.build();

		when(shipmentStoreRepository.save(shipment)).thenReturn(shipment);

	    //when
		Shipment result = shipmentStore.save(shipment);

		//then
		assertNotNull(result);
		assertEquals(result.getTrackingNo(), shipment.getTrackingNo());
	}

	@DisplayName("updateShipment 테스트")
	@Test
	void updateShipmentTest() {
	    //given
		Long testId = 1L;
		String trackingNo = "111122223333";

		Shipment shipment = Shipment.builder()
			.trackingNo("test")
			.build();
		when(shipmentStoreRepository.findShipment(testId)).thenReturn(Optional.of(shipment));
		// update 전 trackingNo 검증
		assertEquals(shipment.getTrackingNo(), shipment.getTrackingNo());

	    //when
		shipmentStore.updateShipment(testId, trackingNo);

	    //then
		assertEquals(shipment.getTrackingNo(), trackingNo);
	}

	@DisplayName("updateShipment 실패 테스트")
	@Test
	void updateShipmentFailTest() {
	    //given
		Long testId = 1L;
		String trackingNo = "111122223333";

		when(shipmentStoreRepository.findShipment(testId)).thenReturn(Optional.empty());

		//when & then
		BaseException exception = assertThrows(BaseException.class, () -> shipmentStore.updateShipment(testId, trackingNo));
		assertEquals(NOT_FIND_SHIPMENT.getMessage(), exception.getMessage());
	}

	@DisplayName("deleteShipment 테스트")
	@Test
	void deleteShipmentTest() {
	    //given
		Long testId = 1L;

		Shipment shipment = Shipment.builder()
			.trackingNo("111122223333")
			.build();
		when(shipmentStoreRepository.findShipment(testId)).thenReturn(Optional.of(shipment));
		// delete 전 status 검증
	    assertEquals(shipment.getStatus(), Shipment.State.ACTIVE);

	    //when
		shipmentStore.deleteShipment(testId);

	    //then
		assertEquals(shipment.getStatus(), Shipment.State.DELETED);
	}

	@DisplayName("deleteShipment 실패 테스트")
	@Test
	void deleteShipmentFailTest() {
		//given
		Long testId = 1L;

		when(shipmentStoreRepository.findShipment(testId)).thenReturn(Optional.empty());

		//when & then
		BaseException exception = assertThrows(BaseException.class, () -> shipmentStore.deleteShipment(testId));
		assertEquals(NOT_FIND_SHIPMENT.getMessage(), exception.getMessage());
	}

}
