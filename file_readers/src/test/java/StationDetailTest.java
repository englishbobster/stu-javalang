import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.hamcrest.MatcherAssert.assertThat;

class StationDetailTest {

    @Test
    void get_the_station_detail_fields() {
        assertThat(Arrays.asList(new StationDetail().privateFields())
                , Matchers.contains("id", "latitude", "longitude", "name",  "presentationName", "zone", "totalLines", "rail"));
    }
}