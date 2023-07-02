package me.carina.rpg.common.command;

import me.carina.rpg.Game;

public class SayCommand implements Command{
    @Override
    public String getPrefix() {
        return "say";
    }

    @Override
    public void run(CommandParser parser,String... args) {
        StringBuilder msg = new StringBuilder();
        for (String arg : args) {
            msg.append(arg);
            msg.append(" ");
        }
        Game.getInstance().getLogger().info(msg.toString().trim());
    }
}
