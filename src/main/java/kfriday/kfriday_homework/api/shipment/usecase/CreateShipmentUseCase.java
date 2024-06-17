package kfriday.kfriday_homework.api.shipment.usecase;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kfriday.kfriday_homework.api.shipment.request.PostShipmentRequest;
import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentCreator;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentValidator;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CreateShipmentUseCase {

	private final ShipmentStore shipmentStore;
	private final ShipmentValidator validator;

	public GetShipmentResponse create(PostShipmentRequest request) {
		validator.createValidate(request.getTrackingNo());

		List<Image> images = request.getImages().stream()
			.map(image -> Image.create(image.getFileName(), image.getType()))
			.collect(Collectors.toList());

		Shipment shipment = ShipmentCreator.create(request.getTrackingNo(), images);

		shipmentStore.save(shipment);

		return new GetShipmentResponse(shipment);
	}
}
