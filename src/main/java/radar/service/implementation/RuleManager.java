package radar.service.implementation;

import radar.service.interfaces.IRule;
import radar.service.interfaces.IRuleManager;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class RuleManager implements IRuleManager {
    private final List<IRule> rules = new ArrayList<>();

    @Override
    public void addRule(IRule rule) {
        rules.add(rule);
    }

    @Override
    public List<IRule> getRules() {
        return Collections.unmodifiableList(rules);
    }
}
