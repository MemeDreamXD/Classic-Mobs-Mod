package com.memedream.classicmobs.event;

import com.memedream.classicmobs.init.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.projectile.ProjectileDeflection;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;

public class ModEvents {

    public static void chitinProjectileParrying(ProjectileImpactEvent event) {
        if (event.getRayTraceResult() instanceof BlockHitResult result) {
            if (event.getProjectile().level().getBlockState(result.getBlockPos()).is(ModBlocks.CHITIN_BLOCK)) {
                Vec3 delta = event.getProjectile().getDeltaMovement();
                float velocity = (float) delta.length() / 2.0F;
                event.getProjectile().setPosRaw(event.getProjectile().xOld, event.getProjectile().yOld, event.getProjectile().zOld);
                if (result.getDirection().getAxis() == Direction.Axis.Y) {
                    event.getProjectile().shoot(delta.x(), delta.reverse().y(), delta.z(), velocity, 0.0F);
                } else if (result.getDirection().getAxis() == Direction.Axis.X) {
                    event.getProjectile().shoot(delta.reverse().x(), delta.reverse().y(), delta.z(), velocity, 0.0F);
                } else {
                    event.getProjectile().shoot(delta.x(), delta.reverse().y(), delta.reverse().z(), velocity, 0.0F);
                }
                event.getProjectile().getPersistentData().putBoolean("deflected", true);
                event.setCanceled(true);
            }
        }
    }
}
