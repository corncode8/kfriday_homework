package kfriday.kfriday_homework.domain.shipment.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.DUPLICATE_TRACKING_NUMBER;

import org.springframework.stereotype.Component;

import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentReaderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentValidator {

	private final ShipmentReaderRepository repository;

	public void createValidate(String trackingNo) {
		Shipment shipment = repository.findByTrackingNo(trackingNo)
			.orElse(null);

		// trackingNo 중복 검사
		if (shipment != null) {
			throw new BaseException(DUPLICATE_TRACKING_NUMBER);
		}
	}
}
