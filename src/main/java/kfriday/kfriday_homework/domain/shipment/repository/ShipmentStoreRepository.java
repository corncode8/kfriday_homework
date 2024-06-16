package kfriday.kfriday_homework.domain.shipment.repository;

import java.util.Optional;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

public interface ShipmentStoreRepository {

	Shipment save(Shipment shipment);

	Optional<Shipment> findShipment(Long id);
}
