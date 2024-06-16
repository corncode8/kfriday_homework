package kfriday.kfriday_homework.domain.shipment.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.NOT_FIND_SHIPMENT;

import org.springframework.stereotype.Component;

import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentStoreRepository;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentStore {

	private final ShipmentStoreRepository shipmentStoreRepository;

	public Shipment save(Shipment shipment) {
		return shipmentStoreRepository.save(shipment);
	}

	public void updateShipment(Long id, String trackingNo) {
		Shipment shipment = shipmentStoreRepository.findShipment(id)
			.orElseThrow(() -> new BaseException(NOT_FIND_SHIPMENT));
		shipment.updateTrackingNo(trackingNo);
	}

	public void deleteShipment(Long id) {
		Shipment shipment = shipmentStoreRepository.findShipment(id)
			.orElseThrow(() -> new BaseException(NOT_FIND_SHIPMENT));
		shipment.setDelete();
	}

}
