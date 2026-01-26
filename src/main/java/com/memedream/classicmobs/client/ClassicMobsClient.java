package com.memedream.classicmobs.client;

import com.memedream.classicmobs.client.audio.BolaSoundInstance;
import com.memedream.classicmobs.entity.BolaEntity;
import net.minecraft.client.Minecraft;

//use this to call any client side code in normal classes, after using proper safe checks of course
public class ClassicMobsClient {

    public static void attachBolaSound(BolaEntity bola) {
        Minecraft.getInstance().getSoundManager().queueTickingSound(new BolaSoundInstance(bola));
    }
}
