package com.memedream.classicmobs.entity;

import com.memedream.classicmobs.entity.ai.goals.DodoTemptGoal;
import com.memedream.classicmobs.init.ModEntities;
import com.memedream.classicmobs.init.ModSounds;
import com.memedream.classicmobs.init.ModTags;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.control.LookControl;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.storage.ValueInput;
import net.minecraft.world.level.storage.ValueOutput;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Optional;

public class DodoEntity extends Animal {

    public float oFlap;
    public float flap;
    public float oFlapSpeed;
    public float flapSpeed;
    public float flapping = 1.0F;
    private float nextFlap = 1.0F;

    private static final EntityDataAccessor<Boolean> JUMPY = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.BOOLEAN);
    private static final EntityDataAccessor<Integer> HEAD_SHAKE_TIMER = SynchedEntityData.defineId(DodoEntity.class, EntityDataSerializers.INT);
    private DodoFoodData dailyFood = DodoFoodData.EMPTY;

    private final DummyLookControl dummyLookControl = new DummyLookControl(this);

    public DodoEntity(EntityType<? extends Animal> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    protected void defineSynchedData(SynchedEntityData.Builder builder) {
        super.defineSynchedData(builder);
        builder.define(HEAD_SHAKE_TIMER, 0);
        builder.define(JUMPY, false);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new PanicGoal(this, 1.4D));
        this.goalSelector.addGoal(2, new BreedGoal(this, 1.0D));
        this.goalSelector.addGoal(3, new DodoTemptGoal(this, 1.0D, this::isFood));
        this.goalSelector.addGoal(4, new FollowParentGoal(this, 1.1D));
        this.goalSelector.addGoal(5, new WaterAvoidingRandomStrollGoal(this, 1.0D));
        this.goalSelector.addGoal(6, new LookAtPlayerGoal(this, Player.class, 6.0F));
        this.goalSelector.addGoal(7, new RandomLookAroundGoal(this));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Animal.createAnimalAttributes()
            .add(Attributes.MAX_HEALTH, 6.0D)
            .add(Attributes.MOVEMENT_SPEED, 0.25D)
            .add(Attributes.FOLLOW_RANGE, 24.0D);
    }

    @Override
    public void aiStep() {
        super.aiStep();
        if (this.getHeadShakeTimer() > 0) {
            this.getEntityData().set(HEAD_SHAKE_TIMER, this.getHeadShakeTimer() - 1);
        }

        if (this.isJumpy() && this.onGround()) {
            this.jumpFromGround();
        }

        if (!this.level().isClientSide()) {
            if (this.dailyFood.lastDayCheck() < this.level().getGameTime() - 24000L) {
                Optional<Item> foodItem = BuiltInRegistries.ITEM
                    .get(ModTags.Items.DODO_FOOD)
                    .flatMap(tag -> tag.getRandomElement(this.getRandom()))
                    .map(Holder::value);
                foodItem.ifPresent(item -> this.dailyFood = new DodoFoodData(new ItemStack(item), this.level().getGameTime()));
            }
        }

        this.oFlap = this.flap;
        this.oFlapSpeed = this.flapSpeed;
        this.flapSpeed = this.flapSpeed + (this.onGround() ? -1.0F : 4.0F) * 0.3F;
        this.flapSpeed = Mth.clamp(this.flapSpeed, 0.0F, 1.0F);
        if (!this.onGround() && this.flapping < 1.0F) {
            this.flapping = 1.0F;
        }

        this.flapping *= 0.9F;
        Vec3 movement = this.getDeltaMovement();
        if (!this.onGround() && movement.y < 0.0) {
            this.setDeltaMovement(movement.multiply(1.0, 0.6, 1.0));
        }
        this.flap = this.flap + this.flapping * 2.0F;
    }

    @Override
    protected void addAdditionalSaveData(ValueOutput output) {
        super.addAdditionalSaveData(output);
        output.store("food_data", DodoFoodData.CODEC, this.dailyFood);
    }

    @Override
    protected void readAdditionalSaveData(ValueInput input) {
        super.readAdditionalSaveData(input);
        this.dailyFood = input.read("food_data", DodoFoodData.CODEC).orElse(DodoFoodData.EMPTY);
    }

    public int getHeadShakeTimer() {
        return this.getEntityData().get(HEAD_SHAKE_TIMER);
    }

    public boolean isJumpy() {
        return this.getEntityData().get(JUMPY);
    }

    public void setJumpy(boolean jumpy) {
        this.getEntityData().set(JUMPY, jumpy);
    }

    @Override
    protected boolean isFlapping() {
        return this.flyDist > this.nextFlap;
    }

    @Override
    protected void onFlap() {
        this.nextFlap = this.flyDist + this.flapSpeed / 2.0F;
    }

    @Override
    public boolean isFood(ItemStack stack) {
        return this.dailyFood.test(stack);
    }

    @Override
    public InteractionResult mobInteract(Player player, InteractionHand hand) {
        //player.displayClientMessage(this.dailyFood.foodStack.getHoverName(), true);
        if (!this.level().isClientSide() && !this.isFood(player.getItemInHand(hand)) && this.canFallInLove()) {
            this.getEntityData().set(HEAD_SHAKE_TIMER, 25);
            this.setOldRot();
        }
        return super.mobInteract(player, hand);
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel serverLevel, AgeableMob ageableMob) {
        return ModEntities.DODO.get().create(serverLevel, EntitySpawnReason.BREEDING);
    }

    @Override
    protected SoundEvent getDeathSound() {
        return ModSounds.DODO_DEATH.get();
    }

    @Override
    protected SoundEvent getAmbientSound() {
        return ModSounds.DODO_IDLE.get();
    }

    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return ModSounds.DODO_HURT.get();
    }

    @Override
    public LookControl getLookControl() {
        return this.getHeadShakeTimer() > 0 ? this.dummyLookControl : super.getLookControl();
    }

    public record DodoFoodData(ItemStack foodStack, long lastDayCheck) {
        public static final DodoFoodData EMPTY = new DodoFoodData(ItemStack.EMPTY, -1L);

        public static final Codec<DodoFoodData> CODEC = RecordCodecBuilder.create(instance -> instance.group(
            ItemStack.CODEC.fieldOf("food").forGetter(DodoFoodData::foodStack),
            Codec.LONG.fieldOf("last_check").forGetter(DodoFoodData::lastDayCheck)
        ).apply(instance, DodoFoodData::new));

        public boolean test(ItemStack stack) {
            return !this.foodStack().isEmpty() && ItemStack.isSameItemSameComponents(stack, this.foodStack());
        }
    }

    //prevents our dodo from awkwardly moving its head around while shaking it
    private static class DummyLookControl extends LookControl {
        private DummyLookControl(Mob mob) {
            super(mob);
        }

        @Override
        public void setLookAt(double x, double y, double z, float yMaxRotSpeed, float xMaxRotAngle) {
            //NO-OP
        }

        @Override
        public void tick() {
            this.mob.xRotO = this.mob.getXRot();
            this.mob.yHeadRotO = this.mob.getYHeadRot();
        }
    }
}
