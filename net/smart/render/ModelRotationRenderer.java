// 
// Decompiled by Procyon v0.6.0
// 

package net.smart.render;

import org.lwjgl.BufferUtils;
import net.smart.utilities.Install;
import net.smart.utilities.Reflect;
import org.lwjgl.opengl.GL11;
import java.nio.FloatBuffer;
import java.lang.reflect.Method;
import java.lang.reflect.Field;

public class ModelRotationRenderer extends bcu
{
    protected static final float RadiantToAngle = 57.295776f;
    protected static final float Whole = 6.2831855f;
    protected static final float Half = 3.1415927f;
    private static Field _compiled;
    private static Method _compileDisplayList;
    private static Field _displayList;
    protected ModelRotationRenderer base;
    public boolean ignoreRender;
    public boolean forceRender;
    public boolean t;
    public int u;
    public int rotationOrder;
    public float scaleX;
    public float scaleY;
    public float scaleZ;
    public boolean ignoreBase;
    public boolean ignoreSuperRotation;
    public float o;
    public float p;
    public float q;
    public static int XYZ;
    public static int XZY;
    public static int YXZ;
    public static int YZX;
    public static int ZXY;
    public static int ZYX;
    public boolean fadeEnabled;
    public boolean fadeOffsetX;
    public boolean fadeOffsetY;
    public boolean fadeOffsetZ;
    public boolean fadeRotateAngleX;
    public boolean fadeRotateAngleY;
    public boolean fadeRotateAngleZ;
    public boolean fadeRotationPointX;
    public boolean fadeRotationPointY;
    public boolean fadeRotationPointZ;
    public RendererData previous;
    private static FloatBuffer buffer;
    private static float[] array;
    
    public ModelRotationRenderer(final bbo modelBase, final int i, final int j, final ModelRotationRenderer baseRenderer) {
        super(modelBase, i, j);
        this.rotationOrder = ModelRotationRenderer.XYZ;
        this.t = false;
        this.base = baseRenderer;
        if (this.base != null) {
            this.base.a((bcu)this);
        }
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.scaleZ = 1.0f;
        this.fadeEnabled = false;
    }
    
    public void a(final float f) {
        if ((!this.ignoreRender && !this.ignoreBase) || this.forceRender) {
            this.doRender(f, this.ignoreBase);
        }
    }
    
    public void renderIgnoreBase(final float f) {
        if (this.ignoreBase) {
            this.doRender(f, false);
        }
    }
    
    public void doRender(final float f, final boolean useParentTransformations) {
        if (!this.preRender(f)) {
            return;
        }
        this.preTransforms(f, true, useParentTransformations);
        GL11.glCallList(this.u);
        if (this.m != null) {
            for (int i = 0; i < this.m.size(); ++i) {
                this.m.get(i).a(f);
            }
        }
        this.postTransforms(f, true, useParentTransformations);
    }
    
    public boolean preRender(final float f) {
        if (this.k) {
            return false;
        }
        if (!this.j) {
            return false;
        }
        if (!this.t) {
            this.UpdateCompiled();
        }
        if (!this.t) {
            Reflect.Invoke(ModelRotationRenderer._compileDisplayList, this, f);
            this.UpdateDisplayList();
            this.t = true;
        }
        return true;
    }
    
    public void preTransforms(final float f, final boolean push, final boolean useParentTransformations) {
        if (this.base != null && !this.ignoreBase && useParentTransformations) {
            this.base.preTransforms(f, push, true);
        }
        this.preTransform(f, push);
    }
    
    public void preTransform(final float f, final boolean push) {
        if (this.f != 0.0f || this.g != 0.0f || this.h != 0.0f || this.ignoreSuperRotation) {
            if (push) {
                GL11.glPushMatrix();
            }
            GL11.glTranslatef(this.c * f, this.d * f, this.e * f);
            if (this.ignoreSuperRotation) {
                ModelRotationRenderer.buffer.rewind();
                GL11.glGetFloat(2982, ModelRotationRenderer.buffer);
                ModelRotationRenderer.buffer.get(ModelRotationRenderer.array);
                GL11.glLoadIdentity();
                GL11.glTranslatef(ModelRotationRenderer.array[12] / ModelRotationRenderer.array[15], ModelRotationRenderer.array[13] / ModelRotationRenderer.array[15], ModelRotationRenderer.array[14] / ModelRotationRenderer.array[15]);
            }
            rotate(this.rotationOrder, this.f, this.g, this.h);
            GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
            GL11.glTranslatef(this.o, this.p, this.q);
        }
        else if (this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f || this.scaleZ != 1.0f || this.o != 0.0f || this.p != 0.0f || this.q != 0.0f) {
            GL11.glTranslatef(this.c * f, this.d * f, this.e * f);
            GL11.glScalef(this.scaleX, this.scaleY, this.scaleZ);
            GL11.glTranslatef(this.o, this.p, this.q);
        }
    }
    
