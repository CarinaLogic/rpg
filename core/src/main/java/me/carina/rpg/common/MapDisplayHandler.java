package me.carina.rpg.common;

import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.utils.ObjectMap;
import com.badlogic.gdx.utils.SnapshotArray;
import me.carina.rpg.Game;

import java.util.Arrays;
import java.util.function.Consumer;

public abstract class MapDisplayHandler<K extends Feature,V> {
    Group group;
    Consumer<ObjectMap.Entry<K,V>> addFunc;
    Consumer<Actor> removeFunc;
    public MapDisplayHandler(Group group,  Consumer<ObjectMap.Entry<K,V>> addFunc){
        this(group,addFunc, Actor::remove);
    }
    public MapDisplayHandler(Group group,  Consumer<ObjectMap.Entry<K,V>> addFunc, Consumer<Actor> removeFunc){
        this.group = group;
        this.addFunc = addFunc;
        this.removeFunc = removeFunc;
    }
    public abstract Iterable<ObjectMap.Entry<K,V>> getIterable();
    public void tick() {
        SnapshotArray<Actor> children = group.getChildren();
        boolean[] checkList = new boolean[children.size];
        Arrays.fill(checkList, false);
        for (ObjectMap.Entry<K,V> entry : getIterable()) {
            Feature feature = entry.key;
            boolean success = false;
            for (Actor display : Game.getClient().getDisplays().getAll(feature)) {
                int i = children.indexOf(display,true);
                if (i != -1){
                    checkList[i] = true;
                    success = true;
                    break;
                }
            }
            if (!success){
                //there's no children corresponds to the arrayFeature array, add it to displays
                addFunc.accept(entry);
            }
        }
        for (int i = 0; i < checkList.length; i++) {
            if (!checkList[i]){
                //there's no array entry for this child, remove it
                removeFunc.accept(children.get(i));
            }
        }
    }
}
