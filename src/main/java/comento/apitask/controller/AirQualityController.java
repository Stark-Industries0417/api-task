package comento.apitask.controller;


import comento.apitask.configure.response.ResponseDto;
import comento.apitask.configure.response.ResponseMessage;
import comento.apitask.configure.response.StatusCode;
import comento.apitask.dtos.ApiResponseDto;
import comento.apitask.service.AirQualityService;
import lombok.RequiredArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.*;

import java.util.NoSuchElementException;

@RestController
@RequestMapping(value = "/api/v1/air-quality")
@RequiredArgsConstructor
public class AirQualityController {

    private final AirQualityService airQualityService;

    @GetMapping(path = "/{city}")
    public ResponseEntity<ResponseDto> getSeoulGuAirQuality(
            @PathVariable(name = "city") String city,
            @RequestParam(name = "gu", required = false) String gu
    )
            throws ParseException {
        if (gu == null || gu.isEmpty()) {
            ApiResponseDto cityAirQuality = airQualityService.getCityAirQuality(city);

            return ResponseEntity.ok(ResponseDto.response(StatusCode.OK, cityAirQuality));
        }
        ApiResponseDto cityGuAirQuality = airQualityService.getCityGuAirQuality(city, gu);
        return ResponseEntity.ok(ResponseDto.response(StatusCode.OK, cityGuAirQuality));
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ResponseDto> handleNoSuchElementException() {
        return ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                .body(ResponseDto.response(StatusCode.NOT_FOUND, ResponseMessage.wrongAccept));
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ResponseDto<Object>> handleMethodException() {
        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED)
                .body(ResponseDto.response(StatusCode.WRONG_METHOD, ResponseMessage.wrongHttp));
    }
}