    private static void rotate(final int rotationOrder, final float rotateAngleX, final float rotateAngleY, final float rotateAngleZ) {
        if (rotationOrder == ModelRotationRenderer.ZXY && rotateAngleY != 0.0f) {
            GL11.glRotatef(rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if (rotationOrder == ModelRotationRenderer.YXZ && rotateAngleZ != 0.0f) {
            GL11.glRotatef(rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        if ((rotationOrder == ModelRotationRenderer.YZX || rotationOrder == ModelRotationRenderer.YXZ || rotationOrder == ModelRotationRenderer.ZXY || rotationOrder == ModelRotationRenderer.ZYX) && rotateAngleX != 0.0f) {
            GL11.glRotatef(rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
        if ((rotationOrder == ModelRotationRenderer.XZY || rotationOrder == ModelRotationRenderer.ZYX) && rotateAngleY != 0.0f) {
            GL11.glRotatef(rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if ((rotationOrder == ModelRotationRenderer.XYZ || rotationOrder == ModelRotationRenderer.XZY || rotationOrder == ModelRotationRenderer.YZX || rotationOrder == ModelRotationRenderer.ZXY || rotationOrder == ModelRotationRenderer.ZYX) && rotateAngleZ != 0.0f) {
            GL11.glRotatef(rotateAngleZ * 57.295776f, 0.0f, 0.0f, 1.0f);
        }
        if ((rotationOrder == ModelRotationRenderer.XYZ || rotationOrder == ModelRotationRenderer.YXZ || rotationOrder == ModelRotationRenderer.YZX) && rotateAngleY != 0.0f) {
            GL11.glRotatef(rotateAngleY * 57.295776f, 0.0f, 1.0f, 0.0f);
        }
        if ((rotationOrder == ModelRotationRenderer.XYZ || rotationOrder == ModelRotationRenderer.XZY) && rotateAngleX != 0.0f) {
            GL11.glRotatef(rotateAngleX * 57.295776f, 1.0f, 0.0f, 0.0f);
        }
    }
    
    public void postTransform(final float f, final boolean pop) {
        if (this.f != 0.0f || this.g != 0.0f || this.h != 0.0f || this.ignoreSuperRotation) {
            if (pop) {
                GL11.glPopMatrix();
            }
        }
        else if (this.c != 0.0f || this.d != 0.0f || this.e != 0.0f || this.scaleX != 1.0f || this.scaleY != 1.0f || this.scaleZ != 1.0f || this.o != 0.0f || this.p != 0.0f || this.q != 0.0f) {
            GL11.glTranslatef(-this.o, -this.p, -this.q);
            GL11.glScalef(1.0f / this.scaleX, 1.0f / this.scaleY, 1.0f / this.scaleZ);
            GL11.glTranslatef(-this.c * f, -this.d * f, -this.e * f);
        }
    }
    
    public void postTransforms(final float f, final boolean pop, final boolean useParentTransformations) {
        this.postTransform(f, pop);
        if (this.base != null && !this.ignoreBase && useParentTransformations) {
            this.base.postTransforms(f, pop, true);
        }
    }
    
    public void reset() {
        this.rotationOrder = ModelRotationRenderer.XYZ;
        this.scaleX = 1.0f;
        this.scaleY = 1.0f;
        this.scaleZ = 1.0f;
        this.c = 0.0f;
        this.d = 0.0f;
        this.e = 0.0f;
        this.f = 0.0f;
        this.g = 0.0f;
        this.h = 0.0f;
        this.ignoreBase = false;
        this.ignoreSuperRotation = false;
        this.forceRender = false;
        this.o = 0.0f;
        this.p = 0.0f;
        this.q = 0.0f;
        this.fadeOffsetX = false;
        this.fadeOffsetY = false;
        this.fadeOffsetZ = false;
        this.fadeRotateAngleX = false;
        this.fadeRotateAngleY = false;
        this.fadeRotateAngleZ = false;
        this.fadeRotationPointX = false;
        this.fadeRotationPointY = false;
        this.fadeRotationPointZ = false;
        this.previous = null;
    }
    
    public void b(final float f) {
        final boolean update = !this.t;
        super.b(f);
        if (update) {
            this.UpdateLocals();
        }
    }
    
    public void c(final float f) {
        final boolean update = !this.t;
        if (!this.preRender(f)) {
            return;
        }
        if (update) {
            this.UpdateLocals();
        }
        this.preTransforms(f, false, true);
    }
    
    private void UpdateLocals() {
        this.UpdateCompiled();
        if (this.t) {
            this.UpdateDisplayList();
        }
    }
    
    private void UpdateCompiled() {
        this.t = (boolean)Reflect.GetField(ModelRotationRenderer._compiled, this);
    }
    
    private void UpdateDisplayList() {
        this.u = (int)Reflect.GetField(ModelRotationRenderer._displayList, this);
    }
    
    public void fadeStore(final float totalTime) {
        if (this.previous != null) {
            this.previous.offsetX = this.o;
            this.previous.offsetY = this.p;
            this.previous.offsetZ = this.q;
            this.previous.rotateAngleX = this.f;
            this.previous.rotateAngleY = this.g;
            this.previous.rotateAngleZ = this.h;
            this.previous.rotationPointX = this.c;
            this.previous.rotationPointY = this.d;
            this.previous.rotationPointZ = this.e;
            this.previous.totalTime = totalTime;
        }
    }
    
    public void fadeIntermediate(final float totalTime) {
        if (this.previous != null && totalTime - this.previous.totalTime <= 2.0f) {
            this.o = this.GetIntermediatePosition(this.previous.offsetX, this.o, this.fadeOffsetX, this.previous.totalTime, totalTime);
            this.p = this.GetIntermediatePosition(this.previous.offsetY, this.p, this.fadeOffsetY, this.previous.totalTime, totalTime);
            this.q = this.GetIntermediatePosition(this.previous.offsetZ, this.q, this.fadeOffsetZ, this.previous.totalTime, totalTime);
            this.f = this.GetIntermediateAngle(this.previous.rotateAngleX, this.f, this.fadeRotateAngleX, this.previous.totalTime, totalTime);
            this.g = this.GetIntermediateAngle(this.previous.rotateAngleY, this.g, this.fadeRotateAngleY, this.previous.totalTime, totalTime);
            this.h = this.GetIntermediateAngle(this.previous.rotateAngleZ, this.h, this.fadeRotateAngleZ, this.previous.totalTime, totalTime);
            this.c = this.GetIntermediatePosition(this.previous.rotationPointX, this.c, this.fadeRotationPointX, this.previous.totalTime, totalTime);
            this.d = this.GetIntermediatePosition(this.previous.rotationPointY, this.d, this.fadeRotationPointY, this.previous.totalTime, totalTime);
            this.e = this.GetIntermediatePosition(this.previous.rotationPointZ, this.e, this.fadeRotationPointZ, this.previous.totalTime, totalTime);
        }
    }
    
    public boolean canBeRandomBoxSource() {
        return true;
    }
    
    private float GetIntermediatePosition(final float prevPosition, final float shouldPosition, final boolean fade, final float lastTotalTime, final float totalTime) {
        if (!fade || shouldPosition == prevPosition) {
            return shouldPosition;
        }
        return prevPosition + (shouldPosition - prevPosition) * (totalTime - lastTotalTime) * 0.2f;
    }
    
    private float GetIntermediateAngle(float prevAngle, float shouldAngle, final boolean fade, final float lastTotalTime, final float totalTime) {
        if (!fade || shouldAngle == prevAngle) {
            return shouldAngle;
        }
        while (prevAngle >= 6.2831855f) {
            prevAngle -= 6.2831855f;
        }
        while (prevAngle < 0.0f) {
            prevAngle += 6.2831855f;
        }
        while (shouldAngle >= 6.2831855f) {
            shouldAngle -= 6.2831855f;
        }
        while (shouldAngle < 0.0f) {
            shouldAngle += 6.2831855f;
        }
        if (shouldAngle > prevAngle && shouldAngle - prevAngle > 3.1415927f) {
            prevAngle += 6.2831855f;
        }
        if (shouldAngle < prevAngle && prevAngle - shouldAngle > 3.1415927f) {
            shouldAngle += 6.2831855f;
        }
        return prevAngle + (shouldAngle - prevAngle) * (totalTime - lastTotalTime) * 0.2f;
    }
    
    static {
        ModelRotationRenderer._compiled = Reflect.GetField(bcu.class, Install.ModelRenderer_compiled);
        ModelRotationRenderer._compileDisplayList = Reflect.GetMethod(bcu.class, Install.ModelRenderer_compileDisplayList, Float.TYPE);
        ModelRotationRenderer._displayList = Reflect.GetField(bcu.class, Install.ModelRenderer_displayList);
        ModelRotationRenderer.XYZ = 0;
        ModelRotationRenderer.XZY = 1;
        ModelRotationRenderer.YXZ = 2;
        ModelRotationRenderer.YZX = 3;
        ModelRotationRenderer.ZXY = 4;
        ModelRotationRenderer.ZYX = 5;
        ModelRotationRenderer.buffer = BufferUtils.createFloatBuffer(16);
        ModelRotationRenderer.array = new float[16];
    }
}
