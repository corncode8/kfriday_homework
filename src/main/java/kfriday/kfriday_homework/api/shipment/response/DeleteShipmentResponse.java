package kfriday.kfriday_homework.api.shipment.response;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DeleteShipmentResponse {

	private Shipment.State state;

	public DeleteShipmentResponse(Shipment shipment) {
		this.state = shipment.getStatus();
	}
}
