package radar;

import radar.model.Car;
import radar.model.CarType;
import radar.model.Fine;
import radar.service.interfaces.IRule;
import radar.service.interfaces.IRadarService;
import radar.service.interfaces.IRuleManager;
import radar.service.interfaces.IFineManager;
import radar.service.implementation.RadarService;
import radar.service.implementation.RuleManager;
import radar.service.implementation.FineManager;
import radar.service.implementation.SpeedLimitRule;
import radar.service.implementation.SeatbeltRule;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Optional;


public class Main {

    private static final String DIVIDER = "----------------------------";

    private static void printFine(Fine fine) {
        System.out.println("Traffic for car " + fine.getPlateNumber());
        System.out.println("Total amount: " + fine.getTotalAmount() + " EGP");
        System.out.println("Violations:");
        for (var v : fine.getViolations()) {
            System.out.println("- " + v.getDescription() + " : " + v.getFee() + " EGP");
        }
    }

    public static void main(String[] args) {

        IRuleManager ruleManager = new RuleManager();
        ruleManager.addRule(new SpeedLimitRule(CarType.PRIVATE, 80,  300));
        ruleManager.addRule(new SpeedLimitRule(CarType.TRUCK,   60,  500));
        ruleManager.addRule(new SpeedLimitRule(CarType.BUS,     70,  400));
        ruleManager.addRule(new SeatbeltRule(100));

        IFineManager fineManager = new FineManager();

        IRadarService radar = new RadarService(ruleManager, fineManager);

        List<Car> cars = List.of(
            new Car("ABC1234", LocalDateTime.now(), CarType.PRIVATE, 94,  false),
            new Car("TRK5678", LocalDateTime.now(), CarType.TRUCK,   55,  true),
            new Car("TRK9999", LocalDateTime.now(), CarType.TRUCK,   75,  true),
            new Car("BUS0001", LocalDateTime.now(), CarType.BUS,     65,  false),
            new Car("PVT0002", LocalDateTime.now(), CarType.PRIVATE, 60,  true)
        );

        System.out.println(DIVIDER);
        System.out.println("        QUANTUM RADAR - FINES         ");
        System.out.println(DIVIDER);
        System.out.println();

        for (Car car : cars) {
            Optional<Fine> fine = radar.process(car);
            fine.ifPresent(f -> {
                printFine(f);
                System.out.println();
            });
        }

        
        System.out.println(DIVIDER);
        System.out.println("         ALL FINES SUMMARY            ");
        System.out.println(DIVIDER);
        List<Fine> allFines = fineManager.getAllFines();
        if (allFines.isEmpty()) {
            System.out.println("No fines issued.");
        } else {
            for (Fine f : allFines) {
                System.out.printf("%-12s  %d EGP%n", f.getPlateNumber(), f.getTotalAmount());
            }
        }

        
        System.out.println();
        System.out.println(DIVIDER);
        System.out.println("       VIOLATED RULES STATS           ");
        System.out.println(DIVIDER);
        Map<String, Long> stats = fineManager.getViolationStats();
        if (stats.isEmpty()) {
            System.out.println("No violations recorded.");
        } else {
            stats.forEach((rule, count) ->
                System.out.printf("%-30s  %d time(s)%n", rule, count));
        }
    }
}
