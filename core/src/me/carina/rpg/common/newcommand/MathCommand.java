package me.carina.rpg.common.newcommand;

import com.badlogic.gdx.math.MathUtils;

import java.util.Objects;

public class MathCommand extends Command{
    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction(altNames = {"$_+"})
    public Double add(Double a, Double b){
        return a + b;
    }
    @CommandFunction(altNames = {"$_-"})
    public Double sub(Double a, Double b){
        return a - b;
    }
    @CommandFunction(altNames = {"$_*"})
    public Double mul(Double a, Double b){
        return a * b;
    }
    @CommandFunction(altNames = {"$_/"})
    public Double div(Double a, Double b){
        if (b == 0) throw new CommandException(CommandException.ExceptionType.zero_division);
        return a / b;
    }
    @CommandFunction(altNames = {"$_="})
    public boolean eq(Double a, Double b){
        return Objects.equals(a, b);
    }
    @CommandFunction(altNames = {"$_!="})
    public boolean ne(Double a, Double b){
        return !Objects.equals(a,b);
    }
    @CommandFunction(altNames = {"$_<"})
    public boolean lt(Double a, Double b){
        return a < b;
    }
    @CommandFunction(altNames = {"$_<="})
    public boolean le(Double a, Double b){
        return a <= b;
    }
    @CommandFunction(altNames = {"$_>"})
    public boolean gt(Double a, Double b){
        return a > b;
    }
    @CommandFunction(altNames = {"$_>="})
    public boolean ge(Double a, Double b){
        return a >= b;
    }
    @CommandFunction(altNames = {"$_%"})
    public Double mod(Double a, Double b){
        return a % b;
    }
}
