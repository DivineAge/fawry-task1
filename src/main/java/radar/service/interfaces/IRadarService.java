package radar.service.interfaces;

import radar.model.Car;
import radar.model.Fine;


import java.util.Optional;

public interface IRadarService {
    Optional<Fine> process(Car car);
}
