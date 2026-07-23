package radar.rules;

import radar.model.Car;
import radar.model.Violation;
import java.util.Optional;


public interface Rule {
    Optional<Violation> evaluate(Car car);
    String getName();
}
