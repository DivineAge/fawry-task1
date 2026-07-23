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


}
