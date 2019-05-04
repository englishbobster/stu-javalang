package stos.experiments.javalang.file_reading.csv_files;

import org.junit.jupiter.api.Test;
import stos.experiments.javalang.file_reading.csv_files.MetroCsvReader;
import stos.experiments.javalang.file_reading.csv_files.RouteDetail;
import stos.experiments.javalang.file_reading.csv_files.StationDetail;

import java.io.StringReader;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class MetroCsvReaderTest {
    private static final String stationfile = "40,51.5392,-0.1426,\"Camden Town\",\"Camden<br />Town\",2,1,0\n"
                      + "42,51.5051,-0.0209,\"Canary Wharf\",\"Canary<br />Wharf\",2,2,0";

    private static final String routes = "11,163,1\n" + "11,212,1";

    @Test
    void should_read_a_line_of_stations_file() {
        MetroCsvReader<StationDetail> reader = new MetroCsvReader<>(new StringReader(stationfile), StationDetail.class);
        List<StationDetail> stationDetails = reader.createEntries();
        assertThat(stationDetails.size(), is(2));
        assertThat(stationDetails.get(0).getName(), is("Camden Town"));
        assertThat(stationDetails.get(0).getId(), is(40));
    }

    @Test
    void should_read_a_line_of_routes_file() {
        MetroCsvReader<RouteDetail> reader = new MetroCsvReader<>(new StringReader(routes), RouteDetail.class);
        List<RouteDetail> routeDetails = reader.createEntries();
        assertThat(routeDetails.size(), is(2));
        assertThat(routeDetails.get(0).getAStation(), is(11));
        assertThat(routeDetails.get(0).getZStation(), is(163));
    }
}
