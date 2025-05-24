// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.properties;

import java.util.Iterator;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Set;
import java.util.Dictionary;
import java.util.List;
import java.util.Map;

public class Property<T>
{
    private static final int printWidth = 69;
    private String comment;
    private String[] header;
    private int gap;
    private boolean explicitlyModified;
    private boolean implicitlyModified;
    private String aquiredString;
    private boolean singular;
    private final int type;
    private String currentVersion;
    private Map<String, Object> versionSources;
    private Map<String, Object> versionDefaults;
    private static int i;
    private static final int Is;
    private static final int And;
    private static final int Or;
    private static final int Not;
    private static final int Plus;
    private static final int EitherOr;
    private static final int Maximum;
    private static final int Minimum;
    private static final int ToKeyName;
    private static final int ToKeyCode;
    private static final int ToBlockConfig;
    public T value;
    private Value<T> systemValue;
    private Value<T> aquiredValue;
    private Object minValue;
    private Object maxValue;
    private Object local;
    private Object left;
    private int operator;
    private Object right;
    private List<Property<Boolean>> depends;
    private static final String Current = "";
    private static final String[] CurrentArray;
    
    public Property(final int type) {
        this.depends = null;
        this.type = type;
    }
    
    private Property(final String key) {
        this(Properties.Key);
        this.set(new Value<T>((T)key));
    }
    
    private Property(final Object left, final int operator, final Object right) {
        this(Properties.Operator);
        this.left = left;
        this.operator = operator;
        this.right = right;
    }
    
    private Property(final Object local, final int operator, final Object left, final Object right) {
        this(Properties.Operator);
        this.local = local;
        this.operator = operator;
        this.left = left;
        this.right = right;
    }
    
    public void update(final String key) {
        this.value = this.getKeyValue(key);
    }
    
    public void setValue(final T value) {
        this.value = value;
        this.systemValue = new Value<T>(value);
        this.aquiredValue = new Value<T>(value);
        this.implicitlyModified = false;
        this.explicitlyModified = false;
    }
    
    public Property<T> singular() {
        this.singular = true;
        return this;
    }
    
    public Property<Boolean> is(final Object value) {
        return new Property<Boolean>(this, Property.Is, value);
    }
    
    public Property<Boolean> and(final Object value) {
        return new Property<Boolean>(this, Property.And, value);
    }
    
    public Property<Boolean> or(final Object value) {
        return new Property<Boolean>(this, Property.Or, value);
    }
    
    public Property<Boolean> andNot(final Property value) {
        return this.and(value.not());
    }
    
    public Property<Boolean> not() {
        return new Property<Boolean>(this, Property.Not, null);
    }
    
    public Property<Float> plus(final Object value) {
        return new Property<Float>(this, Property.Plus, value);
    }
    
    public Property eitherOr(final Object either, final Object or) {
        return new Property(this, Property.EitherOr, either, or);
    }
    
    public Property<Float> maximum(final Object value) {
        return new Property<Float>(this, Property.Maximum, value);
    }
    
    public Property<Float> minimum(final Object value) {
        return new Property<Float>(this, Property.Minimum, value);
    }
    
    public Property<String> toKeyName() {
        return new Property<String>(this, Property.ToKeyName, null);
    }
    
    public Property<Integer> toKeyCode(final Integer defaultValue) {
        return new Property<Integer>(this, Property.ToKeyCode, null).defaults(defaultValue, new String[0]);
    }
    
    public Property<Dictionary<Object, Set<Integer>>> toBlockConfig() {
        return new Property<Dictionary<Object, Set<Integer>>>(this, Property.ToBlockConfig, null);
    }
    
    public Property<T> depends(final Property<Boolean>... conditions) {
        if (this.depends == null) {
            this.depends = new LinkedList<Property<Boolean>>();
        }
        for (int i = 0; i < conditions.length; ++i) {
            this.depends.add(conditions[i]);
        }
        return this;
    }
    
    public Property<T> values(final Object defaultValue, final Object minValue, final Object maxValue) {
        return this.defaults(defaultValue, new String[0]).range(minValue, maxValue);
    }
    
    public Property<T> up(final Object defaultValue, final Object minValue) {
        return this.defaults(defaultValue, new String[0]).min(minValue);
    }
    
    public Property<T> down(final Object defaultValue, final Object maxValue) {
        return this.defaults(defaultValue, new String[0]).max(maxValue);
    }
    
