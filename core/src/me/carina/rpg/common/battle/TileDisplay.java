package me.carina.rpg.common.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.Feature;
import me.carina.rpg.common.ImageDisplay;

public class TileDisplay extends ImageDisplay {
    Tile tile;
    public TileDisplay(Tile tile){
        this.tile = tile;
    }

    @Override
    public Tile getFeature() {
        return tile;
    }

    @Override
    public boolean fillChildren() {
        return true;
    }
}
