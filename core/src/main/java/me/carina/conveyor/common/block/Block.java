package me.carina.conveyor.common.block;

import me.carina.conveyor.common.Definition;
import me.carina.conveyor.common.Feature;
import me.carina.conveyor.common.file.AssetGroup;

public class Block extends Feature {
    Direction direction;
    int width, height;
    int x,y,z;
    Recipes recipes;
    IOProperties ioProperties;

    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick() {
        recipes.
    }
}
