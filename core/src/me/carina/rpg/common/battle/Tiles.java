package me.carina.rpg.common.battle;

import me.carina.rpg.Game;
import me.carina.rpg.common.*;
import me.carina.rpg.common.file.AssetGroup;

public class Tiles extends Array2DFeature<Tile>{

    public Tiles(){}

    @Override
    protected TilesDisplay newDisplay() {
        return new TilesDisplay(this);
    }

    @Override
    public AssetGroup getAssetGroup() {
        return null;
    }

    @Override
    public void tick(Context context) {
        if (getArray().get(0,0) == null){
            getArray().resize(10,10);
            getArray().fill(v -> Game.getInstance().getAssets().get("core","plains", Tile.class));
        }
    }
    @Override
    public Class<? extends Definition> getDefClass() {
        return null;
    }
}
