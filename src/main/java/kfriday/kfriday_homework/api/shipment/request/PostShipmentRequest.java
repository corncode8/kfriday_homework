package kfriday.kfriday_homework.api.shipment.request;

import java.util.ArrayList;
import java.util.List;

import kfriday.kfriday_homework.api.image.request.PostImageRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostShipmentRequest {

	private String trackingNo;
	private List<PostImageRequest> images = new ArrayList<>();
}
