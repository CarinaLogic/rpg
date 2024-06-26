package me.carina.rpg.client.scenes;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TransformDrawable;
import com.badlogic.gdx.utils.IntMap;
import com.badlogic.gdx.utils.Pool;
import com.badlogic.gdx.utils.PooledLinkedList;
import com.badlogic.gdx.utils.Pools;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import me.carina.rpg.common.util.Map;

public class CanvasStage extends GameStage<BaseScreen> {
    private final Pool<DrawEntry> pool = Pools.get(DrawEntry.class);

    public CanvasStage(){
        super(new ScreenViewport());
        getRoot().setTouchable(Touchable.disabled);
    }

    @Override
    public void draw() {
        //NOOP
    }
    //TODO fix performance issue
    //The operation is HEAVY, use accordingly
    public void draw(Drawable drawable, float x, float y, float width, float height, Color color, float parentAlpha) {
        Camera camera = getViewport().getCamera();
        camera.update();
        Batch batch = getBatch();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setProjectionMatrix(camera.combined);
        //Convert to stage coords
        Vector2 pos = new Vector2(x,y);
        screenToStageCoordinates(pos);
        Vector2 tar = new Vector2(x+width,y+height);
        screenToStageCoordinates(tar);
        batch.begin();
        drawable.draw(batch, pos.x, pos.y, tar.x - pos.x, tar.y - pos.y);
        batch.end();
    }
    public void draw(TransformDrawable drawable, float x, float y, float originX, float originY, float width,
                     float height,float scaleX, float scaleY, float rotation, Color color, float parentAlpha){
        Camera camera = getViewport().getCamera();
        camera.update();
        Batch batch = getBatch();
        batch.setColor(color.r, color.g, color.b, color.a * parentAlpha);
        batch.setProjectionMatrix(camera.combined);
        Vector2 pos = new Vector2(x,y);
        screenToStageCoordinates(pos);
        Vector2 tar = new Vector2(x+width,y-height);
        screenToStageCoordinates(tar);
        Vector2 ori = new Vector2(originX,originY);
        screenToStageCoordinates(ori);
        batch.begin();
        drawable.draw(batch,pos.x, pos.y, ori.x, ori.y,tar.x - pos.x, tar.y - pos.y,scaleX,scaleY,rotation);
        batch.end();
    }

    @Override
    public void init() {

    }

    public static class DrawEntry{
        TransformDrawable drawable;
        float x,  y, originX,  originY, width, height, scaleX, scaleY, rotation;
        Color color;
        float parentAlpha;
    }
}
