import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.StringReader;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MetroCsvReaderTest {
    String stationLines = "40,51.5392,-0.1426,\"Camden Town\",\"Camden<br />Town\",2,1,0\n"
                      + "42,51.5051,-0.0209,\"Canary Wharf\",\"Canary<br />Wharf\",2,2,0";
    private CsvToBean<StationDetail> reader;

    @BeforeEach
    void setUp() {
        ColumnPositionMappingStrategy<StationDetail> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(StationDetail.class);

        String[] columns = new String[]{"id", "latitude", "longitude", "name", "presentation", "zone", "totalLines", "rail"};
        strategy.setColumnMapping(columns);

        reader = new CsvToBeanBuilder<StationDetail>(new StringReader(stationLines))
                .withType(StationDetail.class).withMappingStrategy(strategy).build();
    }

    @Test
    void should_read_a_line_of_a_csv_file() {
        List<StationDetail> stationDetails = reader.parse();
        assertThat(stationDetails.size(), is(2));
        assertThat(stationDetails.get(0).getName(), is("Camden Town"));
    }
}