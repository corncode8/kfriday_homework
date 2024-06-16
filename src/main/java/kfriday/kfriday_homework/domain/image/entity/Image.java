package kfriday.kfriday_homework.domain.image.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kfriday.kfriday_homework.domain.shipment.entity.Shipment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "image")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Image {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "image_id", updatable = false)
	private Long id;

	@Column(name = "filename")
	private String fileName;

	@Enumerated(value = EnumType.STRING)
	@Column(nullable = false, length = 10)
	private Type type = Type.PKG;

	public enum Type {
		PKG
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "shipment_id")
	private Shipment shipment;
}
