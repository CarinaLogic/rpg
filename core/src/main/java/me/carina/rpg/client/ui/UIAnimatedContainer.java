package me.carina.rpg.client.ui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.utils.Layout;
import com.badlogic.gdx.utils.Align;

public class UIAnimatedContainer<T extends Actor & Layout> extends Container<T> {
    public UIAnimatedContainer(){}

    public void addFromScreenTop(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(0,screenToLocalCoordinates(new Vector2(
                0,0
        )).y, Align.bottomLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
    }
    public void addFromScreenBottom(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(0,screenToLocalCoordinates(new Vector2(
                0, Gdx.graphics.getHeight()
        )).y,Align.topLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
    }
    public void addFromScreenLeft(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(screenToLocalCoordinates(new Vector2(
                0,0
        )).x,0,Align.bottomRight);
        actor.addAction(Actions.moveTo(0,0,0.2f));
    }
    public void addFromScreenRight(T actor){
        setActor(actor);
        actor.clearActions();
        actor.setPosition(screenToLocalCoordinates(new Vector2(
                Gdx.graphics.getWidth(),0
        )).x,0,Align.bottomLeft);
        actor.addAction(Actions.moveTo(0,0,0.2f));
    }

    @Override
    public void layout() {
        Actor actor = getActor();
        if (actor == null) return;
        float x = actor.getX();
        float y = actor.getY();
        super.layout();
        actor.setPosition(x,y);
    }
}