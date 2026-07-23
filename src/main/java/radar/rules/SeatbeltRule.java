package radar.rules;

import radar.model.Car;
import radar.model.Violation;
import java.util.Optional;


public class SeatbeltRule implements Rule {

    private final int fee;

    public SeatbeltRule(int fee) {
        this.fee = fee;
    }

    @Override
    public Optional<Violation> evaluate(Car car) {
        if (!car.isSeatbeltOn()) {
            return Optional.of(new Violation(getName(), "Seatbelt not fastned", fee));
        }
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "Seatbelt";
    }
}
