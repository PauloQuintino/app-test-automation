package beans;

import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
@AllArgsConstructor
public class ValuesHelper {

	public static Response response;
	private static String scenarioName;


	public String getScenarioName() {
		return scenarioName;
	}

	public void setScenarioName(String scenarioName) {
		ValuesHelper.scenarioName = scenarioName;
	}


}