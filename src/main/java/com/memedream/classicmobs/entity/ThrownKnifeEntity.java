package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.init.ModDataAttachments;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.particles.ItemParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.enchantment.EnchantmentHelper;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.Vec3;

public class ThrownKnifeEntity extends AbstractArrow {

    private static final int SOFT_BLOCK_DAMAGE = 2;
    private static final int ENTITY_HIT_DAMAGE = 5;
    private static final int HARD_BLOCK_DAMAGE = 10;
    private boolean hitSomethingAlready;
    private static final EntityDataAccessor<ItemStack> DATA_ITEM_STACK = SynchedEntityData.defineId(ThrownKnifeEntity.class, EntityDataSerializers.ITEM_STACK);

    public ThrownKnifeEntity(EntityType<? extends AbstractArrow> type, Level level) {
        super(type, level);
    }

    public ThrownKnifeEntity(Level level, LivingEntity shooter, ItemStack stack) {
        super(ModEntities.THROWN_KNIFE.get(), shooter, level, stack, null);
        this.setPickupItemStack(stack);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(DATA_ITEM_STACK, this.getDefaultPickupItem());
    }

    @Override
    protected void onHitBlock(BlockHitResult result) {
        BlockState state = this.level().getBlockState(result.getBlockPos());
        boolean hardBlock = state.getDestroySpeed(this.level(), result.getBlockPos()) > 10;
        if (this.level() instanceof ServerLevel serverLevel && !this.hitSomethingAlready) {
            this.getPickupItemStackOrigin().hurtAndBreak(hardBlock ? HARD_BLOCK_DAMAGE : SOFT_BLOCK_DAMAGE, serverLevel, null, _ -> this.onBroken());
            this.hitSomethingAlready = true;
        }
        if (hardBlock) {
            Vec3 delta = this.getDeltaMovement();
            float velocity = (float) delta.length() * 0.5F;
            if (velocity < 0.1F) {
                super.onHitBlock(result);
                return;
            }
            this.setPosRaw(this.xo, this.yo, this.zo);
            if (result.getDirection().getAxis() == Direction.Axis.Y) {
                this.shoot(delta.x(), delta.reverse().y(), delta.z(), velocity, 0.0F);
            } else if (result.getDirection().getAxis() == Direction.Axis.X) {
                this.shoot(delta.reverse().x(), delta.reverse().y(), delta.z(), velocity, 0.0F);
            } else {
                this.shoot(delta.x(), delta.reverse().y(), delta.reverse().z(), velocity, 0.0F);
            }
            this.setDeltaMovement(this.getDeltaMovement().multiply(0.1D, 0.2D, 0.1D));
        } else {
            super.onHitBlock(result);
        }
    }

    public void setNoDamage() {
        this.hitSomethingAlready = true;
    }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        Entity entity = result.getEntity();
        float f = (float) this.getPickupItemStackOrigin().getAttributeModifiers().compute(Attributes.ATTACK_DAMAGE, 1.0D, EquipmentSlot.MAINHAND);
        Entity entity1 = this.getOwner();
        DamageSource damagesource = this.damageSources().trident(this, entity1 == null ? this : entity1);
        if (this.level() instanceof ServerLevel serverlevel) {
            f = EnchantmentHelper.modifyDamage(serverlevel, this.getWeaponItem(), entity, damagesource, f);
        }
        this.hitSomethingAlready = true;
        if (entity.hurtOrSimulate(damagesource, f)) {
            if (entity.getType() == EntityType.ENDERMAN) {
                return;
            }

            if (this.level() instanceof ServerLevel serverlevel) {
                EnchantmentHelper.doPostAttackEffectsWithItemSource(serverlevel, entity, damagesource, this.getWeaponItem());
                this.getPickupItemStackOrigin().hurtAndBreak(ENTITY_HIT_DAMAGE, serverlevel, null, _ -> this.level().broadcastEntityEvent(this, (byte) 5));
            }

            if (entity instanceof LivingEntity livingentity) {
                this.doKnockback(livingentity, damagesource);
                this.doPostHurtEffects(livingentity);
                if (!this.getPickupItemStackOrigin().isEmpty()) {
                    livingentity.getData(ModDataAttachments.STUCK_KNIVES).addKnifeToEntity(this.getPickupItemStackOrigin(), livingentity);
                }
            }
            this.discard();
        }
    }

    private void onBroken() {
        this.level().broadcastEntityEvent(this, (byte) 5);
        this.discard();
    }

    @Override
    public void tickDespawn() {
        if (this.pickup != AbstractArrow.Pickup.ALLOWED) {
            super.tickDespawn();
        }
    }

    @Override
    public void handleEntityEvent(byte id) {
        if (id == 5) {
            if (!this.getPickupItemStackOrigin().isEmpty()) {
                Holder<SoundEvent> breakSound = this.getPickupItemStackOrigin().get(DataComponents.BREAK_SOUND);
                if (breakSound != null && !this.isSilent()) {
                    this.level().playLocalSound(this.blockPosition(), breakSound.value(), this.getSoundSource(), 0.8F, 0.8F + this.getRandom().nextFloat() * 0.4F, false);
                }

                this.spawnItemParticles(this.getPickupItemStackOrigin(), 5);
            }
        } else {
            super.handleEntityEvent(id);
        }
    }

    @Override
    public ItemStack getWeaponItem() {
        return this.getPickupItemStackOrigin();
    }

    @Override
    protected ItemStack getPickupItem() {
        return this.getPickupItemStackOrigin().copy();
    }

    @Override
    protected ItemStack getDefaultPickupItem() {
        return new ItemStack(ModItems.WOODEN_KNIFE.get());
    }

    @Override
    public ItemStack getPickupItemStackOrigin() {
        return this.getEntityData().get(DATA_ITEM_STACK);
    }

    @Override
    protected void setPickupItemStack(ItemStack stack) {
        this.getEntityData().set(DATA_ITEM_STACK, stack);
    }

    @Override
    public boolean shouldRender(double x, double y, double z) {
        return true;
    }

    public void spawnItemParticles(ItemStack stack, int count) {
        if (!stack.isEmpty()) {
            ItemParticleOption breakParticle = new ItemParticleOption(ParticleTypes.ITEM, ItemStackTemplate.fromNonEmptyStack(stack));

            for (int i = 0; i < count; i++) {
                Vec3 d = new Vec3((this.getRandom().nextFloat() - 0.5D) * 0.1D, this.getRandom().nextFloat() * 0.1D + 0.1D, 0.0D);
                d = d.xRot(-this.getXRot() * Mth.DEG_TO_RAD);
                d = d.yRot(-this.getYRot() * Mth.DEG_TO_RAD);
                double y1 = -this.getRandom().nextFloat() * 0.6D - 0.3D;
                Vec3 p = new Vec3((this.getRandom().nextFloat() - 0.5D) * 0.3D, y1, 0.6D);
                p = p.xRot(-this.getXRot() * Mth.DEG_TO_RAD);
                p = p.yRot(-this.getYRot() * Mth.DEG_TO_RAD);
                p = p.add(this.getX(), this.getEyeY(), this.getZ());
                this.level().addParticle(breakParticle, p.x, p.y, p.z, d.x, d.y + 0.05D, d.z);
            }
        }
    }
}
