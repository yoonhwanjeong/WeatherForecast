package com.example.weatherforecast.component;

import com.example.weatherforecast.model.MidTermForecastRegion;
import com.example.weatherforecast.model.VillageForecastRegion;
import com.example.weatherforecast.repository.MidTermForecastRegionRepository;
import com.example.weatherforecast.repository.VillageForecastRegionRepository;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.io.InputStreamReader;

/**
 * 지역 데이터베이스가 비어있을 경우, CSV 파일에서 값을 읽고 데이터베이스에 저장하는 Component
 */
@Component
public class DatabaseInitializationComponent implements ApplicationRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DatabaseInitializationComponent.class);
    private static final String VILLAGE_FORECAST_REGION_CSV = "/data/village_forecast_regions.csv";
    private static final String MID_TERM_FORECAST_REGION_CSV = "/data/mid_term_forecast_regions.csv";
    private final VillageForecastRegionRepository villageForecastRegionRepository;
    private final MidTermForecastRegionRepository midTermForecastRegionRepository;

    public DatabaseInitializationComponent(VillageForecastRegionRepository villageForecastRegionRepository,
                                           MidTermForecastRegionRepository midTermForecastRegionRepository) {
        this.villageForecastRegionRepository = villageForecastRegionRepository;
        this.midTermForecastRegionRepository = midTermForecastRegionRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        if (villageForecastRegionRepository.count() == 0) {
            LOGGER.info("Initializing table 'village_forecast_region'");
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader("id", "code", "firstLevel", "secondLevel", "thirdLevel", "x", "y", "longitude", "latitude")
                    .setSkipHeaderRecord(true)
                    .setNullString("")
                    .build();
            try (CSVParser csvParser = csvFormat.parse(new InputStreamReader(getClass().getResourceAsStream(VILLAGE_FORECAST_REGION_CSV)))) {
                for (CSVRecord csvRecord : csvParser) {
                    VillageForecastRegion region = new VillageForecastRegion();
                    region.setId(Long.parseLong(csvRecord.get("id")));
                    region.setCode(csvRecord.get("code"));
                    region.setFirstLevel(csvRecord.get("firstLevel"));
                    region.setSecondLevel(csvRecord.get("secondLevel"));
                    region.setThirdLevel(csvRecord.get("thirdLevel"));
                    region.setX(Integer.parseInt(csvRecord.get("x")));
                    region.setY(Integer.parseInt(csvRecord.get("y")));
                    region.setLongitude(Double.parseDouble(csvRecord.get("longitude")));
                    region.setLatitude(Double.parseDouble(csvRecord.get("latitude")));
                    villageForecastRegionRepository.save(region);
                }
                villageForecastRegionRepository.flush();
            }
        }
        if (midTermForecastRegionRepository.count() == 0) {
            LOGGER.info("Initializing table 'mid_term_forecast_region'");
            CSVFormat csvFormat = CSVFormat.DEFAULT.builder()
                    .setHeader("id", "code", "name", "type")
                    .setSkipHeaderRecord(true)
                    .build();
            try (CSVParser csvParser = csvFormat.parse(new InputStreamReader(getClass().getResourceAsStream(MID_TERM_FORECAST_REGION_CSV)))) {
                for (CSVRecord csvRecord : csvParser) {
                    MidTermForecastRegion region = new MidTermForecastRegion();
                    region.setId(Long.parseLong(csvRecord.get("id")));
                    region.setCode(csvRecord.get("code"));
                    region.setName(csvRecord.get("name"));
                    region.setType(MidTermForecastRegion.Type.valueOf(csvRecord.get("type")));
                    midTermForecastRegionRepository.save(region);
                }
                midTermForecastRegionRepository.flush();
            }
        }
    }
}