    public Property<T> range(final Object minValue, final Object maxValue) {
        return this.min(minValue).max(maxValue);
    }
    
    public Property<T> defaults(final Object defaultValue, final String... versions) {
        this.versionDefaults = this.addVersioned(this.versionDefaults, defaultValue, versions);
        return this;
    }
    
    public Property<T> min(final Object minValue) {
        this.minValue = minValue;
        return this;
    }
    
    public Property<T> max(final Object maxValue) {
        this.maxValue = maxValue;
        return this;
    }
    
    public Property<T> key(final String key, final String... versions) {
        if (this.currentVersion == null) {
            if (versions != null && versions.length > 0) {
                this.currentVersion = versions[0];
            }
            else {
                this.currentVersion = "";
            }
        }
        return this.source(new Property(key), versions);
    }
    
    public Property<T> source(final Object source, final String... versions) {
        this.versionSources = this.addVersioned(this.versionSources, source, versions);
        return this;
    }
    
    public Property<T> comment(final String comment) {
        this.comment = comment;
        return this;
    }
    
    public Property<T> section(final String... header) {
        this.gap = 1;
        this.header = header;
        return this;
    }
    
    public Property<T> chapter(final String... header) {
        this.gap = 2;
        this.header = header;
        return this;
    }
    
    public Property<T> book(final String... header) {
        this.gap = 3;
        this.header = header;
        return this;
    }
    
    public void reset() {
        this.explicitlyModified = false;
        this.implicitlyModified = false;
        this.value = null;
        this.systemValue = null;
        this.aquiredValue = null;
        this.reset(this.minValue);
        this.reset(this.maxValue);
        this.reset(this.left);
        this.reset(this.right);
        if (this.depends != null) {
            for (int i = 0; i < this.depends.size(); ++i) {
                this.reset(this.depends.get(i));
            }
        }
    }
    
    private void reset(final Object value) {
        if (value instanceof Property) {
            ((Property)value).reset();
        }
    }
    
    public boolean load(final Properties... propertiesList) {
        if (this.systemValue != null) {
            return true;
        }
        if (this.type == Properties.Constant) {
            return true;
        }
        if (this.type == Properties.Operator) {
            if (this.operator == Property.EitherOr && (this.getValue(this.left) == null || this.getValue(this.right) == null || this.getValue(this.local) == null)) {
                return false;
            }
            if ((this.operator == Property.Is || this.operator == Property.And || this.operator == Property.Or || this.operator == Property.Plus || this.operator == Property.Maximum || this.operator == Property.Minimum) && (this.getValue(this.left) == null || this.getValue(this.right) == null)) {
                return false;
            }
            if ((this.operator == Property.Not || this.operator == Property.ToKeyName || this.operator == Property.ToKeyCode || this.operator == Property.ToBlockConfig) && this.getValue(this.left) == null) {
                return false;
            }
            Object operatorValue = null;
            if (this.operator == Property.Is) {
                operatorValue = this.getValue(this.left).is(this.getValue(this.right));
            }
            else if (this.operator == Property.And) {
                operatorValue = this.getValue(this.left).and(this.getValue(this.right));
            }
            else if (this.operator == Property.Or) {
                operatorValue = this.getValue(this.left).or(this.getValue(this.right));
            }
            else if (this.operator == Property.Not) {
                operatorValue = this.getValue(this.left).not();
            }
            else if (this.operator == Property.Plus) {
                operatorValue = this.getValue(this.left).plus(this.getValue(this.right));
            }
            else if (this.operator == Property.EitherOr) {
                operatorValue = this.getValue(this.local).eitherOr(this.getValue(this.left), this.getValue(this.right));
            }
            else if (this.operator == Property.Maximum) {
                operatorValue = this.getValue(this.left).maximum(this.getValue(this.right));
            }
            else if (this.operator == Property.Minimum) {
                operatorValue = this.getValue(this.left).minimum(this.getValue(this.right));
            }
            else if (this.operator == Property.ToKeyName) {
                operatorValue = this.getValue(this.left).toKeyName();
            }
            else if (this.operator == Property.ToKeyCode) {
                operatorValue = this.getValue(this.left).toKeyCode();
            }
            else if (this.operator == Property.ToBlockConfig) {
                operatorValue = this.getValue(this.left).toBlockConfig();
            }
            if (operatorValue == null) {
                throw new RuntimeException("Unknown operator '" + this.operator + "' found");
            }
            return this.set(this.getValue(operatorValue));
        }
        else {
            if (propertiesList == null || this.versionSources == null) {
                return false;
            }
            if (this.depends != null) {
                for (int i = 0; i < this.depends.size(); ++i) {
                    if (this.getValue(this.depends.get(i)) == null) {
                        return false;
                    }
                }
            }
            final Object minObject = this.getMinimumValue();
            final Value<T> minValue = this.getValue(minObject);
            if (minObject != null && minValue == null) {
                return false;
            }
            final Object maxObject = this.getMaximumValue();
            final Value<T> maxValue = this.getValue(maxObject);
            if (maxObject != null && maxValue == null) {
                return false;
            }
            final Value<T> defaultValue = this.getValue(this.getDefaultValue());
            for (int j = 0; j < propertiesList.length; ++j) {
                final Properties properties = propertiesList[j];
                final Object source = this.getVersionSource(properties.version);
                if (source != null) {
                    final String key = this.getKey(source);
                    this.aquiredValue = ((key != null) ? this.getPropertyValue(properties, key) : this.getValue(source));
                    if (this.aquiredValue != null) {
                        final Value<T> initValue = this.aquiredValue.clone();
                        if (this.depends != null) {
                            for (int n = 0; n < this.depends.size(); ++n) {
                                initValue.withDependency(this.getValue(this.depends.get(n)), defaultValue);
                            }
                        }
                        if (minObject != null) {
                            initValue.withMinimum(minValue, defaultValue);
                        }
                        if (maxObject != null) {
                            initValue.withMaximum(maxValue, defaultValue);
                        }
                        return this.set(initValue);
                    }
                    if (key == null) {
                        return false;
                    }
                }
            }
            return this.set(defaultValue);
        }
    }
    
