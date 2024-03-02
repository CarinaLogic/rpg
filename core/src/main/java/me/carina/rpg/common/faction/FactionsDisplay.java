package me.carina.rpg.common.faction;

import com.badlogic.gdx.scenes.scene2d.Group;
import me.carina.rpg.Game;
import me.carina.rpg.common.ArrayDisplayHandler;
import me.carina.rpg.common.Display;

import java.util.function.Supplier;

public class FactionsDisplay extends Group implements Display<Factions> {
    Supplier<Factions> factionsSupplier;
    ArrayDisplayHandler<Faction> handler = new ArrayDisplayHandler<>(
            this, f -> addActor(Game.getClient().getDisplays().get(()->f,FactionDisplay.class))
    ) {
        @Override
        public Iterable<Faction> getIterable() {
            return factionsSupplier.get();
        }
    };

    public FactionsDisplay(){
    }

    @Override
    public void act(float delta) {
        Game.getInstance().getContext().add(getFeature());
        handler.tick();
        super.act(delta);
    }


    @Override
    public Supplier<Factions> getFeatureSupplier() {
        return factionsSupplier;
    }

    @Override
    public void setFeatureSupplier(Supplier<Factions> supplier) {
        this.factionsSupplier = supplier;
    }
}
