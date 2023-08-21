package me.carina.rpg.common.unit;

import me.carina.rpg.common.ArrayDisplay;
import me.carina.rpg.common.ArrayFeature;

public class UnitsDisplay extends ArrayDisplay<Unit> {
    Units units;
    public UnitsDisplay(Units units) {
        this.units = units;
    }

    @Override
    public ArrayFeature<Unit> getFeature() {
        return units;
    }

    @Override
    public boolean fillChildren() {
        return false;
    }
}
