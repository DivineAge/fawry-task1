package radar.model;

import java.util.Collections;
import java.util.List;

public class Fine {
    private final String plateNumber;
    private final List<Violation> violations;
    
    public Fine(String plateNumber, List<Violation> violations) {    
    this.plateNumber = plateNumber;
    this.violations  = Collections.unmodifiableList(violations);
    }

    public String getPlateNumber() {
        return plateNumber; 
    }
    
    public List<Violation> getViolations() {
        return violations; 
    }

    public int getTotalAmount() {
        int total = 0;
        for (Violation v : violations) {
            total += v.getFee();
        }
        return total;
    }

    public void print() {
        System.out.println("Traffic for car " + plateNumber);
        System.out.println("Total amount: " + getTotalAmount() + " EGP");
        System.out.println("Violations:");
        for (Violation v : violations) {
            System.out.println("- " + v.getDescription() + " : " + v.getFee() + " EGP");
        }
    }
}
