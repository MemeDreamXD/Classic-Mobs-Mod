package com.memedream.classicmobs.item;

import net.neoforged.neoforge.common.ItemAbilities;
import net.neoforged.neoforge.common.ItemAbility;

import java.util.HashSet;
import java.util.Set;

public class ModItemAbilities {

    public static final Set<ItemAbility> DEFAULT_PICKAXE_AXE_ACTIONS = of(ItemAbilities.DEFAULT_AXE_ACTIONS, ItemAbilities.DEFAULT_PICKAXE_ACTIONS);
    public static final Set<ItemAbility> DEFAULT_MATTOCK_ACTIONS = of(ItemAbilities.DEFAULT_HOE_ACTIONS, ItemAbilities.DEFAULT_SHOVEL_ACTIONS);

    @SafeVarargs
    private static Set<ItemAbility> of(Set<ItemAbility>... actionSets) {
        Set<ItemAbility> abilities = new HashSet<>();
        for (Set<ItemAbility> inheritedAbilities : actionSets) {
            abilities.addAll(inheritedAbilities);
        }
        return abilities;
    }
}