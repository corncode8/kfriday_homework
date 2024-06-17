package kfriday.kfriday_homework.api.image.request;

import kfriday.kfriday_homework.domain.image.entity.Image;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostImageRequest {

	private String fileName;
	private Image.Type type;
}
