package gritbus.hipchonbackend.Api;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import gritbus.hipchonbackend.Dto.HipleDto;
import gritbus.hipchonbackend.Dto.LocalHipster.LocalHipsterListDto;
import gritbus.hipchonbackend.Dto.PlaceDto;
import gritbus.hipchonbackend.Dto.PlaceListDto;
import gritbus.hipchonbackend.Service.LocalHipsterService;
import gritbus.hipchonbackend.Service.PlaceService;
import io.swagger.annotations.ApiOperation;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/hipster")
@ApiOperation(value = "장소 API")
public class LocalHipsterController {

	private final LocalHipsterService localHipsterService;

	@Operation(summary = "홈화면 로컬 힙스터 리스트 API", description = "")
	@GetMapping("")
	public ResponseEntity<List<LocalHipsterListDto>> findAllAsList() {
		return ResponseEntity.ok(localHipsterService.findAllAsList());
	}


}
