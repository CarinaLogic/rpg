package me.carina.rpg.client.ui;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.*;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.utils.Align;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

import java.util.function.Supplier;

@Selectable
public class UILabelButton extends TextButton implements CursorHandler{
    public UILabelButton(){
        super("", getPrefStyle());
        getLabel().setAlignment(Align.left);
        getLabelCell().left().expandX();
    }

    @Override
    protected Label newLabel(String text, Label.LabelStyle style) {
        UILabel label = new UILabel();
        label.setStyle(style);
        label.setText(text);
        return label;
    }
    public UILabelButton supplyString(Supplier<String> supplier){
        ((UILabel)getLabel()).supplyString(supplier);
        return this;
    }

    public UILabelButton supplyColor(Supplier<Color> supplier){
        ((UILabel)getLabel()).supplyColor(supplier);
        return this;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    public boolean enter(){
        toggle();
        return true;
    }
    public boolean exit(){
        return false;
    }

    public UILabelButton color(Color color){
        setColor(color);
        return this;
    }

    public static TextButtonStyle getPrefStyle(){
        TextButtonStyle style = new TextButtonStyle();
        style.over = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"hovered"), Drawable.class);
        style.checked = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"selected"), Drawable.class);
        style.font = Game.getClient().getAssets().get(new Path("core",AssetGroup.ui,"font"), BitmapFont.class);
        return style;
    }

    @Override
    public boolean isOver() {
        boolean s = super.isOver();
        if (s) return true;
        Group parent = getParent();
        if (parent == null) return false;
        if (parent instanceof CursorPositionHolder) {
            CursorPositionHolder holder = (CursorPositionHolder) parent;
            return holder.getSelected() == this;
        }
        return false;
    }
}
