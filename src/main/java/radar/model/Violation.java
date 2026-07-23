package radar.model;

public class Violation {

    private final String ruleName;
    private final String description;
    private final int fee;

    public Violation(String ruleName, String description, int fee) {
        this.ruleName = ruleName;
        this.description = description;
        this.fee = fee;
    }

    public String getRuleName() {
        return ruleName; 
    }
    
    public String getDescription() {
        return description; 
    }
    
    public int getFee() {
        return fee; 
    }
}

