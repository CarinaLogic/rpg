package me.carina.rpg.client.battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.scenes.scene2d.ui.Cell;
import com.badlogic.gdx.scenes.scene2d.ui.Container;
import com.badlogic.gdx.scenes.scene2d.ui.Stack;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import me.carina.rpg.Game;
import me.carina.rpg.client.misc.UIColor;
import me.carina.rpg.client.ui.UILabel;
import me.carina.rpg.client.ui.UIProgressBar;
import me.carina.rpg.client.ui.UITableView;
import me.carina.rpg.common.Display;
import me.carina.rpg.common.unit.UIUnitDisplay;
import me.carina.rpg.common.unit.UIUnitPartDisplay;
import me.carina.rpg.common.unit.Unit;
import me.carina.rpg.common.unit.stat.HPMaxStat;
import me.carina.rpg.common.unit.stat.HPStat;
import me.carina.rpg.common.unit.stat.SPMaxStat;
import me.carina.rpg.common.unit.stat.SPStat;

public class UIBattleStatPanel extends Stack implements Display<Unit> {
    UITableView tableView;
    Unit unit;
    UILabel nameLabel;
    UIUnitDisplay unitDisplay;
    UIHpBar hpBar;
    UISpBar spBar;
    boolean selected = false;
    public UIBattleStatPanel(Unit unit){
        this.unit = unit;
        {
            tableView = new UITableView();
            add(tableView);
            {
                nameLabel = new UILabel();
                nameLabel.addAction(Actions.forever(Actions.run(() -> nameLabel.text(
                        unit.getName()
                ))));
                tableView.add(nameLabel).left().colspan(2);
            }
            tableView.row();
            {
                tableView.add(new UILabel().text("HP")).padRight(1);
                hpBar = new UIHpBar();
                hpBar.addAction(Actions.forever(Actions.run(() -> hpBar.value(
                        unit.getStats().getStat(HPStat.class).get() * 100 /
                                unit.getStats().getStat(HPMaxStat.class).get()))));
                tableView.add(hpBar).expandX().fillX();
            }
            tableView.row();
            {
                tableView.add(new UILabel().text("SP")).padRight(1);
                spBar = new UISpBar();
                spBar.addAction(Actions.forever(Actions.run(() -> spBar.value(
                        unit.getStats().getStat(SPStat.class).get() * 100 /
                                unit.getStats().getStat(SPMaxStat.class).get()))));
                tableView.add(spBar).expandX().fillX();
            }
        }
        {
            Table unitContainer = new Table();
            add(unitContainer);
            Table unitTable = new Table();
            unitTable.setClip(true);
            unitContainer.add(unitTable).expand().top().right().size(32, 22);
            unitDisplay = unit.newDisplay(UIUnitDisplay.class);
            unitTable.add(unitDisplay).top().right();
        }
    }

    @Override
    public Unit getFeature() {
        return unit;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        Game.getInstance().getContext().add(getFeature());
        if (Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit() == unit && !selected){
            setSize(96,72);
            selected = true;
        }
        else if (Game.getClient().getContext().get(BattleMapGUIStage.class).getSelectedUnit() != unit && selected){
            setSize(72,48);
            selected = false;
        }
        super.draw(batch, parentAlpha);
    }

    public static class UIHpBar extends UIProgressBar{
        public UIHpBar(){
            super();
            color(UIColor.hp);
        }
    }

    public static class UISpBar extends UIProgressBar{
        public UISpBar(){
            super();
            color(UIColor.sp);
        }
    }
}
