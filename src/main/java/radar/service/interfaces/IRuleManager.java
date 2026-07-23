package radar.service.interfaces;

import java.util.List;

public interface IRuleManager {
    void addRule(IRule rule);
    List<IRule> getRules();
}
