package com.memedream.classicmobs.item;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.entity.ThrownKnifeEntity;
import com.mojang.blaze3d.vertex.PoseStack;
import net.minecraft.client.model.HumanoidModel;
import net.minecraft.client.player.LocalPlayer;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.component.DataComponents;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.stats.Stats;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.Projectile;
import net.minecraft.world.entity.projectile.arrow.AbstractArrow;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemUseAnimation;
import net.minecraft.world.item.ToolMaterial;
import net.minecraft.world.item.component.ItemAttributeModifiers;
import net.minecraft.world.item.component.Tool;
import net.minecraft.world.item.component.Weapon;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.client.extensions.common.IClientItemExtensions;
import org.jspecify.annotations.Nullable;

import java.util.List;

public class KnifeItem extends Item {

    public static final Identifier KNIFE_REDUCED_INTERACTION_RANGE = ClassicMobs.prefix("reduced_knife_interaction");
    public static final Identifier STAB_RANGE = ClassicMobs.prefix("stabby_knife_range");
    public static final Identifier STAB_SPEED = ClassicMobs.prefix("stabby_knife_speed");

    public KnifeItem(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline, Properties properties) {
        super(createKnifeProperties(properties, material, attackDamageBaseline, attackSpeedBaseline));
    }

    private static Item.Properties createKnifeProperties(Item.Properties properties, ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        HolderGetter<Block> registrationLookup = BuiltInRegistries.acquireBootstrapRegistrationLookup(BuiltInRegistries.BLOCK);
        return properties
            .durability(material.durability())
            .repairable(material.repairItems())
            .enchantable(material.enchantmentValue())
            .component(
                DataComponents.TOOL,
                new Tool(
                    List.of(
                        Tool.Rule.minesAndDrops(HolderSet.direct(Blocks.COBWEB.builtInRegistryHolder()), 15.0F),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SWORD_INSTANTLY_MINES), Float.MAX_VALUE),
                        Tool.Rule.overrideSpeed(registrationLookup.getOrThrow(BlockTags.SWORD_EFFICIENT), 1.5F)
                    ),
                    1.0F,
                    2,
                    false
                )
            )
            .attributes(createKnifeAttributes(material, attackDamageBaseline, attackSpeedBaseline))
            .component(DataComponents.WEAPON, new Weapon(1));
    }

    private static ItemAttributeModifiers createKnifeAttributes(ToolMaterial material, float attackDamageBaseline, float attackSpeedBaseline) {
        return ItemAttributeModifiers.builder()
            .add(
                Attributes.ATTACK_DAMAGE,
                new AttributeModifier(Item.BASE_ATTACK_DAMAGE_ID, attackDamageBaseline + material.attackDamageBonus(), AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ATTACK_SPEED,
                new AttributeModifier(Item.BASE_ATTACK_SPEED_ID, attackSpeedBaseline, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND
            )
            .add(
                Attributes.ENTITY_INTERACTION_RANGE,
                new AttributeModifier(KNIFE_REDUCED_INTERACTION_RANGE, -1.0D, AttributeModifier.Operation.ADD_VALUE),
                EquipmentSlotGroup.MAINHAND)
            .build();
    }

    @Override
    public void postHurtEnemy(ItemStack stack, LivingEntity mob, LivingEntity attacker) {
        if (attacker.isShiftKeyDown()) {
            mob.invulnerableTime /= 2;
        }
    }

    @Override
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if (!player.isSecondaryUseActive()) {
            player.startUsingItem(hand);
            return InteractionResult.CONSUME;
        }
        return super.use(level, player, hand);
    }

    @Override
    public boolean releaseUsing(ItemStack stack, Level level, LivingEntity entity, int timeLeft) {
        if (entity instanceof Player player) {
            int i = this.getUseDuration(stack, entity) - timeLeft;
            if (i >= 10) {
                if (level instanceof ServerLevel serverLevel) {
                    ItemStack thrownItemStack = stack.consumeAndReturn(1, player);
                    ThrownKnifeEntity knife = Projectile.spawnProjectileFromRotation(ThrownKnifeEntity::new, serverLevel, thrownItemStack, player, 0.0F, 2.5F, 1.0F);
                    if (player.hasInfiniteMaterials()) {
                        knife.pickup = AbstractArrow.Pickup.CREATIVE_ONLY;
                    }
                    level.playSound(null, knife, SoundEvents.TRIDENT_THROW.value(), SoundSource.PLAYERS, 1.0F, 1.0F);
                }
                player.awardStat(Stats.ITEM_USED.get(this));
                return true;
            }
        }
        return false;
    }

    @Override
    public int getUseDuration(ItemStack itemStack, LivingEntity user) {
        return 72000;
    }

    @Override
    public ItemUseAnimation getUseAnimation(ItemStack itemStack) {
        return ItemUseAnimation.TRIDENT;
    }

    @Override
    public void inventoryTick(ItemStack stack, ServerLevel level, Entity owner, @Nullable EquipmentSlot slot) {
        if (owner instanceof Player player) {
            var interactionAttrib = player.getAttribute(Attributes.ENTITY_INTERACTION_RANGE);
            var atkSpeedAttrib = player.getAttribute(Attributes.ATTACK_SPEED);
            if (interactionAttrib != null && atkSpeedAttrib != null) {
                if (player.isShiftKeyDown()) {
                    interactionAttrib.addOrUpdateTransientModifier(new AttributeModifier(STAB_RANGE, -1.0D, AttributeModifier.Operation.ADD_VALUE));
                    atkSpeedAttrib.addOrUpdateTransientModifier(new AttributeModifier(STAB_SPEED, 6.0D, AttributeModifier.Operation.ADD_VALUE));
                } else {
                    interactionAttrib.removeModifier(STAB_RANGE);
                    atkSpeedAttrib.removeModifier(STAB_SPEED);
                }
            }
        }
    }

    public static class KnifeAnimation implements IClientItemExtensions {
        @Override
        public HumanoidModel.@Nullable ArmPose getArmPose(LivingEntity entity, InteractionHand hand, ItemStack stack) {
            if (entity.isShiftKeyDown() && entity.onGround()) {
                return HumanoidModel.ArmPose.valueOf("CLASSIC_MOBS_KNIFE_STABBY");
            }
            return IClientItemExtensions.super.getArmPose(entity, hand, stack);
        }

        @Override
        public boolean applyForgeHandTransform(PoseStack poseStack, LocalPlayer player, HumanoidArm arm, ItemStack itemInHand, float partialTick, float equipProcess, float swingProcess) {
            return IClientItemExtensions.super.applyForgeHandTransform(poseStack, player, arm, itemInHand, partialTick, equipProcess, swingProcess);
        }
    }
}
