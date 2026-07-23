package radar.Interface;

import radar.model.Car;
import radar.model.Violation;
import java.util.Optional;


public interface IRule {
    Optional<Violation> evaluate(Car car);
    String getName();
}
