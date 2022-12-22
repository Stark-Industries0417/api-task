package comento.apitask.configure;



import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Component
public class OpenApiSeoul {
    @Value("${seoulUrl}")
    private String seoulUrl;

    public List<JSONObject> fetch() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(seoulUrl, String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonCityAir = (JSONObject) jsonObject.get("RealtimeCityAir");
        JSONArray jsonRows = (JSONArray) jsonCityAir.get("row");

        List<JSONObject> result = new ArrayList<>();
        for(Object r: jsonRows) {
            JSONObject row = (JSONObject) r;
            result.add(row);
        }
        return result;
    }
}
