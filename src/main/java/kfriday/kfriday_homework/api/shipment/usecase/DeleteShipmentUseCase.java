package kfriday.kfriday_homework.api.shipment.usecase;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kfriday.kfriday_homework.api.shipment.response.DeleteShipmentResponse;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class DeleteShipmentUseCase {

	private final ShipmentStore shipmentStore;

	public DeleteShipmentResponse delete(Long id) {
		Shipment shipment = shipmentStore.deleteShipment(id);

		return new DeleteShipmentResponse(shipment);
	}
}
