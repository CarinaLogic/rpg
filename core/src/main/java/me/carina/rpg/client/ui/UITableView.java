package me.carina.rpg.client.ui;

import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.utils.NinePatchDrawable;
import me.carina.rpg.Game;
import me.carina.rpg.common.file.AssetGroup;
import me.carina.rpg.common.file.Path;

public class UITableView extends Table{
    public UITableView() {
        pad(6);
        setBackground(Game.getClient().getAssets().get(new Path("core", AssetGroup.ui, "frame"), NinePatchDrawable.class));
    }
}
