package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ClassicMobs.MOD_ID);

    public static final Supplier<SoundEvent> DODO_IDLE = registerSoundEvent("dodo_idle");
    public static final Supplier<SoundEvent> DODO_HURT = registerSoundEvent("dodo_hurt");
    public static final Supplier<SoundEvent> DODO_DEATH = registerSoundEvent("dodo_death");
    public static final Supplier<SoundEvent> HARPY_IDLE = registerSoundEvent("harpy_idle");
    public static final Supplier<SoundEvent> HARPY_HURT = registerSoundEvent("harpy_hurt");
    public static final Supplier<SoundEvent> HARPY_DEATH = registerSoundEvent("harpy_death");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
