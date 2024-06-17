package kfriday.kfriday_homework.domain.shipment.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.NOT_FIND_SHIPMENT;

import org.springframework.stereotype.Component;

import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentReaderRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentReader {

	private final ShipmentReaderRepository shipmentReaderRepository;

	public Shipment findShipment(Long id) {
		return shipmentReaderRepository.findShipment(id)
			.orElseThrow(() -> new BaseException(NOT_FIND_SHIPMENT));
	}

}
