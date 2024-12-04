package org.laokou.common.ruleengine.core;

import org.laokou.common.ruleengine.api.Facts;
import org.laokou.common.ruleengine.api.Rule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
/**
 * @author cola
 */
public abstract class CompositeRule extends AbstractRule {
    private static final Logger LOGGER = LoggerFactory.getLogger(CompositeRule.class);

    protected List<Rule> rules = new ArrayList<>();

    private boolean isSorted=false;

    public CompositeRule() {

    }

    public CompositeRule priority(int priority) {
        this.priority = priority;
        return this;
    }

    public CompositeRule name(String name){
        this.name = name;
        return this;
    }

    @Override
    public boolean apply(Facts facts) {
        sort();
        return doApply(facts);
    }

    protected abstract boolean doApply(Facts facts);

    protected void sort(){
        if(!isSorted){
            LOGGER.debug(this.name+" before sort:" + rules);
            Collections.sort(rules);
            LOGGER.debug(this.name+" after sort:" + rules);
            isSorted = true;
        }
    }
}
