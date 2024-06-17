package kfriday.kfriday_homework.api.shipment.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentReader;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ReadShipmentUseCase {

	private final ShipmentReader shipmentReader;

	public GetShipmentResponse get(Long id) {
		Shipment shipment = shipmentReader.findShipment(id);

		return new GetShipmentResponse(shipment);
	}
}
