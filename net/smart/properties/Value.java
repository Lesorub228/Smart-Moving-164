// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.properties;

import net.smart.utilities.Name;
import java.util.LinkedList;
import java.util.HashSet;
import net.smart.utilities.Reflect;
import java.util.HashMap;
import java.util.Map;
import java.io.Writer;
import java.io.StringWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Hashtable;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Dictionary;

public class Value<T>
{
    private int type;
    private T value;
    private Dictionary<String, T> keyValues;
    private List<String> unparsableStrings;
    public static final String Null = "null";
    private static final List<String> _allkeys;
    public static final Class<?> keyboard;
    public static final Class<?> mouse;
    public static final Method _getKeyName;
    public static final Method _getKeyIndex;
    public static final Method _getButtonName;
    public static final Method _getButtonIndex;
    
    public Value(final int type) {
        this.type = type;
    }
    
    public Value(final T value) {
        this.value = value;
    }
    
    public Value(final Value<T> value) {
        this.type = value.type;
        this.value = value.value;
        if (value.keyValues != null) {
            this.keyValues = new Hashtable<String, T>();
            final Enumeration<String> keys = value.keyValues.keys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                this.keyValues.put(key, value.keyValues.get(key));
            }
        }
    }
    
    public Value<T> put(final T value) {
        return this.put(null, value);
    }
    
    public T get(final String key) {
        if (key != null && this.keyValues != null) {
            final T value = this.keyValues.get(key);
            if (value != null) {
                return value;
            }
        }
        return this.value;
    }
    
    public T getStored(final String key) {
        if (key == null || key == "null") {
            return this.value;
        }
        if (this.keyValues != null) {
            return this.keyValues.get(key);
        }
        return null;
    }
    
    private T get(final String key, final Value<T> defaultValue) {
        T result = this.get(key);
        if (result == null) {
            result = this.value;
            if (result == null) {
                result = defaultValue.value;
            }
        }
        return result;
    }
    
    public Value<T> put(final String key, final T value) {
        if (key == null || key == "null" || key.isEmpty()) {
            this.value = value;
        }
        else {
            if (this.keyValues == null) {
                this.keyValues = new Hashtable<String, T>();
            }
            this.keyValues.put(key, value);
        }
        return this;
    }
    
    public void withDependency(final Value<T> dependency, final Value<T> defaultValue) {
        final Iterator<String> iterator = GetAllKeys(this, dependency);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            if (this.get(key, (Value<Object>)defaultValue).equals(true) && dependency.get(key).equals(false)) {
                this.put(key, false);
            }
        }
    }
    
    public void withMinimum(final Value<T> minimum, final Value<T> defaultValue) {
        final Iterator<String> iterator = GetAllKeys(this, minimum);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            this.put(key, Math.max(this.get(key, (Value<Float>)defaultValue), (float)minimum.get(key)));
        }
    }
    
    public void withMaximum(final Value<T> maximum, final Value<T> defaultValue) {
        final Iterator<String> iterator = GetAllKeys(this, maximum);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            this.put(key, Math.min(this.get(key, (Value<Float>)defaultValue), (float)maximum.get(key)));
        }
    }
    
    public Value<Boolean> is(final Value<T> right) {
        final Value<Boolean> result = new Value<Boolean>(Properties.Boolean);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            final T leftValue = this.get(key);
            final T rightValue = right.get(key);
            result.put(key, (leftValue == null && rightValue == null) || (leftValue != null && rightValue != null && leftValue.equals(rightValue)));
        }
        return result;
    }
    
    public Value<Boolean> and(final Value<T> right) {
        final Value<Boolean> result = new Value<Boolean>(Properties.Boolean);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, this.get(key) && (boolean)right.get(key));
        }
        return result;
    }
    
    public Value<Boolean> or(final Value<T> right) {
        final Value<Boolean> result = new Value<Boolean>(Properties.Boolean);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, this.get(key) || (boolean)right.get(key));
        }
        return result;
    }
    
    public Value<Boolean> not() {
        final Value<Boolean> result = new Value<Boolean>(Properties.Boolean);
        final Iterator<String> iterator = GetAllKeys(this);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, !this.get(key));
        }
        return result;
    }
    
    public Value<Float> plus(final Value<T> right) {
        final Value<Float> result = new Value<Float>(Properties.Float);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, this.get(key) + (float)right.get(key));
        }
        return result;
    }
    
    public Value<T> eitherOr(final Value<T> left, final Value<T> right) {
        final Value<T> result = new Value<T>(Properties.getBaseType(left.type));
        final Iterator<String> iterator = GetAllKeys(this, left, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, (Object)(this.get(key) ? left.get(key) : right.get(key)));
        }
        return result;
    }
    
    public Value<Float> maximum(final Value<T> right) {
        final Value<Float> result = new Value<Float>(Properties.Float);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, Math.max(this.get(key), (float)right.get(key)));
        }
        return result;
    }
    
    public Value<Float> minimum(final Value<T> right) {
        final Value<Float> result = new Value<Float>(Properties.Float);
        final Iterator<String> iterator = GetAllKeys(this, right);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, Math.min(this.get(key), (float)right.get(key)));
        }
        return result;
    }
    
    public Value<String> toKeyName() {
        final Value<String> result = new Value<String>(Properties.String);
        final Iterator<String> iterator = GetAllKeys(this);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, toKeyName(this.get(key)));
        }
        return result;
    }
    
    public Value<Integer> toKeyCode() {
        final Value<Integer> result = new Value<Integer>(Properties.Integer);
        final Iterator<String> iterator = GetAllKeys(this);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, toKeyCode(this.get(key)));
        }
        return result;
    }
    
    public Value<Dictionary<Object, Set<Integer>>> toBlockConfig() {
        final Value<Dictionary<Object, Set<Integer>>> result = new Value<Dictionary<Object, Set<Integer>>>(Properties.Strings);
        final Iterator<String> iterator = GetAllKeys(this);
        while (iterator.hasNext()) {
            final String key = iterator.next();
            result.put(key, toBlockConfig(this.get(key)));
        }
        return result;
    }
    
    public Value<T> clone() {
        return new Value<T>(this);
    }
    
    public static Iterator<String> GetAllKeys(final Value<?>... values) {
        return GetAllKeys((String[])null, values);
    }
    
    public static Iterator<String> GetAllKeys(final String[] sorted, final Value<?>... values) {
        Value._allkeys.clear();
        for (int i = 0; i < values.length; ++i) {
            final Value<?> value = values[i];
            if (value.keyValues != null) {
                final Enumeration<String> keys = value.keyValues.keys();
                while (keys.hasMoreElements()) {
                    Value._allkeys.add(keys.nextElement());
                }
            }
        }
        Collections.sort(Value._allkeys);
        Value._allkeys.add(0, "null");
        int i = 0;
        int n = 1;
        while (sorted != null && i < sorted.length) {
            final String sort = sorted[i];
            if (sort != null) {
                if (sort != "null") {
                    if (Value._allkeys.remove(sort)) {
                        Value._allkeys.add(n++, sort);
                    }
                }
            }
            ++i;
        }
        return Value._allkeys.iterator();
    }
    
    @Override
    public boolean equals(final Object other) {
        if (!(other instanceof Value)) {
            return false;
        }
        final Value<T> otherValue = (Value<T>)other;
        if (this.value == null != (otherValue.value == null)) {
            return false;
        }
        if (this.value != null && !this.valuesEqual(this.value, otherValue.value)) {
            return false;
        }
        if (((this.keyValues == null) ? 0 : this.keyValues.size()) != ((otherValue.keyValues == null) ? 0 : otherValue.keyValues.size())) {
            return false;
        }
        if (this.keyValues != null && this.keyValues.size() != 0) {
            final Enumeration<String> keys = this.keyValues.keys();
            while (keys.hasMoreElements()) {
                final String key = keys.nextElement();
                final T otherKeyValue = otherValue.keyValues.get(key);
                if (otherKeyValue == null) {
                    return false;
                }
                final T keyValue = this.keyValues.get(key);
                if (!this.valuesEqual(keyValue, otherKeyValue)) {
                    return false;
                }
            }
        }
        return true;
    }
    
    private boolean valuesEqual(final T first, final T second) {
        if (!(first instanceof Object[])) {
            return first.equals(second);
        }
        final Object[] firstArray = (Object)first;
        final Object[] secondArray = (Object)second;
        if (firstArray.length != secondArray.length) {
            return false;
        }
        for (int i = 0; i < firstArray.length; ++i) {
            if (!firstArray[i].equals(secondArray[i])) {
                return false;
            }
        }
        return true;
    }
    
    public Value<T> load(final String content, final boolean singular) {
        if (content == null || singular) {
            this.value = this.parsePropertyElement(content);
            return this;
        }
        final String[] elements = content.split(";");
        for (int i = 0; i < elements.length; ++i) {
            final String element = elements[i];
            final int seperatorIndex = element.indexOf(58);
            String key = null;
            String keyValue = null;
            if (seperatorIndex > 0) {
                key = element.substring(0, seperatorIndex);
                keyValue = element.substring(seperatorIndex + 1);
            }
            else {
                keyValue = element;
            }
            final T value = this.parsePropertyElement(keyValue);
            if (value != null) {
                this.put(key, value);
            }
            else {
                if (this.unparsableStrings == null) {
                    this.unparsableStrings = new ArrayList<String>();
                }
                this.unparsableStrings.add(keyValue);
            }
        }
        return this;
    }
    
    public Iterator<String> getUnparsableStrings() {
        if (this.unparsableStrings != null) {
            return this.unparsableStrings.iterator();
        }
        return null;
    }
    
    public void print(final PrintWriter printer, final String[] sorted) {
        boolean first = true;
        final Iterator<String> keys = GetAllKeys(sorted, this);
        while (keys.hasNext()) {
            final String key = keys.next();
            if (key == "null" && this.value == null) {
                continue;
            }
            if (!first) {
                printer.print(";");
            }
            else {
                first = false;
            }
            if (key != "null") {
                printer.print(key);
                printer.print(":");
            }
            this.printDisplayString(printer, this.get(key));
        }
    }
    
    @Override
    public String toString() {
        final StringWriter result = new StringWriter();
        this.print(new PrintWriter(result), null);
        return result.toString();
    }
    
    public String createDisplayString(final Object value) {
        if (value instanceof String[] || value instanceof Map) {
            final StringWriter result = new StringWriter();
            this.printDisplayString(new PrintWriter(result), value);
            return result.toString();
        }
        return this.getDisplayString(value);
    }
    
    private void printDisplayString(final PrintWriter printer, final Object value) {
        if (value instanceof String[]) {
            boolean first = true;
            final String[] values = (String[])value;
            for (int i = 0; i < values.length; ++i) {
                if (first) {
                    first = false;
                }
                else {
                    printer.print(",");
                }
                printer.print(this.getDisplayString(values[i]));
            }
        }
        else if (value instanceof Map) {
            boolean first = true;
            final Map values2 = (Map)value;
            final List<String> keys = new ArrayList<String>(values2.size());
            for (final Object key : values2.keySet()) {
                keys.add((String)key);
            }
            Collections.sort(keys);
            for (int j = 0; j < keys.size(); ++j) {
                if (first) {
                    first = false;
                }
                else {
                    printer.print(",");
                }
                final String key2 = keys.get(j);
                printer.print(this.getDisplayString(key2));
                printer.print(",");
                printer.print(this.getDisplayString(values2.get(key2)));
            }
        }
        else {
            printer.print(this.getDisplayString(value));
        }
    }
    
    private String getDisplayString(final Object value) {
        String result = value.toString();
        if (result.endsWith(".0")) {
            result = result.substring(0, result.length() - 2);
        }
        return result;
    }
    
    private T parsePropertyElement(final String stringToParse) {
        final int baseType = Properties.getBaseType(this.type);
        if (baseType == Properties.Boolean) {
            return (T)tryParseBoolean(stringToParse);
        }
        if (baseType == Properties.Float) {
            return (T)tryParseFloat(stringToParse);
        }
        if (baseType == Properties.Integer) {
            return (T)tryParseInteger(stringToParse);
        }
        if (baseType == Properties.Strings) {
            return (T)(Object)tryParseStrings(stringToParse);
        }
        if (baseType == Properties.StringMap) {
            return (T)this.tryParseStringMap(stringToParse);
        }
        if (baseType == Properties.IntegerMap) {
            return (T)this.tryParseIntegerMap(stringToParse);
        }
        if (baseType == Properties.String) {
            return (T)tryParseString(stringToParse);
        }
        return null;
    }
    
    public static Boolean tryParseBoolean(final String value) {
        try {
            if (value != null) {
                return value.equals("true") || (value.equals("false") ? Boolean.valueOf(false) : null);
            }
        }
        catch (final Exception ex) {}
        return null;
    }
    
    public static Float tryParseFloat(final String value) {
        try {
            if (value != null) {
                return Float.parseFloat(value);
            }
        }
        catch (final Exception ex) {}
        return null;
    }
    
    public static Integer tryParseInteger(final String value) {
        try {
            if (value != null) {
                return Integer.parseInt(value);
            }
        }
        catch (final Exception ex) {}
        return null;
    }
    
    public static String tryParseString(final String value) {
        return (value == null) ? null : value;
    }
    
    public static String[] tryParseStrings(final String value) {
        return (String[])((value == null) ? null : (value.isEmpty() ? new String[0] : value.split(",")));
    }
    
    public Map<String, String> tryParseStringMap(final String value) {
        final String[] keyValues = value.split(",");
        final Map<String, String> result = new HashMap<String, String>();
        for (int i = 0; i < keyValues.length; ++i) {
            final String key = keyValues[i++];
            if (i < keyValues.length) {
                result.put(key, keyValues[i]);
            }
        }
        return result;
    }
    
    public Map<String, Integer> tryParseIntegerMap(final String value) {
        final String[] keyValues = value.split(",");
        final Map<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < keyValues.length; ++i) {
            final String key = keyValues[i++];
            if (i < keyValues.length) {
                result.put(key, Integer.parseInt(keyValues[i]));
            }
        }
        return result;
    }
    
    public Value<T> e(final T e) {
        return this.put("e", e);
    }
    
    public Value<T> m(final T m) {
        return this.put("m", m);
    }
    
    public Value<T> h(final T h) {
        return this.put("h", h);
    }
    
    public Value<T> c(final T c) {
        return this.put("c", c);
    }
    
    public Value<T> a(final T a) {
        return this.put("a", a);
    }
    
    public static String toKeyName(final Integer keyCode) {
        if (keyCode == null) {
            return null;
        }
        if (keyCode >= 0) {
            return (String)Reflect.Invoke(Value._getKeyName, null, keyCode);
        }
        return (String)Reflect.Invoke(Value._getButtonName, null, keyCode + 100);
    }
    
    private static Integer toKeyCode(String keyName) {
        if (keyName == null) {
            return null;
        }
        keyName = keyName.toUpperCase();
        int keyCode = (int)Reflect.Invoke(Value._getKeyIndex, null, keyName);
        if (keyCode > 0) {
            return keyCode;
        }
        keyCode = (int)Reflect.Invoke(Value._getButtonIndex, null, keyName);
        if (keyCode >= 0) {
            return keyCode - 100;
        }
        return null;
    }
    
    private static Dictionary<Object, Set<Integer>> toBlockConfig(final String[] config) {
        if (config == null) {
            return null;
        }
        final Dictionary<Object, Set<Integer>> result = new Hashtable<Object, Set<Integer>>();
        for (int i = 0; i < config.length; ++i) {
            final String[] elements = config[i].split("/");
            if (elements.length > 0) {
                final Set<Integer> metaDatas = new HashSet<Integer>();
                final String blockName = elements[0];
                result.put(blockName, metaDatas);
                if (blockName.matches("[0-9]+")) {
                    result.put(Integer.parseInt(blockName), metaDatas);
                }
                for (int n = 1; n < elements.length; ++n) {
                    final String metaDataText = elements[n];
                    if (metaDataText.matches("[0-9]+")) {
                        metaDatas.add(Integer.parseInt(metaDataText));
                    }
                }
            }
        }
        return result;
    }
    
    static {
        _allkeys = new LinkedList<String>();
        keyboard = Reflect.LoadClass(Value.class, new Name("org.lwjgl.input.Keyboard"), false);
        mouse = Reflect.LoadClass(Value.class, new Name("org.lwjgl.input.Mouse"), false);
        _getKeyName = ((Value.keyboard != null) ? Reflect.GetMethod(Value.keyboard, new Name("getKeyName"), Integer.TYPE) : null);
        _getKeyIndex = ((Value.keyboard != null) ? Reflect.GetMethod(Value.keyboard, new Name("getKeyIndex"), String.class) : null);
        _getButtonName = ((Value.mouse != null) ? Reflect.GetMethod(Value.mouse, new Name("getButtonName"), Integer.TYPE) : null);
        _getButtonIndex = ((Value.mouse != null) ? Reflect.GetMethod(Value.mouse, new Name("getButtonIndex"), String.class) : null);
    }
}
