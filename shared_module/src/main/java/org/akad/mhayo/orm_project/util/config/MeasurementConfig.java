import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


@Component
public class MeasurementConfig {

    @Getter
    private static String csvPath;

    @Value("${measurement.csv.path}")
    public void setCsvPath(String path) {
        MeasurementConfig.csvPath = path;
    }

}
