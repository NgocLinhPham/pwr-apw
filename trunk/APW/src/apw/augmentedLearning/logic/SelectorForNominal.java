/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package apw.augmentedLearning.logic;

import java.util.HashSet;
import java.util.Iterator;
import static apw.augmentedLearning.logic.RelationBetweenSets.*;

/**
 *
 * @author Nitric
 */
public class SelectorForNominal extends Selector {
    protected SelectorTypeForNominal type;
    protected HashSet<String> values = new HashSet<String>();

    protected SelectorForNominal(int attributeId, boolean isNegated, SelectorTypeForNominal type, HashSet<String> set) {
        this.attributeId = attributeId;
        this.type = type;
        this.forNominalAttribute = true;
        this.values = set;
    }

    public static SelectorForNominal getSelEmpty(int attributeId) {
        return new SelectorForNominal(attributeId, false, SelectorTypeForNominal.EMPTY, new HashSet<String>());
    }

    public static SelectorForNominal getSelUniversal(int attributeId, HashSet<String> values) {
        return new SelectorForNominal(attributeId, false, SelectorTypeForNominal.UNIVERSAL, values);
    }

    public static SelectorForNominal getSelSet(int attributeId, boolean isNegated, HashSet<String> set) {
        return new SelectorForNominal(attributeId, isNegated, SelectorTypeForNominal.SET, set);
    }

    public boolean covers(String attributeValue) {
        return values.contains(attributeValue);
    }

    public void setValues(HashSet<String> values) {
        this.values = values;
        this.type = SelectorTypeForNominal.SET;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("id = " + attributeId + ", ");
        switch (type) {
            case EMPTY : return sb.append("empty").toString();
            case UNIVERSAL: return sb.append("universal " + values).toString();
            case SET: return sb.append(values).toString();
        }
        return null;
    }

    public boolean isUniversal() {
        return type == SelectorTypeForNominal.UNIVERSAL;
    }

    public boolean isEmpty() {
        return type == SelectorTypeForNominal.EMPTY;
    }

    public boolean removeValue(String s) {
        boolean result = values.contains(s);
        if (result) {
            values.remove(s);
            if (values.isEmpty())
                type = SelectorTypeForNominal.EMPTY;
        }
        return result;
    }

    @Override
    public Selector getNegation() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public RelationBetweenSets isMoreGeneralThan(Selector other) {
        if (!other.isForNominalAttribute())
            throw new IllegalArgumentException("Cannot compare two different types of selectors!");
        SelectorForNominal o = (SelectorForNominal) other;
        if (values.containsAll(o.values))
            if (o.values.containsAll(values))
                return EQUAL;
            else
                return MORE_GENERAL;
        else if (o.values.containsAll(values))
            return LESS_GENERAL;
        else
            return NOT_COMPARABLE;
    }

    public Selector intersection(Selector other) {
        if (!other.isForNominalAttribute())
            throw new IllegalArgumentException("Cannot intersect two different types of selectors!");
        SelectorForNominal o = (SelectorForNominal) other;
        switch (isMoreGeneralThan(o)) {
            case EQUAL: return this;
            case LESS_GENERAL: return this;
            case MORE_GENERAL: return o;
            default: {
                HashSet<String> common = new HashSet<String>();
                Iterator<String> iter = values.iterator();
                String s;
                while (iter.hasNext()) {
                    s = iter.next();
                    if (o.values.contains(s))
                        common.add(s);
                }
                return getSelSet(attributeId, false, common);
            }
        }
    }

    public boolean covers(Object o) {
        String s = (String)o;
        /* System.out.println("s = " + s);
        System.out.println("values = " + values); */
        return values.contains(s);
    }
}