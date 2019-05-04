package stos.experiments.javalang.file_reading.csv_files;

import com.opencsv.bean.ColumnPositionMappingStrategy;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

import java.io.Reader;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MetroCsvReader<T extends CsvDetail> {

    private final CsvToBean<T> detailCsvToBean;
    private final Class<T> detail;

    MetroCsvReader(Reader reader, Class<T> detail) {
        this.detail = detail;
        ColumnPositionMappingStrategy<T> strategy = new ColumnPositionMappingStrategy<>();
        strategy.setType(detail);
        String[] columns = privateFieldsForT();
        strategy.setColumnMapping(columns);
        CsvToBeanBuilder<T> stationDetailCsvToBeanBuilder = new CsvToBeanBuilder<>(reader);
        detailCsvToBean = stationDetailCsvToBeanBuilder.withType(detail).withMappingStrategy(strategy).build();
    }

    List<T> createEntries() {
        List<T> result = detailCsvToBean.parse();
        return result;
    }

    private String[] privateFieldsForT() {
        List<String> collect = Arrays.asList(detail.getDeclaredFields())
                .stream()
                .map(Field::getName)
                .collect(Collectors.toList());
        return collect.toArray(new String[collect.size()]);
    }
}
