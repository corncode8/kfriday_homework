package kfriday.kfriday_homework.domain.shipment.infrastructure;

import java.util.Optional;

import org.springframework.stereotype.Repository;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import kfriday.kfriday_homework.domain.shipment.repository.ShipmentReaderRepository;
import lombok.RequiredArgsConstructor;

@Repository
@RequiredArgsConstructor
public class ShipmentCoreReaderRepository implements ShipmentReaderRepository {

	public final ShipmentJpaRepository shipmentJpaRepository;

	public Optional<Shipment> findShipment(Long id) {
		return shipmentJpaRepository.findById(id);
	}

	public Optional<Shipment> findByTrackingNo(String trackingNo) {
		return shipmentJpaRepository.findByTrackingNo(trackingNo);
	}
}