    private boolean set(final Value<T> initValue) {
        this.systemValue = initValue;
        this.update(null);
        return true;
    }
    
    private Map<String, Object> addVersioned(Map<String, Object> versioned, final Object value, String... versions) {
        if (versioned == null) {
            versioned = new Hashtable<String, Object>(1);
        }
        if (versions == null || versions.length == 0) {
            versions = Property.CurrentArray;
        }
        for (int i = 0; i < versions.length; ++i) {
            versioned.put(versions[i], value);
        }
        return versioned;
    }
    
    public T getKeyValue(final String key) {
        T value = this.systemValue.get(key);
        if (value == null) {
            value = this.getValue(this.getDefaultValue()).get(null);
        }
        if (value == null) {
            value = (T)Properties.getDefaultValue(this.type);
        }
        return value;
    }
    
    private Value<T> getValue(final Object value) {
        if (value instanceof Property) {
            final Property property = (Property)value;
            property.load((Properties[])null);
            return property.systemValue;
        }
        if (value instanceof Value) {
            return (Value)value;
        }
        return new Value<T>((T)value);
    }
    
    private Object getDefaultValue(final String version) {
        Object defaultValue = null;
        if (this.versionDefaults != null) {
            if (version != null) {
                defaultValue = this.versionDefaults.get(version);
            }
            if (defaultValue == null) {
                defaultValue = this.versionDefaults.get("");
            }
        }
        if (defaultValue == null) {
            defaultValue = Properties.getDefaultValue(this.type);
        }
        return defaultValue;
    }
    
    private Object getDefaultValue() {
        return this.getDefaultValue("");
    }
    
    private Object getMinimumValue() {
        if (this.minValue != null) {
            return this.minValue;
        }
        return Properties.getMinimumValue(this.type);
    }
    
    private Object getMaximumValue() {
        if (this.maxValue != null) {
            return this.maxValue;
        }
        return Properties.getMaximumValue(this.type);
    }
    
    private Object getVersionSource(final String version) {
        if (this.versionSources == null) {
            return null;
        }
        Object source = null;
        if (version != null) {
            source = this.versionSources.get(version);
        }
        if (source == null) {
            source = this.versionSources.get("");
        }
        return source;
    }
    
    private Value<T> getPropertyValue(final Properties properties, final String key) {
        final String propertyString = properties.getProperty(key);
        if (propertyString != null) {
            this.aquiredString = propertyString;
        }
        String stringToParse;
        if ((stringToParse = propertyString) != null) {
            stringToParse = propertyString.trim();
            this.explicitlyModified = stringToParse.endsWith("!");
            if (this.explicitlyModified) {
                stringToParse = stringToParse.substring(0, stringToParse.length() - 1);
            }
            stringToParse = stringToParse.trim();
        }
        final Value<T> value = this.parsePropertyValue(stringToParse);
        this.implicitlyModified = (stringToParse != null && (value == null || !value.equals(this.getValue(this.getDefaultValue(properties.version)))));
        if (!this.explicitlyModified && !this.implicitlyModified) {
            return this.getValue(this.getDefaultValue());
        }
        return value;
    }
    
