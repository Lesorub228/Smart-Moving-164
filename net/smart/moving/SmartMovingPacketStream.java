// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.moving;

import java.util.HashSet;
import java.io.OutputStream;
import java.io.ObjectOutputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ByteArrayInputStream;
import java.util.Set;

public class SmartMovingPacketStream
{
    public static final String Id;
    public static final Set<StackTraceElement> errors;
    
    public static void receivePacket(final ea packet, final IPacketReceiver comm, final IEntityPlayerMP player) {
        try {
            final ByteArrayInputStream byteInput = new ByteArrayInputStream(packet.c);
            final ObjectInputStream objectInput = new ObjectInputStream(byteInput);
            final byte packetId = objectInput.readByte();
            switch (packetId) {
                case 0: {
                    final int entityId = objectInput.readInt();
                    final long state = objectInput.readLong();
                    comm.processStatePacket(packet, player, entityId, state);
                    break;
                }
                case 1: {
                    final String info = (String)objectInput.readObject();
                    comm.processConfigInfoPacket(packet, player, info);
                    break;
                }
                case 2: {
                    final String[] content = (String[])objectInput.readObject();
                    final String username = (String)objectInput.readObject();
                    comm.processConfigContentPacket(packet, player, content, username);
                    break;
                }
                case 3: {
                    comm.processConfigChangePacket(packet, player);
                    break;
                }
                case 4: {
                    final int difference = objectInput.readInt();
                    final String username = (String)objectInput.readObject();
                    comm.processSpeedChangePacket(packet, player, difference, username);
                    break;
                }
                case 5: {
                    final float hunger = objectInput.readFloat();
                    comm.processHungerChangePacket(packet, player, hunger);
                    break;
                }
                case 6: {
                    final String soundId = (String)objectInput.readObject();
                    final float volume = objectInput.readFloat();
                    final float pitch = objectInput.readFloat();
                    comm.processSoundPacket(packet, player, soundId, volume, pitch);
                    break;
                }
                default: {
                    throw new RuntimeException("Unknown packet id '" + packetId + "' found");
                }
            }
        }
        catch (final Throwable t) {
            if (SmartMovingPacketStream.errors.add(t.getStackTrace()[0])) {
                t.printStackTrace();
            }
            else {
                System.err.println(t.getClass().getName() + ": " + t.getMessage());
            }
        }
    }
    
    public static void sendState(final IPacketSender comm, final int entityId, final long state) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(0);
            objectOutput.writeInt(entityId);
            objectOutput.writeLong(state);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendConfigInfo(final IPacketSender comm, final String info) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(1);
            objectOutput.writeObject(info);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendConfigContent(final IPacketSender comm, final String[] content, final String username) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(2);
            objectOutput.writeObject(content);
            objectOutput.writeObject(username);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendConfigChange(final IPacketSender comm) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(3);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendSpeedChange(final IPacketSender comm, final int difference, final String username) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(4);
            objectOutput.writeInt(difference);
            objectOutput.writeObject(username);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendHungerChange(final IPacketSender comm, final float hunger) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(5);
            objectOutput.writeFloat(hunger);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    public static void sendSound(final IPacketSender comm, final String soundId, final float volume, final float pitch) {
        final ByteArrayOutputStream byteOutput = new ByteArrayOutputStream();
        try {
            final ObjectOutputStream objectOutput = new ObjectOutputStream(byteOutput);
            objectOutput.writeByte(6);
            objectOutput.writeObject(soundId);
            objectOutput.writeFloat(volume);
            objectOutput.writeFloat(pitch);
            objectOutput.flush();
        }
        catch (final Throwable t) {
            throw new RuntimeException(t);
        }
        comm.sendPacket(byteOutput.toByteArray());
    }
    
    static {
        String id = Info.ModComId;
        if (id.length() > 15) {
            id = id.substring(0, 15);
        }
        Id = id;
        errors = new HashSet<StackTraceElement>();
    }
}
