package com.memedream.classicmobs.event;

import com.memedream.classicmobs.entity.ThrownKnifeEntity;
import com.memedream.classicmobs.init.ModBlocks;
import com.memedream.classicmobs.init.ModDataAttachments;
import com.memedream.classicmobs.init.ModEffects;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import net.neoforged.neoforge.common.Tags;
import net.neoforged.neoforge.event.entity.ProjectileImpactEvent;
import net.neoforged.neoforge.event.entity.living.LivingDeathEvent;
import net.neoforged.neoforge.event.entity.living.MobEffectEvent;
import net.neoforged.neoforge.event.entity.player.PlayerEvent;

public class ModEvents {

    public static void chitinProjectileParrying(ProjectileImpactEvent event) {
        if (!event.isCanceled() && event.getRayTraceResult() instanceof BlockHitResult result) {
            if (event.getProjectile().level().getBlockState(result.getBlockPos()).is(ModBlocks.CHITIN_BLOCK)) {
                Vec3 delta = event.getProjectile().getDeltaMovement();
                float velocity = (float) delta.length() / 2.0F;
                if (velocity < 0.1F) return;
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

    public static void faeCurseWeakness(PlayerEvent.BreakSpeed event) {
        if (event.getEntity().hasEffect(ModEffects.FAE_CURSE) && event.getState().is(Tags.Blocks.ORES)) {
            event.getEntity().addEffect(new MobEffectInstance(MobEffects.MINING_FATIGUE, 5, 2, false, false, false));
        }
    }

    public static void sorryBossCantRemoveTheCurse(MobEffectEvent.Remove event) {
        if (event.getEffect().is(ModEffects.FAE_CURSE) && (!(event.getEntity() instanceof Player player) || !player.isCreative())) {
           event.setCanceled(true);
        }
    }

    public static void dropKnivesOnDeath(LivingDeathEvent event) {
        if (!event.getEntity().getData(ModDataAttachments.STUCK_KNIVES).getStuckKnives().isEmpty() && event.getEntity().level() instanceof ServerLevel level) {
            LivingEntity victim = event.getEntity();
            for (ItemStack item : victim.getData(ModDataAttachments.STUCK_KNIVES).getStuckKnives()) {
                ThrownKnifeEntity knife = Projectile.spawnProjectileUsingShoot(ThrownKnifeEntity::new, level, item, victim,
                    victim.getRandom().nextFloat() * 360.0F * victim.getRandom().nextIntBetweenInclusive(-1, 1),
                    victim.getRandom().nextFloat(),
                    victim.getRandom().nextFloat() * 360.0F * victim.getRandom().nextIntBetweenInclusive(-1, 1),
                    0.15F,
                    1.0F);
                knife.setNoDamage();
                knife.pickup = item.has(DataComponents.INTANGIBLE_PROJECTILE) ? AbstractArrow.Pickup.CREATIVE_ONLY : AbstractArrow.Pickup.ALLOWED;
            }
        }
    }
}