    private Value<T> parsePropertyValue(final String stringToParse) {
        if (stringToParse != null) {
            return new Value<T>(this.type).load(stringToParse, this.singular);
        }
        return null;
    }
    
    public boolean print(final PrintWriter printer, final String[] sorted, final String version, final boolean comments) {
        if (!this.isPersistent() || this.systemValue == null) {
            return false;
        }
        if (this.getVersionSource(version) == null) {
            return false;
        }
        for (int gap = this.gap + ((this.comment == null) ? -1 : 1), i = 0; i < gap; ++i) {
            printer.println();
        }
        if (this.header != null && this.header.length > 0) {
            this.printHeader(printer);
        }
        if (this.comment != null && comments) {
            printer.print("# ");
            printer.print(this.comment);
            printer.println();
        }
        if (this.aquiredString == null) {
            this.printValue(printer, sorted, false);
            return true;
        }
        boolean error = false;
        final Iterator<String> unparsed = this.aquiredValue.getUnparsableStrings();
        while (unparsed != null && unparsed.hasNext()) {
            final String unparsableString = unparsed.next();
            this.printErrorPrefix(printer);
            printer.print("Could not interpret string \"");
            printer.print(unparsableString);
            printer.print("\" as ");
            printer.print(Properties.getBaseTypeName(Properties.getBaseType(this.type)));
            printer.print(" value, used ");
            printer.print((!this.aquiredString.isEmpty() && this.aquiredValue.get(null) != null) ? "local" : "system");
            printer.print(" default");
            this.printValuePostfix(printer, null);
            this.printErrorPostfix(printer);
            error = true;
        }
        final Object minObject = this.getMinimumValue();
        final Value<T> minValue = this.getValue(minObject);
        final Object maxObject = this.getMaximumValue();
        final Value<T> maxValue = this.getValue(maxObject);
        final Iterator<String> keys = Value.GetAllKeys(this.systemValue);
        while (keys.hasNext()) {
            final String key = keys.next();
            final T parsedSingleValue = this.aquiredValue.getStored(key);
            final T aquiredSingleValue = this.aquiredValue.get(key);
            final T usedSingleValue = this.systemValue.getStored(key);
            if (parsedSingleValue == null) {
                if (aquiredSingleValue == null) {
                    continue;
                }
                if (aquiredSingleValue.equals(usedSingleValue)) {
                    continue;
                }
            }
            else if (parsedSingleValue.equals(usedSingleValue)) {
                continue;
            }
            if (Properties.getBaseType(this.type) == Properties.Boolean && this.depends != null && !this.depends.isEmpty()) {
                String dependKey = null;
                for (int j = 0; j < this.depends.size(); ++j) {
                    final Property<Boolean> depend = this.depends.get(j);
                    final String currentDependKey = depend.getCurrentKey();
                    if (currentDependKey != null && !depend.getKeyValue(key)) {
                        dependKey = currentDependKey;
                        break;
                    }
                }
                this.printWarnPrefix(printer);
                this.printValuePrefix(printer, key);
                printer.print("is ignored because ");
                if (dependKey != null) {
                    printer.print("the ");
                    if (key == "null") {
                        printer.print("default");
                    }
                    else {
                        printer.print("\"" + key + "\"");
                    }
                    printer.print(" value of property \"");
                    printer.print(dependKey);
                    printer.print("\" is \"false\"");
                }
                else {
                    printer.print("one of the restricting expressions evaluated to \"false\"");
                }
                this.printWarnPostfix(printer);
                error = true;
            }
            else {
                this.printErrorPrefix(printer);
                this.printValuePrefix(printer, key);
                printer.print("was out of range, used ");
                if (minValue != null && usedSingleValue.equals(minValue.get(key))) {
                    printer.print("minimum");
                }
                else if (maxValue != null && usedSingleValue.equals(maxValue.get(key))) {
                    printer.print("maximum");
                }
                else {
                    printer.print("in-range");
                }
                this.printValuePostfix(printer, key);
                this.printErrorPostfix(printer);
                error = true;
            }
        }
        this.printValue(printer, sorted, error);
        return true;
    }
    
