package stos.experiments.javalang.file_reading.csv_files;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StationDetail implements CsvDetail {
    private int id;
    private float latitude;
    private float longitude;
    private String name;
    private String presentationName;
    private int zone;
    private int totalLines;
    private int rail;
}
