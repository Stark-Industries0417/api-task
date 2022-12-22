package comento.apitask.dtos;


import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class ApiResponseDto {
    private String city;
    private Double cityPm10Avg;
    private String cityPm10AvgDegree;

    private List<SeoulGuJsonDto> guList = new ArrayList<>();

    public void setCity(String city) {
        this.city = city;
    }

    public void setCityPm10Avg(Double cityPm10Avg) {
        this.cityPm10Avg = cityPm10Avg;
    }

    public void setCityPm10AvgDegree(String cityPm10AvgDegree) {
        this.cityPm10AvgDegree = cityPm10AvgDegree;
    }
}
