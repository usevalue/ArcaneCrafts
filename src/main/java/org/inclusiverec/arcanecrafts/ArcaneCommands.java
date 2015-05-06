package org.inclusiverec.arcanecrafts;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.inclusiverec.arcanecrafts.items.ArcaneItem;

import java.util.HashMap;

public class ArcaneCommands implements CommandExecutor {

    @SuppressWarnings("deprecation")
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (command.getName().equalsIgnoreCase("arcanecrafts")) {

            if (args.length==0) return false;

            if (args[0].equalsIgnoreCase("help")) {
                sender.sendMessage(getHelp(args));
                return true;
            }

            if (args[0].equalsIgnoreCase("get")) {
                if(args.length==1||args.length>3) {
                    sender.sendMessage(getHelp("get"));
                    return true;
                }

                Player player;
                ArcaneItem item;
                if(args.length==3) {
                    player = ArcaneCrafts.plugin.getServer().getPlayer(args[2]);
                    if(player==null||!(player instanceof Player)) {
                        sender.sendMessage(args[2]+" is not a valid player name!");
                        return true;
                    }
                    if(!player.isOnline()) {
                        sender.sendMessage(args[2]+" is offline.");
                        return true;
                    }
                }
                else {
                    if (sender instanceof Player) player = (Player) sender;
                    else {
                        sender.sendMessage("When getting an item from the console, you must specify a player recipient:");
                        sender.sendMessage("arcanecrafts get <item> <player");
                        return true;
                    }
                }

                item = getItem(args[1]);
                player.getInventory().addItem(item.getItemStack());
                return true;
            }

        }
        return false;
    }

    public String[] getHelp(String topic) {
        String[] request = new String[2];
        request[0] = "help";
        request[1] = topic;
        return getHelp(request);
    }

    public String[] getHelp(String[] args) {
        String[] help;
        if(args.length<2) {
            help = new String[2];
            help[0] = "Valid ArcaneCrafts commands are:";
            help[1] = "/arcanecrafts get <item> [player]";
            return help;
        }
        if(args[1].equalsIgnoreCase("get")) {
            help = new String[5];
            help[0] = "The get command spawns an arcane item into a player's inventory.";
            help[1] = "Usage: /arcanecrafts get <item> [player]";
            help[2] = "<item> must be a valid CONFIGURATION name.  Type /arcanecrafts list for a list of configured items.";
            help[3] = "You can either enter another [player] to receive the item, or else you will receive it yourself.";
            help[4] = "If the target player lacks inventory space, the command will noiselessly fail."; // I assume?
        }
        return null;
    }

    public ArcaneItem getItem(String configName) {
        //TODO
        return null;
    }

}
