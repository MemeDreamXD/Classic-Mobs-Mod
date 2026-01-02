package com.memedream.classicmobs.client.particle;

import com.memedream.classicmobs.init.ModParticles;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.DripParticle;
import net.minecraft.client.particle.Particle;
import net.minecraft.client.particle.TextureSheetParticle;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.fml.util.ObfuscationReflectionHelper;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.reflect.Field;

@SuppressWarnings("unused") //dont care shut up
public class FleshDripParticle extends DripParticle {

    private static final MethodHandles.Lookup LOOKUP = MethodHandles.lookup();
    private static final Field particle_Gravity = ObfuscationReflectionHelper.findField(Particle.class, "gravity");
    private static final MethodHandle handle_particle_Gravity_set;
    private static final MethodHandle handle_particle_Gravity_get;

    static {
        MethodHandle tmp_handle_particle_Gravity_set = null;
        MethodHandle tmp_handle_particle_Gravity_get = null;
        try {
            tmp_handle_particle_Gravity_set = LOOKUP.unreflectSetter(particle_Gravity);
            tmp_handle_particle_Gravity_get = LOOKUP.unreflectGetter(particle_Gravity);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        handle_particle_Gravity_set = tmp_handle_particle_Gravity_set;
        handle_particle_Gravity_get = tmp_handle_particle_Gravity_get;
    }

    public FleshDripParticle(ClientLevel level, double x, double y, double z) {
        super(level, x, y, z, Fluids.EMPTY);
    }

    public static TextureSheetParticle createFleshHangParticle(
            SimpleParticleType type,
            ClientLevel level,
            double x,
            double y,
            double z,
            double xd,
            double yd,
            double zd
    ) {
        DripParticle.DripHangParticle particle = new DripParticle.DripHangParticle(
                level, x, y, z, Fluids.EMPTY, ModParticles.FALLING_FLESH.get()
        );
        try {
            handle_particle_Gravity_set.invokeExact((Particle) particle, (float) handle_particle_Gravity_get.invokeExact((Particle) particle) * 0.01F);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        particle.setLifetime(100);
        particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
        return particle;
    }

    public static TextureSheetParticle createFleshFallParticle(
            SimpleParticleType type,
            ClientLevel level,
            double x,
            double y,
            double z,
            double xd,
            double yd,
            double zd
    ) {
        DripParticle particle = new DripParticle.FallAndLandParticle(
                level, x, y, z, Fluids.EMPTY, ModParticles.LANDING_FLESH.get()
        );
        try {
            handle_particle_Gravity_set.invokeExact((Particle) particle, 0.01F);
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
        return particle;
    }

    public static TextureSheetParticle createFleshLandParticle(
            SimpleParticleType type,
            ClientLevel level,
            double x,
            double y,
            double z,
            double xd,
            double yd,
            double zd
    ) {
        DripParticle particle = new DripParticle.DripLandParticle(level, x, y, z, Fluids.EMPTY);
        particle.setLifetime((int)(28.0 / (Math.random() * 0.8 + 0.2)));
        particle.setColor(106.0F / 255.0F, 93.0F / 255.0F, 24.0F / 255.0F);
        return particle;
    }
}
