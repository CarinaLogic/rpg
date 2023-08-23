package me.carina.rpg.common.unit;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.Game;
import me.carina.rpg.common.Display;

public class UnitDisplay extends Group implements Display<Unit> {
    Unit unit;
    float facing = 0;
    public UnitDisplay(Unit unit){
        this.unit = unit;
        addActor(unit.unitParts.newDisplay(UnitPartsDisplay.class));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        super.draw(batch, parentAlpha);
    }

    public void lookAt(float x, float y){
        facing = (float) Math.atan2(y- unit.y,x- unit.x);
    }

    public void lookAt(Unit target){
        facing = (float) Math.atan2(target.y- unit.y,target.x- unit.x);
    }

    public float getFacing() {
        return facing;
    }

    @Override
    public Unit getFeature() {
        return unit;
    }
}
