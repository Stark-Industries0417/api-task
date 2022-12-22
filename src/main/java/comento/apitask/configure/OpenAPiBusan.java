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
public class OpenAPiBusan {

    @Value("${busanUrl}")
    private String busanUrl;

    public List<JSONObject> fetch() throws ParseException {
        RestTemplate restTemplate = new RestTemplate();
        String jsonString = restTemplate.getForObject(busanUrl, String.class);
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(jsonString);
        JSONObject jsonStation = (JSONObject) jsonObject.get("getAirQualityInfoClassifiedByStation");
        JSONObject jsonBody = (JSONObject) jsonStation.get("body");
        JSONObject jsonItems = (JSONObject) jsonBody.get("items");

        JSONArray jsonItem = (JSONArray) jsonItems.get("item");
        List<JSONObject> result = new ArrayList<>();
        for(Object json: jsonItem) {
            result.add((JSONObject) json);
        }
        return result;
    }
}
