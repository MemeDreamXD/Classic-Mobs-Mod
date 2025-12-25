package com.memedream.classicmobs.sound;

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

    public static final Supplier<SoundEvent> DODO_IDLE_1 = registerSoundEvent("dodo_idle_1");
    public static final Supplier<SoundEvent> DODO_IDLE_2 = registerSoundEvent("dodo_idle_2");
    public static final Supplier<SoundEvent> DODO_IDLE_3 = registerSoundEvent("dodo_idle_3");
    public static final Supplier<SoundEvent> DODO_HURT = registerSoundEvent("dodo_hurt");
    public static final Supplier<SoundEvent> DODO_DEATH = registerSoundEvent("dodo_death");

    private static Supplier<SoundEvent> registerSoundEvent(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(ClassicMobs.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
