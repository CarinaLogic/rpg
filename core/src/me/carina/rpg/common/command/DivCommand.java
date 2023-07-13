package me.carina.rpg.common.command;

public class DivCommand implements Command{
    @Override
    public String getPrefix() {
        return "div";
    }

    @Override
    public boolean init(CommandParser parser, String... args) {
        return true;
    }

    @Override
    public boolean run(CommandParser parser, String... args) {
        if (args.length < 2) throw new IllegalArgumentException();
        float s = (float) parser.getValue(args[1]);
        for (int i = 2; i < args.length; i++) {
            if (s == 0) throw new IllegalArgumentException();
            s /= (float) parser.getValue(args[i]);
        }
        parser.setValue(args[0], s);
        return true;
    }
}