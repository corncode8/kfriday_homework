package kfriday.kfriday_homework.domain.shipment.components;

import java.util.List;

import org.springframework.stereotype.Component;

import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ShipmentCreator {

	public static Shipment create(String trackingNo, List<Image> images) {
		return Shipment.create(trackingNo, images);
	}
}
