package com.memedream.classicmobs.init;

import com.memedream.classicmobs.ClassicMobs;
import com.memedream.classicmobs.component.entity.StuckKnifeAttachment;
import net.neoforged.neoforge.attachment.AttachmentType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

public class ModDataAttachments {
    public static final DeferredRegister<AttachmentType<?>> ATTACHMENTS = DeferredRegister.create(NeoForgeRegistries.Keys.ATTACHMENT_TYPES, ClassicMobs.MOD_ID);

    public static final DeferredHolder<AttachmentType<?>, AttachmentType<StuckKnifeAttachment>> STUCK_KNIVES = ATTACHMENTS.register("stuck_knives", () -> AttachmentType.builder(() -> new StuckKnifeAttachment()).serialize(StuckKnifeAttachment.CODEC).build());
}
