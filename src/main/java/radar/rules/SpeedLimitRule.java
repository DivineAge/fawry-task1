package radar.rules;

import radar.model.Car;
import radar.model.CarType;
import radar.model.Violation;
import java.util.Optional;


public class SpeedLimitRule implements Rule {

    private final CarType carType;
    private final int maxSpeedKmh;
    private final int fee;

    public SpeedLimitRule(CarType carType, int maxSpeedKmh, int fee) {
        this.carType = carType;
        this.maxSpeedKmh = maxSpeedKmh;
        this.fee = fee;
    }

    @Override
    public Optional<Violation> evaluate(Car car) {
        if (car.getCarType() == carType && car.getSpeedKmh() > maxSpeedKmh) {
            String desc = String.format("speed of %d exceeded max allowed %d", car.getSpeedKmh(), maxSpeedKmh);
            return Optional.of(new Violation(getName(), desc, fee));
        }
        return Optional.empty();
    }

    @Override
    public String getName() {
        return "Speed Limit - " + carType.name().charAt(0)+ carType.name().substring(1).toLowerCase();
    }
}
