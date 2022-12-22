package comento.apitask.dtos;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class SeoulGuJsonDto {
    private String gu;
    private Double pm10;
    private Double pm25;
    private Double o3;
    private Double no2;
    private Double co;
    private Double so2;

    private String pm10Degree;
    private String pm25Degree;
    private String o3Degree;
    private String no2Degree;
    private String coDegree;
    private String so2Degree;
}
