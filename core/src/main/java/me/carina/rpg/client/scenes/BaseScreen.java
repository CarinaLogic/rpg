package me.carina.rpg.client.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.*;
import me.carina.rpg.Game;
import me.carina.rpg.client.Client;

import java.util.function.BiConsumer;

public abstract class BaseScreen implements Screen {
    InputMultiplexer multiplexer = new InputMultiplexer();
    Queue<BiConsumer<BaseScreen,Client>> delayedInitFunctions = new Queue<>();

    public BaseScreen(){
        Gdx.input.setInputProcessor(multiplexer);
    }

    public abstract void init();
    @Override
    public void show(){
        init();
        delayedInitFunctions.forEach(func -> func.accept(this, (Client) Game.getInstance()));
        delayedInitFunctions.clear();
        if (Game.getInstance().getLogger().getLevel() == Logger.DEBUG && !(this instanceof LoadingScreen)){
            addStage(new DebugStage());
        }
    }

    public <T extends BaseScreen> void addStage(GameStage<T> stage){
        //noinspection unchecked
        stage.setScreen((T) this);
        stage.init();
        multiplexer.addProcessor(0,stage);
    }
    public CanvasStage getCanvas() {
        return null;
    }

    @Override
    public void render(float delta) {
        SnapshotArray<InputProcessor> processors = multiplexer.getProcessors();
        processors.reverse();
        for (InputProcessor processor : processors) {
            if (processor instanceof Stage){
                Stage stage = (Stage) processor;
                stage.act();
            }
        }
        ScreenUtils.clear(Color.BLACK);
        for (InputProcessor processor : processors) {
            if (processor instanceof Stage) {
                Stage stage = (Stage) processor;
                stage.getViewport().apply();
                stage.draw();
            }
        }
        processors.reverse();
    }

    @Override
    public void resize(int width, int height) {
        for (InputProcessor processor : multiplexer.getProcessors()) {
            if (processor instanceof Stage){
                Stage stage = (Stage) processor;
                stage.getViewport().update(width,height,true);
            }
        }
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public abstract boolean canChangeScreen();
}
