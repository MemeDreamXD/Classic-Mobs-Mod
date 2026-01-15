package com.memedream.classicmobs.block;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.component.DataComponents;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.util.StringRepresentable;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.component.Consumable;
import net.minecraft.world.item.component.ConsumableListener;
import net.minecraft.world.item.consume_effects.ApplyStatusEffectsConsumeEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import java.util.List;
import java.util.Locale;
import java.util.Map;

public class MeatBlock extends ConfiguredDirectionalBlock {

    public static final IntegerProperty BITES = IntegerProperty.create("bites", 0, 3);
    public static final MapCodec<MeatBlock> CODEC = RecordCodecBuilder.mapCodec(instance -> instance.group(
                    BoneType.CODEC.fieldOf("bone").forGetter(o -> o.type),
                    propertiesCodec())
            .apply(instance, MeatBlock::new));

    private final BoneType type;

    public MeatBlock(BoneType type, Properties properties) {
        super(properties);
        this.type = type;
        this.registerDefaultState(this.getStateDefinition().any().setValue(BITES, 0));
    }

    @Override
    protected MapCodec<? extends DirectionalBlock> codec() {
        return CODEC;
    }

    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        if (state.getValue(BITES) == 0) return Shapes.block();
        return switch (this.type) {
            case NORMAL -> MeatShapes.NORMAL_SHAPES.get(state.getValue(FACING)).get(state.getValue(BITES) - 1);
            case THIN -> MeatShapes.THIN_SHAPES.get(state.getValue(FACING)).get(state.getValue(BITES) - 1);
            case NONE -> MeatShapes.BONELESS_SHAPES.get(state.getValue(FACING)).get(state.getValue(BITES) - 1);
        };
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult result) {
        Consumable consumable = this.asItem().components().get(DataComponents.CONSUMABLE);

        if (consumable != null) {
            if (level.isClientSide()) {
                if (this.eat(level, pos, state, player, consumable).consumesAction()) {
                    return InteractionResult.SUCCESS;
                }

                if (player.getMainHandItem().isEmpty()) {
                    return InteractionResult.CONSUME;
                }
            }

            return this.eat(level, pos, state, player, consumable);
        }
        return super.useWithoutItem(state, level, pos, player, result);
    }

    public InteractionResult eat(Level level, BlockPos pos, BlockState state, Player player, Consumable consumable) {
        ItemStack stack = this.asItem().getDefaultInstance();
        stack.set(DataComponents.POTION_DURATION_SCALE, 0.25F);
        if (!player.canEat(false) || !consumable.canConsume(player, stack)) {
            return InteractionResult.PASS;
        } else {
            RandomSource random = player.getRandom();
            consumable.emitParticlesAndSounds(random, player, stack, 16);
            stack.getAllOfType(ConsumableListener.class).forEach(component -> {
                if (component instanceof FoodProperties foodProps) {
                    player.getFoodData().eat(foodProps.nutrition() / 4, foodProps.saturation() / 4);
                } else {
                    component.onConsume(level, player, stack, consumable);
                }
            });
            if (!level.isClientSide()) {
                consumable.onConsumeEffects().forEach(action -> {
                    if (action instanceof ApplyStatusEffectsConsumeEffect(List<MobEffectInstance> effects, float probability)) {
                        if (player.getRandom().nextFloat() < probability) {
                            for (MobEffectInstance applyEffect : effects) {
                                player.addEffect(new MobEffectInstance(applyEffect.getEffect(), applyEffect.getDuration() / 4, applyEffect.getAmplifier() / 4, applyEffect.isAmbient(), applyEffect.isVisible(), applyEffect.showIcon()));
                            }
                        }
                    } else {
                        action.apply(level, stack, player);
                    }
                });
            }

            int i = state.getValue(BITES);
            if (i < 3) {
                level.setBlock(pos, state.setValue(BITES, i + 1), 3);
            } else {
                //TODO control this either via the using conversion data component or a loot table
                if (this.type != BoneType.NONE) {
                    Block.popResource(level, pos, new ItemStack(Items.BONE));
                    level.playSound(null, pos, SoundEvents.ITEM_FRAME_REMOVE_ITEM, SoundSource.BLOCKS, 1.0F, 1.0F);
                }
                level.removeBlock(pos, false);
                level.gameEvent(player, GameEvent.BLOCK_DESTROY, pos);
            }

            return InteractionResult.SUCCESS;
        }
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder.add(BITES));
    }

    public enum BoneType implements StringRepresentable {
        NORMAL,
        THIN,
        NONE;

        public static final EnumCodec<BoneType> CODEC = StringRepresentable.fromEnum(BoneType::values);

        @Override
        public String getSerializedName() {
            return this.name().toLowerCase(Locale.ROOT);
        }
    }

    //avert your eyes
    static class MeatShapes {
        private static final Map<Direction, List<VoxelShape>> NORMAL_SHAPES = Map.of(
                Direction.UP, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(6.0D, 12.0D, 6.0D, 10.0D, 16.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(6.0D, 8.0D, 6.0D, 10.0D, 16.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(6.0D, 4.0D, 6.0D, 10.0D, 16.0D, 10.0D))
                ),
                Direction.DOWN, List.of(
                        Shapes.or(Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 0.0D, 6.0D, 10.0D, 4.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 0.0D, 6.0D, 10.0D, 12.0D, 10.0D))
                ),
                Direction.NORTH, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 4.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 8.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D), Block.box(6.0D, 6.0D, 0.0D, 10.0D, 10.0D, 12.0D))
                ),
                Direction.SOUTH, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D), Block.box(6.0D, 6.0D, 12.0D, 10.0D, 10.0D, 16.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D), Block.box(6.0D, 6.0D, 8.0D, 10.0D, 10.0D, 16.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D), Block.box(6.0D, 6.0D, 4.0D, 10.0D, 10.0D, 16.0D))
                ),
                Direction.WEST, List.of(
                        Shapes.or(Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 6.0D, 6.0D, 4.0D, 10.0D, 10.0D)),
                        Shapes.or(Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 6.0D, 6.0D, 8.0D, 10.0D, 10.0D)),
                        Shapes.or(Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 6.0D, 6.0D, 12.0D, 10.0D, 10.0D))
                ),
                Direction.EAST, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D), Block.box(12.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D), Block.box(8.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.box(4.0D, 6.0D, 6.0D, 16.0D, 10.0D, 10.0D))
                )
        );

        private static final Map<Direction, List<VoxelShape>> THIN_SHAPES = Map.of(
                Direction.UP, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D), Block.box(7.0D, 12.0D, 7.0D, 9.0D, 16.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D), Block.box(7.0D, 8.0D, 7.0D, 9.0D, 16.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D), Block.box(7.0D, 4.0D, 7.0D, 9.0D, 16.0D, 9.0D))
                ),
                Direction.DOWN, List.of(
                        Shapes.or(Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 0.0D, 7.0D, 9.0D, 4.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 0.0D, 7.0D, 9.0D, 8.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 0.0D, 7.0D, 9.0D, 12.0D, 9.0D))
                ),
                Direction.NORTH, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 4.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 8.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D), Block.box(7.0D, 7.0D, 0.0D, 9.0D, 9.0D, 12.0D))
                ),
                Direction.SOUTH, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D), Block.box(7.0D, 7.0D, 12.0D, 9.0D, 9.0D, 16.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D), Block.box(7.0D, 7.0D, 8.0D, 9.0D, 9.0D, 16.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D), Block.box(7.0D, 7.0D, 4.0D, 9.0D, 9.0D, 16.0D))
                ),
                Direction.WEST, List.of(
                        Shapes.or(Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 7.0D, 7.0D, 4.0D, 9.0D, 9.0D)),
                        Shapes.or(Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 7.0D, 7.0D, 8.0D, 9.0D, 9.0D)),
                        Shapes.or(Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D), Block.box(0.0D, 7.0D, 7.0D, 12.0D, 9.0D, 9.0D))
                ),
                Direction.EAST, List.of(
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D), Block.box(12.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D), Block.box(8.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D)),
                        Shapes.or(Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D), Block.box(4.0D, 7.0D, 7.0D, 16.0D, 9.0D, 9.0D))
                )
        );

        private static final Map<Direction, List<VoxelShape>> BONELESS_SHAPES = Map.of(
                Direction.UP, List.of(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 12.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 8.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 4.0D, 16.0D)
                ),
                Direction.DOWN, List.of(
                        Block.box(0.0D, 4.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 8.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 12.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Direction.NORTH, List.of(
                        Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 8.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 12.0D, 16.0D, 16.0D, 16.0D)
                ),
                Direction.SOUTH, List.of(
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 12.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 8.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 16.0D, 16.0D, 4.0D)
                ),
                Direction.WEST, List.of(
                        Block.box(4.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(8.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D),
                        Block.box(12.0D, 0.0D, 0.0D, 16.0D, 16.0D, 16.0D)
                ),
                Direction.EAST, List.of(
                        Block.box(0.0D, 0.0D, 0.0D, 12.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 8.0D, 16.0D, 16.0D),
                        Block.box(0.0D, 0.0D, 0.0D, 4.0D, 16.0D, 16.0D)
                )
        );
    }
}
