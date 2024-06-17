package kfriday.kfriday_homework.api.shipment.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kfriday.kfriday_homework.api.shipment.request.PostShipmentRequest;
import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UpdateShipmentUseCase {

	private final ShipmentStore shipmentStore;

	public GetShipmentResponse update(Long id, PostShipmentRequest request) {
		List<Image> images = request.getImages().stream()
			.map(image -> Image.create(image.getFileName(), image.getType()))
			.collect(Collectors.toList());

		Shipment shipment = shipmentStore.updateShipment(id, request.getTrackingNo(), images);

		return new GetShipmentResponse(shipment);
	}
}
