import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDetail {
    private int id;
    private float latitude;
    private float longitude;
    private String name;
    // We skip presentation name
    // private String presentationName;
    private int zone;
    private int totalLines;
    private int rail;
}
