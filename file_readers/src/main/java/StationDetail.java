import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDetail {
    private int id;
    private float latitude;
    private float longitude;
    private String name;
    private String presentationName;
    private int zone;
    private int totalLines;
    private int rail;

    // Use reflection to return a list of the fields
    String[] privateFields() {
        List<String> collect = Arrays.asList(StationDetail.class.getDeclaredFields())
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        return collect.toArray(new String[collect.size()]);
    }
}
