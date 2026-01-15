package com.memedream.classicmobs.client.particle;

import com.memedream.classicmobs.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.ParticleProvider;
import net.minecraft.client.particle.SpriteSet;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.material.Fluids;

@SuppressWarnings("unused") //dont care shut up
public class FleshDripParticle extends DripParticle {

    public FleshDripParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
        super(level, x, y, z, Fluids.EMPTY, sprite);
    }

    private static class FleshDripHangParticle extends DripHangParticle {

        public FleshDripHangParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
            super(level, x, y, z, Fluids.EMPTY, ModParticles.FALLING_FLESH.get(), sprite);
            this.gravity *= 0.01F;
        }
    }

    private static class FleshDripFallParticle extends FallAndLandParticle {

        public FleshDripFallParticle(ClientLevel level, double x, double y, double z, TextureAtlasSprite sprite) {
            super(level, x, y, z, Fluids.EMPTY, ModParticles.LANDING_FLESH.get(), sprite);
            this.gravity *= 0.01F;
        }
    }

    public static class FleshHangProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FleshHangProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            FleshDripFallParticle particle = new FleshDripFallParticle(level, x, y, z, this.sprite.get(random));
            particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
            return particle;
        }
    }

    public static class FleshFallProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FleshFallProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            FleshDripFallParticle particle = new FleshDripFallParticle(level, x, y, z, this.sprite.get(random));
            particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
            return particle;
        }
    }

    public static class FleshLandProvider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprite;

        public FleshLandProvider(SpriteSet sprite) {
            this.sprite = sprite;
        }

        public Particle createParticle(SimpleParticleType options, ClientLevel level, double x, double y, double z, double xAux, double yAux, double zAux, RandomSource random) {
            DripLandParticle particle = new DripLandParticle(level, x, y, z, Fluids.EMPTY, this.sprite.get(random));
            particle.setLifetime((int)(28.0 / (Math.random() * 0.8 + 0.2)));
            particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
            return particle;
        }
    }
}
