package tbases.client.render.items;

import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemTransformVec3f;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.model.TRSRTransformation;
import org.lwjgl.util.vector.Vector3f;
import tbases.init.TBItems;

import javax.vecmath.Matrix4f;

public class HerobrinesScytheMH {
    private ItemStack tool;
    private static ItemTransformVec3f thirdPersonLeft = null;
    private static ItemTransformVec3f thirdPersonRight = null;
    private static ItemTransformVec3f firstPersonLeft = null;
    private static ItemTransformVec3f firstPersonRight = null;

    public HerobrinesScytheMH() {
    }

    public ItemCameraTransforms getItemCameraTransforms() {
        new Vector3f();
        new Vector3f();
        new Vector3f(1.0F, 1.0F, 1.0F);
        Vector3f rotation = new Vector3f(0.0F, 4.8F, 2.2F);
        Vector3f translation = new Vector3f(0.0F, 0.0F, -0.1F);
        if (this.tool != null && this.tool.getItem() == TBItems.herobrinesscythe) {
            translation.y += 2.0F;
        }

        translation.scale(0.0625F);
        Vector3f scale = new Vector3f(2.0F, 2.0F, 1.0F);
        thirdPersonRight = new ItemTransformVec3f(rotation, translation, scale);
        rotation = new Vector3f(0.0F, 0.8F, 0.0F);
        translation = new Vector3f(0.0F, 0.4F, 0.0F);
        if (this.tool != null && this.tool.getItem() == TBItems.herobrinesscythe) {
            ++translation.y;
        }

        translation.scale(0.0625F);
        scale = new Vector3f(0.0F, 0.4F, 0.0F);
        firstPersonRight = new ItemTransformVec3f(rotation, translation, scale);
        return new ItemCameraTransforms(thirdPersonLeft, thirdPersonRight, firstPersonLeft, firstPersonRight, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT, ItemTransformVec3f.DEFAULT);
    }

    public static Matrix4f getMatrix(ItemTransformVec3f transform) {
        Matrix4f m = new Matrix4f();
        Matrix4f t = new Matrix4f();
        m.setIdentity();
        m.setTranslation(TRSRTransformation.toVecmath(transform.translation));
        t.setIdentity();
        t.rotY(transform.rotation.y);
        m.mul(t);
        t.setIdentity();
        t.rotX(transform.rotation.x);
        m.mul(t);
        t.setIdentity();
        t.rotZ(transform.rotation.z);
        m.mul(t);
        t.setIdentity();
        t.m00 = transform.scale.x;
        t.m11 = transform.scale.y;
        t.m22 = transform.scale.z;
        m.mul(t);
        return m;
    }
}