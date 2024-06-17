package kfriday.kfriday_homework.domain.shipment.repository;

import java.util.Optional;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

public interface ShipmentReaderRepository {

	Optional<Shipment> findShipment(Long id);

	Optional<Shipment> findByTrackingNo(String trackingNo);

}
