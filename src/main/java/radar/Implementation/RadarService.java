package radar.Implementation;

import radar.model.Car;
import radar.model.Fine;
import radar.model.Violation;
import radar.Interface.IRule;
import radar.Interface.IRadarService;

import java.util.*;


public class RadarService implements IRadarService {

    private final List<IRule> rules = new ArrayList<>();
    private final List<Fine> fines = new ArrayList<>();

    @Override
    public void addRule(IRule rule) {
        rules.add(rule);
    }

 
    public Optional<Fine> process(Car car) {
        List<Violation> violations = new ArrayList<>();

        for (IRule rule : rules) {
            rule.evaluate(car).ifPresent(violations::add);
        }

        if (violations.isEmpty()) {
            return Optional.empty();
        }

        Fine fine = new Fine(car.getPlateNumber(), violations);
        fines.add(fine);
        return Optional.of(fine);
    }

    public List<Fine> getAllFines() {
        return Collections.unmodifiableList(fines);
    }

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
