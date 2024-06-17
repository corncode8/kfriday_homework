package kfriday.kfriday_homework.domain.shipment.components;

import static kfriday.kfriday_homework.api.support.response.BaseResponseStatus.NOT_FIND_SHIPMENT;

import java.util.List;

import org.springframework.stereotype.Component;

import kfriday.kfriday_homework.api.support.exceptions.BaseException;
import kfriday.kfriday_homework.domain.image.entity.Image;
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

	public Shipment updateShipment(Long id, String trackingNo, List<Image> images) {
		Shipment shipment = shipmentStoreRepository.findShipment(id)
			.orElseThrow(() -> new BaseException(NOT_FIND_SHIPMENT));
		shipment.updateShipment(trackingNo, images);

		return shipment;
	}

	public Shipment deleteShipment(Long id) {
		Shipment shipment = shipmentStoreRepository.findShipment(id)
			.orElseThrow(() -> new BaseException(NOT_FIND_SHIPMENT));
		shipment.setDelete();

		return shipment;
	}

}
