package Helper;
import kong.unirest.json.JSONObject;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Value;

@Value
@AllArgsConstructor
@Getter
public class ResponseEntity {
    private StatusCode statusCode;
    private JSONObject responseData;

}
