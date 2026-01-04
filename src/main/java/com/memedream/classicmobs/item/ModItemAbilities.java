package com.memedream.classicmobs.item;

import com.google.common.collect.Sets;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static net.neoforged.neoforge.common.ItemAbilities.*;

public class ModItemAbilities {
    public static final Set<ItemAbility> DEFAULT_PICKAXE_AXE_ACTIONS;
    public static final Set<ItemAbility> DEFAULT_MATTOCK_ACTIONS;

    private static Set<ItemAbility> of(ItemAbility... actions) {
        return Stream.of(actions).collect(Collectors.toCollection(Sets::newIdentityHashSet));
    }

    static {
        DEFAULT_PICKAXE_AXE_ACTIONS = of(AXE_DIG, AXE_STRIP, AXE_SCRAPE, AXE_WAX_OFF, PICKAXE_DIG);
        DEFAULT_MATTOCK_ACTIONS = of(HOE_DIG, HOE_TILL, SHOVEL_DIG, SHOVEL_FLATTEN, SHOVEL_DOUSE);
    }
}