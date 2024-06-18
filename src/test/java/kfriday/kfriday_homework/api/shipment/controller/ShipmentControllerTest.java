package kfriday.kfriday_homework.api.shipment.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import com.fasterxml.jackson.databind.ObjectMapper;

import kfriday.kfriday_homework.api.image.request.PostImageRequest;
import kfriday.kfriday_homework.api.shipment.request.PostShipmentRequest;
import kfriday.kfriday_homework.domain.image.entity.Image;
import kfriday.kfriday_homework.domain.shipment.components.ShipmentStore;
import kfriday.kfriday_homework.domain.shipment.entity.Shipment;

@Testcontainers
@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class ShipmentControllerTest {

	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private ShipmentStore shipmentStore;

	@Container
	private static GenericContainer mySqlContainer = new MySQLContainer("mysql:8.0")
		.withReuse(true);

	@BeforeEach
	void setUp() {
		String testTrackingNo = "testNo";
		Shipment shipment = Shipment.builder()
			.trackingNo(testTrackingNo)
			.build();

		shipmentStore.save(shipment);
	}

	@DisplayName("create 테스트")
	@Test
	void createTest() throws Exception{
	    //given
		String testTrackingNo = "test";
		String testFilename= "testFilename";

		List<PostImageRequest> images = Arrays.asList(
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG)
		);
		PostShipmentRequest request = new PostShipmentRequest(testTrackingNo, images);
		System.out.println("request = " + request.getTrackingNo());

	    mockMvc.perform(post("/api/shipment")
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.isSuccess").value(true))
			.andExpect(jsonPath("$.code").value(200))
			.andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
			.andExpect(jsonPath("$.result.trackingNo").value(request.getTrackingNo()))
			.andExpect(jsonPath("$.result.images.size()").value(images.size()));
	}

	@DisplayName("read 테스트")
	@Test
	void readTest() throws Exception{
		//given
		Long testId = 1L;

		mockMvc.perform(get("/api/shipment/{id}", testId)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.isSuccess").value(true))
			.andExpect(jsonPath("$.code").value(200))
			.andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
			.andExpect(jsonPath("$.result.id").value(testId))
			.andExpect(jsonPath("$.result.trackingNo").value("testNo"));
	}

	@DisplayName("update 테스트")
	@Test
	void updateTest() throws Exception{
		//given
		Long testId = 1L;
		String updateTrackingNo = "new_TrackingNo";
		String testFilename= "testFilename";
		List<PostImageRequest> images = Arrays.asList(
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG),
			new PostImageRequest(testFilename, Image.Type.PKG)
		);

		PostShipmentRequest request = new PostShipmentRequest(updateTrackingNo, images);

		mockMvc.perform(patch("/api/shipment/{id}", testId)
				.contentType(MediaType.APPLICATION_JSON)
				.content(new ObjectMapper().writeValueAsString(request))
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.isSuccess").value(true))
			.andExpect(jsonPath("$.code").value(200))
			.andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
			.andExpect(jsonPath("$.result.id").value(testId))
			.andExpect(jsonPath("$.result.trackingNo").value(updateTrackingNo));
	}

	@DisplayName("delete 테스트")
	@Test
	void deleteTest() throws Exception{
		//given
		Long testId = 1L;

		mockMvc.perform(delete("/api/shipment/{id}", testId)
				.accept(MediaType.APPLICATION_JSON))
			.andDo(print())
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.isSuccess").value(true))
			.andExpect(jsonPath("$.code").value(200))
			.andExpect(jsonPath("$.message").value("요청에 성공하였습니다."))
			.andExpect(jsonPath("$.result.state").value(Shipment.State.DELETED.name()));
	}

}
