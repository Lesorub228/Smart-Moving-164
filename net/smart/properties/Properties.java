// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.properties;

import java.util.Hashtable;
import java.util.Map;
import java.util.HashMap;
import java.io.InputStream;
import java.io.FileInputStream;
import java.util.Iterator;
import java.util.Collection;
import java.lang.reflect.Field;
import net.smart.utilities.Reflect;
import java.util.ArrayList;
import java.util.List;
import java.io.File;

public class Properties extends java.util.Properties
{
    private static final long serialVersionUID = 5319578641402091067L;
    private static int i;
    public static final int Boolean;
    public static final int Unmodified;
    public static final int Modified;
    public static final int Float;
    public static final int Positive;
    public static final int Negative;
    public static final int PositiveFactor;
    public static final int NegativeFactor;
    public static final int IncreasingFactor;
    public static final int DecreasingFactor;
    public static final int Integer;
    public static final int String;
    public static final int Strings;
    public static final int Operator;
    public static final int Constant;
    public static final int Key;
    public static final int StringMap;
    public static final int IntegerMap;
    public final String version;
    
    public Properties() {
        this.version = null;
    }
    
    public Properties(final File file) {
        this.load(file);
        this.version = this.getProperty("move.options.version");
    }
    
    public Properties(final String version, final File file) {
        this.load(file);
        this.version = version;
    }
    
    protected List<Property> getProperties() {
        return this.getProperties(null);
    }
    
    protected List<Property> getProperties(final Class<?> type) {
        final List<Property> properties = new ArrayList<Property>();
        if (type != null) {
            return this.addProperties(properties, type, false);
        }
        return this.addProperties(properties, this.getClass(), true);
    }
    
    private List<Property> addProperties(final List<Property> list, final Class<?> type, final boolean base) {
        if (base && type.getSuperclass() != null) {
            this.addProperties(list, type.getSuperclass(), base);
        }
        final Field[] fields = type.getDeclaredFields();
        for (int i = 0; i < fields.length; ++i) {
            fields[i].setAccessible(true);
            final Object value = Reflect.GetField(fields[i], this);
            this.addProperties(list, value);
        }
        return list;
    }
    
    private void addProperties(final List<Property> list, final Object value) {
        if (value instanceof Property) {
            list.add((Property)value);
        }
        else if (value instanceof Collection) {
            final Iterator iterator = ((Collection)value).iterator();
            while (iterator.hasNext()) {
                this.addProperties(list, iterator.next());
            }
        }
    }
    
    public void write(final Properties properties) {
        this.write(properties, null);
    }
    
    public void write(final Properties properties, final String key) {
        final List<Property> propertiesToWrite = this.getProperties();
        for (int i = 0; i < propertiesToWrite.size(); ++i) {
            final Property property = propertiesToWrite.get(i);
            if (property.isPersistent()) {
                ((Hashtable<String, String>)properties).put(property.getCurrentKey(), (key == null) ? property.getValueString() : property.getKeyValueString(key));
            }
        }
    }
    
    private boolean load(final File file) {
        try {
            if (file.exists()) {
                this.load(new FileInputStream(file));
            }
            return true;
        }
        catch (final Exception e) {
            return false;
        }
    }
    
    public static int getBaseType(final int type) {
        if (type == Properties.Boolean || type == Properties.Unmodified || type == Properties.Modified) {
            return Properties.Boolean;
        }
        if (type == Properties.Float || type == Properties.Positive || type == Properties.Negative || type == Properties.PositiveFactor || type == Properties.NegativeFactor || type == Properties.IncreasingFactor || type == Properties.DecreasingFactor) {
            return Properties.Float;
        }
        if (type == Properties.Integer) {
            return Properties.Integer;
        }
        if (type == Properties.Strings) {
            return Properties.Strings;
        }
        if (type == Properties.StringMap) {
            return Properties.StringMap;
        }
        if (type == Properties.IntegerMap) {
            return Properties.IntegerMap;
        }
        return Properties.String;
    }
    
    public static String getBaseTypeName(final int baseType) {
        if (baseType == Properties.Boolean) {
            return "boolean";
        }
        if (baseType == Properties.Float) {
            return "floating point";
        }
        if (baseType == Properties.Integer) {
            return "integer";
        }
        return "string";
    }
    
    public static Object getDefaultValue(final int type) {
        if (type == Properties.Boolean) {
            return false;
        }
        if (type == Properties.Unmodified) {
            return true;
        }
        if (type == Properties.Modified) {
            return false;
        }
        if (type == Properties.Integer) {
            return 0;
        }
        if (type == Properties.Float) {
            return 0.0f;
        }
        if (type == Properties.Positive) {
            return 0.0f;
        }
        if (type == Properties.Negative) {
            return 0.0f;
        }
        if (type == Properties.PositiveFactor) {
            return 1.0f;
        }
        if (type == Properties.NegativeFactor) {
            return 1.0f;
        }
        if (type == Properties.IncreasingFactor) {
            return 1.0f;
        }
        if (type == Properties.DecreasingFactor) {
            return 1.0f;
        }
        if (type == Properties.String) {
            return "";
        }
        if (type == Properties.Strings) {
            return new String[0];
        }
        if (type == Properties.StringMap) {
            return new HashMap();
        }
        if (type == Properties.IntegerMap) {
            return new HashMap();
        }
        return null;
    }
    
