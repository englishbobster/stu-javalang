package stos.experiments.javalang.jsonpath;

import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import stu.experiments.javalang.jsonpath.ExampleJson;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

class JsonSearcherTest {

  public static final String EXPECTED_SUB_DOC = "[{\"number\":42," +
          "\"street\":\"Builders rd\"," +
          "\"city\":\"Divvers\"," +
          "\"postcosd\":\"PE71LP\"," +
          "\"country\":\"UK\"}]";

  private static final String ANOTHER_SUB_DOC = "[{\"number\":1010," +
          "\"street\":\"Cresent av\"," +
          "\"city\":\"Milwauki\"," +
          "\"postcosd\":\"its a zip\"," +
          "\"country\":\"Murrca\"}]";

  private String jsonExample;

  @BeforeEach
  void setUp() {
    jsonExample = new ExampleJson().buildJsonExample();
  }
  
  @Test
  void using_json_path_with_data_equal_to_first_address() {
    Object read = JsonPath.parse(jsonExample).read("$..addresses.[?(@.street == 'Builders rd')]");
    String result = read.toString();
    assertThat(result, is(equalTo(EXPECTED_SUB_DOC)));
  }

  @Test
  void using_json_path_with_data_equal_to_second_adress() {
    Object read = JsonPath.parse(jsonExample).read("$..addresses.[?(@.street == 'Cresent av')]");
    String result = read.toString();
    assertThat(result, is(equalTo(ANOTHER_SUB_DOC)));
  }

  @Test
  void using_json_path_to_find_something_at_root_level() {
    Object read = JsonPath.parse(jsonExample).read("$.firstname");
    String result = read.toString();
    assertThat(result, is(equalTo("Brian")));
  }

}