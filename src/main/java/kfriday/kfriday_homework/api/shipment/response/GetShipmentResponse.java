package kfriday.kfriday_homework.api.shipment.response;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import kfriday.kfriday_homework.api.image.response.GetImageResponse;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetShipmentResponse {

	private Long id;
	private String trackingNo;

	List<GetImageResponse> images = new ArrayList<>();

	public GetShipmentResponse(Shipment shipment) {
		this.id = shipment.getId();
		this.trackingNo = shipment.getTrackingNo();
		this.images = shipment.getImages().stream()
			.map(image -> new GetImageResponse(image))
			.collect(Collectors.toList());
	}
}
