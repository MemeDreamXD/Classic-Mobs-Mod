package com.memedream.classicmobs.entity;

import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.monster.Monster;
import net.minecraft.world.level.Level;

public class SupportCreeperEntity extends Creeper {

    public SupportCreeperEntity(EntityType<? extends Creeper> entityType, Level level) {
        super(entityType, level);
    }
}
