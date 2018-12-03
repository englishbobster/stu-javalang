import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RouteDetail implements CsvDetail {
    private int aStation;
    private int zStation;
    private int line;
}
