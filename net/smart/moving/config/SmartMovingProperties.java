// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

import java.io.OutputStream;
import java.io.PrintWriter;
import java.io.FileOutputStream;
import java.io.File;
import java.util.Iterator;
import java.util.List;
import net.smart.properties.Property;
import net.smart.properties.Properties;

public abstract class SmartMovingProperties extends Properties
{
    public static final String Enabled = "enabled";
    public static final String Disabled = "disabled";
    private static final String[] _defaultKeys;
    private int toggler;
    private String[] keys;
    public boolean enabled;
    
    public SmartMovingProperties() {
        this.toggler = -2;
        this.keys = SmartMovingProperties._defaultKeys;
    }
    
    protected void load(final Properties... propertiesList) throws Exception {
        final List<Property> propertiesToLoad = this.getProperties();
        if (this.toggler != -2) {
            final Iterator<Property> iterator = (Iterator<Property>)propertiesToLoad.iterator();
            while (iterator.hasNext()) {
                iterator.next().reset();
            }
        }
        while (propertiesToLoad.size() > 0) {
            final Iterator<Property> iterator = (Iterator<Property>)propertiesToLoad.iterator();
            while (iterator.hasNext()) {
                if (iterator.next().load(propertiesList)) {
                    iterator.remove();
                }
            }
        }
        this.toggler = 0;
        this.update();
    }
    
    protected void save(final File file, final String version, final boolean header, final boolean comments) throws Exception {
        final List<Property> propertiesToSave = this.getProperties();
        final FileOutputStream stream = new FileOutputStream(file);
        final PrintWriter printer = new PrintWriter(stream);
        if (header) {
            this.printHeader(printer);
        }
        if (version != null) {
            this.printVersion(printer, version, comments);
        }
        for (int i = 0; i < propertiesToSave.size(); ++i) {
            if (propertiesToSave.get(i).print(printer, this.keys, version, comments) && i < propertiesToSave.size() - 1) {
                printer.println();
            }
        }
        printer.close();
    }
    
    protected abstract void printVersion(final PrintWriter p0, final String p1, final boolean p2);
    
    protected abstract void printHeader(final PrintWriter p0);
    
    public void toggle() {
        final int length = (this.keys == null) ? 0 : this.keys.length;
        ++this.toggler;
        if (this.toggler == length) {
            this.toggler = -1;
        }
        this.update();
    }
    
    public void setKeys(String[] keys) {
        if (keys == null || keys.length == 0) {
            keys = SmartMovingProperties._defaultKeys;
        }
        this.keys = keys;
        this.toggler = 0;
        this.update();
    }
    
    public String getKey(final int index) {
        if (this.keys[index] == null) {
            return "enabled";
        }
        return this.keys[index];
    }
    
    public String getNextKey(final String key) {
        if (key == null || key.equals("disabled")) {
            return this.getKey(0);
        }
        int index;
        for (index = 0; index < this.keys.length && (key != null || this.keys[index] != null) && (key == null || !key.equals(this.keys[index])); ++index) {}
        if (++index < this.keys.length) {
            return this.keys[index];
        }
        return "disabled";
    }
    
    public void setCurrentKey(final String key) {
        if (key == null || key.equals("disabled")) {
            this.toggler = -1;
        }
        else if (this.keys.length == 1 && this.keys[0] == null && key.equals("enabled")) {
            this.toggler = 0;
        }
        else {
            this.toggler = 0;
            while (this.toggler < this.keys.length && (key != null || this.keys[this.toggler] != null) && (key == null || !key.equals(this.keys[this.toggler]))) {
                ++this.toggler;
            }
            if (this.toggler == this.keys.length) {
                this.toggler = -1;
            }
        }
        this.update();
    }
    
    public String getCurrentKey() {
        if (this.toggler == -1) {
            return "disabled";
        }
        return this.keys[this.toggler];
    }
    
    public boolean hasKey(final String key) {
        if ("enabled".equals(key)) {
            return this.keys[0] == null;
        }
        if ("disabled".equals(key)) {
            return true;
        }
        for (int i = 0; i < this.keys.length; ++i) {
            if ((key == null && this.keys[i] == null) || (key != null && key.equals(this.keys[i]))) {
                return true;
            }
        }
        return false;
    }
    
    public int getKeyCount() {
        return this.keys.length;
    }
    
    protected void update() {
        final List<Property> properties = this.getProperties();
        final Iterator<Property> iterator = (Iterator<Property>)properties.iterator();
        final String currentKey = this.getCurrentKey();
        while (iterator.hasNext()) {
            iterator.next().update(currentKey);
        }
        this.enabled = (this.toggler != -1);
    }
    
    static {
        _defaultKeys = new String[1];
    }
}
