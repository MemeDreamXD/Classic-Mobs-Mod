package com.memedream.classicmobs.datagen.assets;

import com.memedream.classicmobs.init.ModParticles;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.Identifier;
import net.neoforged.neoforge.client.data.ParticleDescriptionProvider;

public class ParticleGen extends ParticleDescriptionProvider {

    public ParticleGen(PackOutput output) {
        super(output);
    }

    @Override
    protected void addDescriptions() {
        this.spriteSet(ModParticles.DRIPPING_FLESH.get(), Identifier.withDefaultNamespace("drip_hang"));
        this.spriteSet(ModParticles.FALLING_FLESH.get(), Identifier.withDefaultNamespace("drip_fall"));
        this.spriteSet(ModParticles.LANDING_FLESH.get(), Identifier.withDefaultNamespace("drip_land"));
    }
}
