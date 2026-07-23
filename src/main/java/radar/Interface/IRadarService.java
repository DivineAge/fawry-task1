package radar.Interface;

import radar.model.Car;
import radar.model.Fine;
import radar.Interface.IRule;

import java.util.List;
import java.util.Map;
import java.util.Optional;

public interface IRadarService {
    void addRule(IRule rule);
    Optional<Fine> process(Car car);
    List<Fine> getAllFines();
    Map<String, Long> getViolationStats();
}