    private void printValue(final PrintWriter printer, final String[] sorted, final boolean error) {
        printer.print(this.getCurrentKey());
        printer.print(":");
        if (this.aquiredString != null && (error || this.explicitlyModified)) {
            printer.print(this.aquiredString);
        }
        else if (this.implicitlyModified && this.systemValue.equals(this.getValue(this.getDefaultValue()))) {
            printer.print(this.aquiredString);
            printer.print("!");
        }
        else {
            this.systemValue.print(printer, sorted);
        }
    }
    
    private void printHeader(final PrintWriter printer) {
        final String title = this.header[0];
        String body = (this.header.length > 1) ? this.header[1] : null;
        final char separator = (this.gap == 3) ? '=' : ((this.gap == 2) ? '-' : ' ');
        this.printSeparation(printer, separator, 69);
        printer.print("# ");
        printer.println(title);
        if (body != null) {
            this.printSeparation(printer, '-', title.length());
            final int lineWidth = 67;
            while (true) {
                printer.print("# ");
                if (body.length() <= lineWidth) {
                    break;
                }
                int i;
                for (i = lineWidth; i > 0 && body.charAt(i) != ' '; --i) {}
                printer.println(body.substring(0, i));
                body = body.substring(i + 1);
            }
            printer.println(body);
        }
        this.printSeparation(printer, separator, 69);
        printer.println();
    }
    
    private void printSeparation(final PrintWriter printer, final char seperator, final int length) {
        printer.print("# ");
        for (int i = 0; i < length; ++i) {
            printer.print(seperator);
        }
        printer.println();
    }
    
    private void printValuePrefix(final PrintWriter printer, final String key) {
        printer.print("Interpreted ");
        if (key != "null") {
            printer.print("\"");
            printer.print(key);
            printer.print("\" ");
        }
        printer.print("value \"");
        printer.print(this.aquiredValue.get(key));
        printer.print("\" ");
    }
    
    private void printValuePostfix(final PrintWriter printer, final String key) {
        printer.print(" value \"");
        printer.print((this.aquiredString == null || this.aquiredString.isEmpty()) ? this.getValue(this.getDefaultValue()) : this.getKeyValue(key));
        printer.print("\" instead");
    }
    
    private void printErrorPrefix(final PrintWriter printer) {
        this.printErrorPrefix(printer, false);
    }
    
    private void printWarnPrefix(final PrintWriter printer) {
        this.printErrorPrefix(printer, true);
    }
    
    private void printErrorPrefix(final PrintWriter printer, final boolean warning) {
        printer.print("#");
        if (!warning) {
            printer.print("!!");
        }
        printer.print("! ");
    }
    
    private void printErrorPostfix(final PrintWriter printer) {
        this.printErrorPostfix(printer, false);
    }
    
    private void printWarnPostfix(final PrintWriter printer) {
        this.printErrorPostfix(printer, true);
    }
    
    private void printErrorPostfix(final PrintWriter printer, final boolean warning) {
        printer.print(" !");
        if (!warning) {
            printer.print("!!");
        }
        printer.println("#");
    }
    
    public boolean isPersistent() {
        return this.versionSources != null && this.versionSources.size() > 0;
    }
    
    public String getCurrentKey() {
        if (this.isPersistent()) {
            return this.getKey(this.getVersionSource(this.currentVersion));
        }
        return null;
    }
    
    private String getKey(final Object candidate) {
        if (candidate instanceof Property) {
            final Property property = (Property)candidate;
            if (property.type == Properties.Key) {
                return (String)property.value;
            }
        }
        return null;
    }
    
    @Override
    public String toString() {
        if (this.isPersistent()) {
            return this.getCurrentKey();
        }
        return super.toString();
    }
    
    public String getKeyValueString(final String key) {
        return this.getValueString(this.getKeyValue(key));
    }
    
    public String getValueString() {
        return this.getValueString(this.value);
    }
    
    public String getValueString(final T value) {
        return (value != null) ? this.systemValue.createDisplayString(value) : null;
    }
    
    static {
        Property.i = 0;
        Is = Property.i++;
        And = Property.i++;
        Or = Property.i++;
        Not = Property.i++;
        Plus = Property.i++;
        EitherOr = Property.i++;
        Maximum = Property.i++;
        Minimum = Property.i++;
        ToKeyName = Property.i++;
        ToKeyCode = Property.i++;
        ToBlockConfig = Property.i++;
        CurrentArray = new String[] { "" };
    }
}
