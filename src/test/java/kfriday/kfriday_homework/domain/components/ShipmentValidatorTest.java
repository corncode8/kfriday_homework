package kfriday.kfriday_homework.domain.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.DUPLICATE_TRACKING_NUMBER;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
import kfriday.kfriday_homework.domain.shipment.components.ShipmentValidator;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentReaderRepository;

@ExtendWith(MockitoExtension.class)
public class ShipmentValidatorTest {

	@Mock
	private ShipmentReaderRepository repository;

	@InjectMocks
	private ShipmentValidator validator;

	@DisplayName("createValidate 테스트")
	@Test
	void createValidateTest() {
	    //given
		String testTeackingNo = "test";

		when(repository.findByTrackingNo(testTeackingNo)).thenReturn(Optional.empty());

	    //when
		validator.createValidate(testTeackingNo);

		//then
		assertDoesNotThrow(() -> validator.createValidate(testTeackingNo));

	}

	@DisplayName("createValidate 실패 테스트")
	@Test
	void createValidateFailTest() {
	    //given
		String testTeackingNo = "test";
		Shipment shipment = Shipment.builder()
			.trackingNo(testTeackingNo)
			.build();

		when(repository.findByTrackingNo(testTeackingNo)).thenReturn(Optional.of(shipment));

	    //when & then
		BaseException exception = assertThrows(BaseException.class, () -> validator.createValidate(testTeackingNo));
		assertEquals(DUPLICATE_TRACKING_NUMBER.getMessage(), exception.getMessage());
	}

}
