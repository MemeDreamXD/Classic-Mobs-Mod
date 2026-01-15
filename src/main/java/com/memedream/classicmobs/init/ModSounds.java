package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ClassicMobs.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_IDLE = registerSoundEvent("entity.classicmobs.dodo.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_HURT = registerSoundEvent("entity.classicmobs.dodo.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_DEATH = registerSoundEvent("entity.classicmobs.dodo.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_IDLE = registerSoundEvent("entity.classicmobs.harpy.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_HURT = registerSoundEvent("entity.classicmobs.harpy.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_DEATH = registerSoundEvent("entity.classicmobs.harpy.death");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
