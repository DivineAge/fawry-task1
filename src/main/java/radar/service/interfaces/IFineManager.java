package radar.service.interfaces;

import radar.model.Fine;

import java.util.List;
import java.util.Map;

public interface IFineManager {
    void recordFine(Fine fine);
    List<Fine> getAllFines();
    Map<String, Long> getViolationStats();
}
