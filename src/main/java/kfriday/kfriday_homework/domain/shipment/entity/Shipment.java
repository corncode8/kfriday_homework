package kfriday.kfriday_homework.domain.shipment.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kfriday.kfriday_homework.domain.image.entity.Image;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Table(name = "shipment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Shipment {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "shipment_id", updatable = false)
	private Long id;

	@Column(name = "tracking_no", nullable = false)
	private String trackingNo;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, length = 10)
	private State status = State.ACTIVE;

	@OneToMany(mappedBy = "shipment", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Image> images;

	public void updateTrackingNo(String trackingNo) {
		this.trackingNo = trackingNo;
	}

	public void setDelete() {
		this.status = State.DELETED;
	}

	public enum State {
		ACTIVE, DELETED
	}

	@Builder
	public Shipment(String trackingNo, List<Image> images) {
		this.trackingNo = trackingNo;
		this.images = images;
	}
}
