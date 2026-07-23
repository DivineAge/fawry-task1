package radar.service.implementation;

import radar.model.Fine;
import radar.model.Violation;
import radar.service.interfaces.IFineManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class FineManager implements IFineManager {
    private final List<Fine> fines = new ArrayList<>();

    @Override
    public void recordFine(Fine fine) {
        fines.add(fine);
    }

    @Override
    public List<Fine> getAllFines() {
        return Collections.unmodifiableList(fines);
    }

    @Override
    public Map<String, Long> getViolationStats() {
        Map<String, Long> stats = new LinkedHashMap<>();
        for (Fine fine : fines) {
            for (Violation v : fine.getViolations()) {
                stats.merge(v.getRuleName(), 1L, Long::sum);
            }
        }
        return stats;
    }
}
