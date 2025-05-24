// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving.config;

import java.util.Hashtable;
import java.util.Iterator;
import net.smart.properties.Properties;

public class SmartMovingServerConfig extends SmartMovingClientConfig
{
    private Properties properties;
    private Properties topProperties;
    
    public SmartMovingServerConfig() {
        this.properties = new Properties();
        this.topProperties = new Properties();
    }
    
    public void loadFromProperties(final String[] propertyArray, final boolean top) {
        for (int i = 0; i < propertyArray.length - 1; i += 2) {
            final String key = propertyArray[i];
            final String value = propertyArray[i + 1];
            ((Hashtable<String, String>)this.properties).put(key, value);
            if (top) {
                ((Hashtable<String, String>)this.topProperties).put(key, value);
            }
        }
        this.load(top);
    }
    
    public void load(final boolean top) {
        if (!top && !this.topProperties.isEmpty()) {
            for (final Object topKey : ((Hashtable<Object, V>)this.topProperties).keySet()) {
                this.properties.put(topKey, ((Hashtable<K, Object>)this.topProperties).get(topKey));
            }
        }
        super.loadFromProperties(this.properties);
    }
    
    public void reset() {
        this.properties.clear();
        this.topProperties.clear();
    }
}
