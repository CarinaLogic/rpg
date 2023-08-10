package me.carina.rpg.common.newcommand;

import com.badlogic.gdx.utils.Null;

public class BranchCommand extends Command{

    @Override
    public boolean enabled() {
        return true;
    }
    @CommandFunction
    public void if_$(boolean condition, CommandLabel label){
        if (!condition){
            getScript().jumpToLabel(label);
        }
    }
    @CommandFunction
    public void while_$(boolean condition, CommandLabel label){
        if (condition) getScript().queueJump(getScript().getLabelIndex(label)-1, getScript().cursor);
        else getScript().jumpToLabel(label);
    }
    //Assuming all functions does not set delay, otherwise timing issue occurs
    @CommandFunction
    public void for_$(InlineCommand init, InlineCommand term, InlineCommand increment, CommandLabel label){
        if (getScript().isJumped()){
            increment.parse();
        }
        else {
            init.parse();
        }
        if (!term.parse(Boolean.class)){
            getScript().jumpToLabel(label);
        }
        else {
            getScript().queueJump(getScript().getLabelIndex(label)-1, getScript().cursor);
        }
    }
}
