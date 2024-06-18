package kfriday.kfriday_homework.api.shipment;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import kfriday.kfriday_homework.api.shipment.request.PostShipmentRequest;
import kfriday.kfriday_homework.api.shipment.response.DeleteShipmentResponse;
import kfriday.kfriday_homework.api.shipment.response.GetShipmentResponse;
import kfriday.kfriday_homework.api.shipment.usecase.CreateShipmentUseCase;
import kfriday.kfriday_homework.api.shipment.usecase.DeleteShipmentUseCase;
import kfriday.kfriday_homework.api.shipment.usecase.GetShipmentUseCase;
import kfriday.kfriday_homework.api.shipment.usecase.UpdateShipmentUseCase;
import kfriday.kfriday_homework.api.support.response.BaseResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ShipmentController {

	private final CreateShipmentUseCase createShipmentUseCase;
	private final GetShipmentUseCase getShipmentUseCase;
	private final UpdateShipmentUseCase updateShipmentUseCase;
	private final DeleteShipmentUseCase deleteShipmentUseCase;

	/**
	 * Create API
	 * [POST] /api/shipment
	 * @return BaseResponse<GetShipmentResponse>
	 */
	@PostMapping("shipment")
	public BaseResponse<GetShipmentResponse> create(@RequestBody PostShipmentRequest request) {
		GetShipmentResponse response = createShipmentUseCase.create(request);

		return new BaseResponse<>(response);
	}

	/**
	 * Read API
	 * [GET] /api/shipment/{id}
	 * @return BaseResponse<GetShipmentResponse>
	 */
	@GetMapping("shipment/{id}")
	public BaseResponse<GetShipmentResponse> getShipment(@PathVariable("id") Long id) {
		GetShipmentResponse resd = getShipmentUseCase.get(id);

		return new BaseResponse<>(resd);
	}

	/**
	 * Update API
	 * [PATCH] /api/shipment/{id}
	 * @return BaseResponse<GetShipmentResponse>
	 */
	@PatchMapping("shipment/{id}")
	public BaseResponse<GetShipmentResponse> updateShipment(@PathVariable("id") Long id,@RequestBody PostShipmentRequest request) {
		GetShipmentResponse update = updateShipmentUseCase.update(id, request);

		return new BaseResponse<>(update);
	}

	/**
	 * Delete API
	 * [DELETE] /api/shipment/{id}
	 * @return BaseResponse<DeleteShipmentResponse>
	 */
	@DeleteMapping("shipment/{id}")
	public BaseResponse<DeleteShipmentResponse> deleteShipment(@PathVariable("id") Long id) {
		DeleteShipmentResponse delete = deleteShipmentUseCase.delete(id);

		return new BaseResponse<>(delete);
	}

}
