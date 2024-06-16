package kfriday.kfriday_homework.domain.shipment.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentStoreRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShipmentCoreStoreRepository implements ShipmentStoreRepository {

	private final ShipmentJpaRepository shipmentJpaRepository;

	public Shipment save(Shipment shipment) {
		return shipmentJpaRepository.save(shipment);
	}

	public Optional<Shipment> findShipment(Long id) {
		return shipmentJpaRepository.findById(id);
	}

}
