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
import kfriday.kfriday_homework.domain.shipment.components.ShipmentReader;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentReaderRepository;

@ExtendWith(MockitoExtension.class)
public class ShipmentReaderTest {

	@Mock
	private ShipmentReaderRepository shipmentReaderRepository;

	@InjectMocks
	private ShipmentReader shipmentReader;

	@DisplayName("findShipment 테스트")
	@Test
	void findShipmentTest() {
	    //given
		Long testId = 1L;
		String testTrackingNo = "111122223333";
		Shipment shipment = Shipment.builder()
			.trackingNo(testTrackingNo)
			.build();

		when(shipmentReaderRepository.findShipment(testId)).thenReturn(Optional.of(shipment));

	    //when
		Shipment result = shipmentReader.findShipment(testId);

		//then
		assertNotNull(result);
		assertEquals(result.getTrackingNo(), shipment.getTrackingNo());
	}

	@DisplayName("findShipment 실패 테스트")
	@Test
	void findShipmentFailTest() {
		//given
		Long testId = 1L;

		when(shipmentReaderRepository.findShipment(testId)).thenReturn(Optional.empty());

		//when & then
		BaseException exception = assertThrows(BaseException.class, () -> shipmentReader.findShipment(testId));
		assertEquals(NOT_FIND_SHIPMENT.getMessage(), exception.getMessage());
	}

}
