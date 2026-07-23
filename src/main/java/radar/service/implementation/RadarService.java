package radar.service.implementation;

import radar.model.Car;
import radar.model.Fine;
import radar.model.Violation;
import radar.service.interfaces.IFineManager;
import radar.service.interfaces.IRadarService;
import radar.service.interfaces.IRule;
import radar.service.interfaces.IRuleManager;

import java.util.*;


public class RadarService implements IRadarService {

    private final IRuleManager ruleManager;
    private final IFineManager fineManager;

    public RadarService(IRuleManager ruleManager, IFineManager fineManager) {
        this.ruleManager = ruleManager;
        this.fineManager = fineManager;
    }

    @Override
    public Optional<Fine> process(Car car) {
        List<Violation> violations = new ArrayList<>();

        for (IRule rule : ruleManager.getRules()) {
            rule.evaluate(car).ifPresent(violations::add);
        }

        if (violations.isEmpty()) {
            return Optional.empty();
        }

        Fine fine = new Fine(car.getPlateNumber(), violations);
        fineManager.recordFine(fine);
        return Optional.of(fine);
    }
}
