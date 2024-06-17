package kfriday.kfriday_homework.api.image.response;

import kfriday.kfriday_homework.domain.image.entity.Image;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class GetImageResponse {

	private String filename;
	private Image.Type type;

	public GetImageResponse(Image image) {
		this.filename = image.getFileName();
		this.type = image.getType();
	}
}
