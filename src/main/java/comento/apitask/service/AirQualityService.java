package comento.apitask.service;

import comento.apitask.configure.CalcDegree;
import comento.apitask.configure.OpenAPiBusan;
import comento.apitask.configure.OpenApiSeoul;
import comento.apitask.dtos.SeoulGuJsonDto;
import comento.apitask.dtos.ApiResponseDto;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AirQualityService {
    private final OpenApiSeoul openAPiSeoul;
    private final OpenAPiBusan openAPiBusan;

    public ApiResponseDto getCityAirQuality(String city) throws ParseException {
        if(city.equals("seoul")){
            List<JSONObject> seoulRows = openAPiSeoul.fetch();
            return calcAvgDegree(city, seoulRows);
        }

        List<JSONObject> busanRows = openAPiBusan.fetch();
        return calcAvgDegree(city, busanRows);
    }

    public ApiResponseDto getCityGuAirQuality(String city, String gu) throws ParseException {
        if(city.equals("seoul")) {
            List<JSONObject> seoulRows = openAPiSeoul.fetch();
            ApiResponseDto apiResponseDto = calcAvgDegree(city, seoulRows);

            List<SeoulGuJsonDto> seoulGu = apiResponseDto.getGuList().stream()
                    .filter(seoulGuDto -> seoulGuDto.getGu().equals(gu))
                    .collect(Collectors.toList());

            apiResponseDto.getGuList().clear();
            apiResponseDto.getGuList().add(seoulGu.get(0));
            return apiResponseDto;
        }

        List<JSONObject> busanRows = openAPiBusan.fetch();
        ApiResponseDto apiResponseDto = calcAvgDegree(city, busanRows);
        List<SeoulGuJsonDto> busanGu = apiResponseDto.getGuList().stream()
                .filter(busanGuDto -> busanGuDto.getGu().equals(gu))
                .collect(Collectors.toList());

        apiResponseDto.getGuList().clear();
        apiResponseDto.getGuList().add(busanGu.get(0));
        return apiResponseDto;
    }

    private ApiResponseDto calcAvgDegree(String city, List<JSONObject> rows) {
        ApiResponseDto resDto = new ApiResponseDto();
        CalcDegree calc = new CalcDegree();

        Double pm10Avg = 0.0;
        for(JSONObject row: rows) {
            Double pm10 = city.equals("seoul") ? (Double) row.get("PM10")
                    : Double.parseDouble((String) row.get("pm10"));
            Double pm25 = city.equals("seoul") ? (Double) row.get("PM25")
                    : Double.parseDouble((String) row.get("pm25"));
            Double o3 = city.equals("seoul") ? (Double) row.get("O3")
                    : Double.parseDouble((String) row.get("o3"));
            Double no2 = city.equals("seoul") ? (Double) row.get("NO2")
                    : Double.parseDouble((String) row.get("no2"));
            Double co = city.equals("seoul") ? (Double) row.get("CO")
                    : Double.parseDouble((String) row.get("co"));
            Double so2 = city.equals("seoul") ? (Double) row.get("SO2")
                    : Double.parseDouble((String) row.get("so2"));

            String pm10Degree = calc.pm10Degree(pm10);
            String pm25Degree = calc.pm25Degree(pm25);
            String o3Degree = calc.o3Degree(o3);
            String no2Degree = calc.no2Degree(no2);
            String coDegree = calc.coDegree(co);
            String so2Degree = calc.so2Degree(so2);

            pm10Avg += pm10;

            SeoulGuJsonDto gu = SeoulGuJsonDto
                 .builder()
                 .gu((String) row.get(city.equals("seoul") ? "MSRSTE_NM" : "site"))
                 .pm10(pm10)
                 .pm25(pm25)
                 .o3(o3)
                 .no2(no2)
                 .co(co)
                 .so2(so2)
                 .pm10Degree(pm10Degree)
                 .pm25Degree(pm25Degree)
                 .o3Degree(o3Degree)
                 .no2Degree(no2Degree)
                 .coDegree(coDegree)
                 .so2Degree(so2Degree)
                 .build();

            resDto.getGuList().add(gu);
        }
        pm10Avg /= rows.size();
        String pm10AvgDegree = calc.pm10Degree(pm10Avg);

        resDto.setCity(city);
        resDto.setCityPm10Avg(pm10Avg);
        resDto.setCityPm10AvgDegree(pm10AvgDegree);
        return resDto;
    }
}