    public static Object getMinimumValue(final int type) {
        if (type == Properties.Positive) {
            return 0.0f;
        }
        if (type == Properties.PositiveFactor) {
            return 0.0f;
        }
        if (type == Properties.IncreasingFactor) {
            return 1.0f;
        }
        if (type == Properties.DecreasingFactor) {
            return 0.0f;
        }
        return null;
    }
    
    public static Object getMaximumValue(final int type) {
        if (type == Properties.Negative) {
            return 0.0f;
        }
        if (type == Properties.NegativeFactor) {
            return 0.0f;
        }
        if (type == Properties.DecreasingFactor) {
            return 1.0f;
        }
        return null;
    }
    
    public static Property<Boolean> Unmodified() {
        return Property(Properties.Unmodified);
    }
    
    public static Property<Boolean> Unmodified(final String key, final String... versions) {
        return Unmodified().key(key, versions);
    }
    
    public static Property<Boolean> Modified() {
        return Property(Properties.Modified);
    }
    
    public static Property<Boolean> Modified(final String key, final String... versions) {
        return Modified().key(key, versions);
    }
    
    public static Property<Integer> Integer() {
        return Property(Properties.Integer);
    }
    
    public static Property<Integer> Integer(final String key, final String... versions) {
        return Integer().key(key, versions);
    }
    
    public static Property<Float> Float() {
        return Property(Properties.Float);
    }
    
    public static Property<Float> Float(final String key, final String... versions) {
        return Float().key(key, versions);
    }
    
    public static Property<Float> Positive() {
        return Property(Properties.Positive);
    }
    
    public static Property<Float> Positive(final String key, final String... versions) {
        return Positive().key(key, versions);
    }
    
    public static Property<Float> Negative() {
        return Property(Properties.Negative);
    }
    
    public static Property<Float> Negative(final String key, final String... versions) {
        return Negative().key(key, versions);
    }
    
    public static Property<Float> PositiveFactor() {
        return Property(Properties.PositiveFactor);
    }
    
    public static Property<Float> PositiveFactor(final String key, final String... versions) {
        return PositiveFactor().key(key, versions);
    }
    
    public static Property<Float> NegativeFactor() {
        return Property(Properties.NegativeFactor);
    }
    
    public static Property<Float> NegativeFactor(final String key, final String... versions) {
        return NegativeFactor().key(key, versions);
    }
    
    public static Property<Float> IncreasingFactor() {
        return Property(Properties.IncreasingFactor);
    }
    
    public static Property<Float> IncreasingFactor(final String key, final String... versions) {
        return IncreasingFactor().key(key, versions);
    }
    
    public static Property<Float> DecreasingFactor() {
        return Property(Properties.DecreasingFactor);
    }
    
    public static Property<Float> DecreasingFactor(final String key, final String... versions) {
        return DecreasingFactor().key(key, versions);
    }
    
    public static Property<String> String() {
        return Property(Properties.String);
    }
    
    public static Property<String> String(final String key, final String... versions) {
        return String().key(key, versions);
    }
    
    public static Property<String[]> Strings() {
        return Property(Properties.Strings);
    }
    
    public static Property<String[]> Strings(final String key, final String... versions) {
        return Strings().key(key, versions);
    }
    
    public static Property<Map<String, String>> StringMap() {
        return Property(Properties.StringMap);
    }
    
    public static Property<Map<String, String>> StringMap(final String key, final String... versions) {
        return StringMap().key(key, versions);
    }
    
    public static Property<Map<String, Integer>> IntegerMap() {
        return Property(Properties.IntegerMap);
    }
    
    public static Property<Map<String, Integer>> IntegerMap(final String key, final String... versions) {
        return IntegerMap().key(key, versions);
    }
    
    private static Property Property(final int type) {
        return new Property(type);
    }
    
    public Value<Boolean> Value(final Boolean base) {
        return new Value<Boolean>(Properties.Boolean).put(base);
    }
    
    public Value<Float> Value(final Float base) {
        return new Value<Float>(Properties.Float).put(base);
    }
    
    public Value<String> Value(final String base) {
        return new Value<String>(Properties.String).put(base);
    }
    
    protected static String[] concat(final String left, final String[] right) {
        return concat(new String[] { left }, right);
    }
    
    protected static String[] concat(final String[] left, final String[] right) {
        final String[] result = new String[left.length + right.length];
        int i;
        for (i = 0; i < left.length; ++i) {
            result[i] = left[i];
        }
        while (i < result.length) {
            result[i] = right[i - left.length];
            ++i;
        }
        return result;
    }
    
    static {
        Properties.i = 0;
        Boolean = Properties.i++;
        Unmodified = Properties.i++;
        Modified = Properties.i++;
        Float = Properties.i++;
        Positive = Properties.i++;
        Negative = Properties.i++;
        PositiveFactor = Properties.i++;
        NegativeFactor = Properties.i++;
        IncreasingFactor = Properties.i++;
        DecreasingFactor = Properties.i++;
        Integer = Properties.i++;
        String = Properties.i++;
        Strings = Properties.i++;
        Operator = Properties.i++;
        Constant = Properties.i++;
        Key = Properties.i++;
        StringMap = Properties.i++;
        IntegerMap = Properties.i++;
    }
}
