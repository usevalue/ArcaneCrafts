package org.inclusiverec.arcanecrafts;

import org.bukkit.plugin.java.JavaPlugin;
import org.inclusiverec.arcanecrafts.items.ArcaneItem;
import org.inclusiverec.arcanecrafts.items.ArcaneItemFactory;

import java.util.HashMap;

public class ArcaneCrafts extends JavaPlugin {

    public static ArcaneCrafts plugin;
    private HashMap<String,ArcaneItem> arcaneItems;
    private ArcaneItemFactory factory;

    @Override
    public void onEnable() {
        plugin=this;
        factory = new ArcaneItemFactory();
        load();
    }

    public void load() {

        // Load items
        arcaneItems = new HashMap<String,ArcaneItem>();
        for(String itemName:getConfig().getConfigurationSection("Items").getKeys(false)) {
            ArcaneItem item = factory.getItemFromConfig(itemName);
            if(item!=null)
                arcaneItems.put(itemName, item);
        }

        // Load dependencies

    }
}
