package org.inclusiverec.arcanecrafts;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;
import org.inclusiverec.arcanecrafts.items.ArcaneItem;
import org.inclusiverec.arcanecrafts.items.ArcaneWeapon;

import java.util.HashMap;
import java.util.logging.Level;

public class ArcaneCrafts extends JavaPlugin {

    public static ArcaneCrafts plugin;
    public static HashMap<String,ArcaneItem> armoury;
    private boolean debug = true;

    @Override
    public void onEnable() {
        plugin=this;
        load();
    }

    public void load() {
        saveDefaultConfig();

        // Load items
        armoury = new HashMap<String, ArcaneItem>();
        ConfigurationSection items;
        if(getConfig().contains("Items")) items = getConfig().getConfigurationSection("Items");
        else items = getConfig().createSection("Items");
        for(String configName:items.getKeys(false)) {
            if(debug) getLogger().log(Level.WARNING,"Loading item "+configName+"...");
            ArcaneItem getIt = loadItemFromConfig(configName);
            if(getIt!=null) armoury.put(configName,getIt);
        }

        // Load dependencies

        // Register commands
        getCommand("arcanecrafts").setExecutor(new ArcaneCommands());

        // Event listeners?  Or leave it to individual items?

    }

    public ArcaneItem loadItemFromConfig(String configName) {
        ItemStack item = new ItemStack(Material.valueOf(getConfig().getConfigurationSection("Items."+configName).getString("Material")));
        ArcaneWeapon arcItem = new ArcaneWeapon(configName,item);
        return arcItem;
    }

}
