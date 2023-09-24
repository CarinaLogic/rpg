package me.carina.conveyor.common.battle;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.conveyor.Game;
import me.carina.conveyor.common.Array2DDisplayHandler;
import me.carina.conveyor.common.Display;

public class TilesDisplay extends Group implements Display<Tiles> {
    Tiles tiles;
    Array2DDisplayHandler handler = new Array2DDisplayHandler(
            this, feature -> addActor(feature.newDisplay(TileDisplay.class))
    ) {
        @Override
        public Tiles getIterable() {
            return tiles;
        }
    };
    public TilesDisplay(Tiles tiles){
        this.tiles = tiles;
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }


    @Override
    public Tiles getFeature() {
        return tiles;
    }
}