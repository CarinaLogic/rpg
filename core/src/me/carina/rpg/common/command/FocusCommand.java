package me.carina.rpg.common.command;

public class FocusCommand implements Command{
    @Override
    public String getPrefix() {
        return "focus";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return false;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        return false;
    }
}
