package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, ClassicMobs.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_IDLE = registerSoundEvent("entity.classicmobs.dodo.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_HURT = registerSoundEvent("entity.classicmobs.dodo.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> DODO_DEATH = registerSoundEvent("entity.classicmobs.dodo.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_IDLE = registerSoundEvent("entity.classicmobs.harpy.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_HURT = registerSoundEvent("entity.classicmobs.harpy.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> HARPY_DEATH = registerSoundEvent("entity.classicmobs.harpy.death");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_IDLE = registerSoundEvent("entity.classicmobs.antlion.idle");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_HURT = registerSoundEvent("entity.classicmobs.antlion.hurt");
    public static final DeferredHolder<SoundEvent, SoundEvent> ANTLION_DEATH = registerSoundEvent("entity.classicmobs.antlion.death");

    public static final DeferredHolder<SoundEvent, SoundEvent> BOLA_WINDUP = registerSoundEvent("item.classicmobs.bola.windup");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLA_THROW = registerSoundEvent("item.classicmobs.bola.throw");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLA_SNAG = registerSoundEvent("item.classicmobs.bola.snag");
    public static final DeferredHolder<SoundEvent, SoundEvent> BOLA_FLY = registerSoundEvent("item.classicmobs.bola.fly");

    private static DeferredHolder<SoundEvent, SoundEvent> registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(ClassicMobs.MOD_ID, name);
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(id));
    }
}
