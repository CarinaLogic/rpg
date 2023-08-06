package me.carina.rpg.common;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;

public abstract class ImageDisplay extends Display{

    @Override
    public void draw(Batch batch, float parentAlpha) {
        addContext();
        if (!populated) populateChild();
        tick();
        Color color = getColor();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        setSize(getDisplayWidth(),getDisplayHeight());
        setPosition(getDisplayX(),getDisplayY(),getAlignment());
        Drawable drawable = getDrawable();
        if (drawable == null) return;
        if (drawable instanceof TransformDrawable) {
            TransformDrawable transformDrawable = (TransformDrawable) drawable;
            transformDrawable.draw(batch,getX(),getY(),getOriginX(),getOriginY(),getWidth(),getHeight(),
                    getScaleX(),getScaleY(),getRotation());
            return;
        }
        drawable.draw(batch, getX(),getY(),getWidth(),getHeight());
    }

    public abstract Drawable getDrawable();
}
