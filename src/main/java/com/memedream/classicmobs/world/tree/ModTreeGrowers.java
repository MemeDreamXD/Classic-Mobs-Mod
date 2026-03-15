package com.memedream.classicmobs.world.tree;

import com.memedream.classicmobs.init.ModFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {

    public static final TreeGrower PALM = new TreeGrower("palm_tree",
        Optional.empty(),
        Optional.of(ModFeatures.PALM_TREE_CF),
        Optional.empty());
}
