package com.memedream.classicmobs.client.audio;

import com.memedream.classicmobs.entity.BolaEntity;
import com.memedream.classicmobs.init.ModSounds;
import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.sounds.SoundSource;

public class BolaSoundInstance extends AbstractTickableSoundInstance {

    private final BolaEntity bola;

    public BolaSoundInstance(BolaEntity bola) {
        super(ModSounds.BOLA_FLY.get(), SoundSource.AMBIENT, SoundInstance.createUnseededRandom());
        this.bola = bola;
        this.looping = true;
        this.delay = 0;
    }

    @Override
    public void tick() {
        if (this.bola.isAlive()) {
            this.x = this.bola.getX();
            this.y = this.bola.getY();
            this.z = this.bola.getZ();
        } else {
            this.stop();
        }
    }
}
