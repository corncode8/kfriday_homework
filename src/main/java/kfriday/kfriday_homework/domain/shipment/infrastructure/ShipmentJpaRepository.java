package kfriday.kfriday_homework.domain.shipment.infrastructure;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

public interface ShipmentJpaRepository extends JpaRepository<Shipment, Long> {

	Optional<Shipment> findByTrackingNo(String trackingNo);
}
