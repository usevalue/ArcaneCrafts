package org.inclusiverec.arcanecrafts;

import org.bukkit.plugin.java.JavaPlugin;
import org.inclusiverec.arcanecrafts.items.ArcaneItem;

import java.util.HashMap;

public class ArcaneCrafts extends JavaPlugin {

    public static ArcaneCrafts plugin;
    private HashMap<String,ArcaneItem> arcaneItems;

    @Override
    public void onEnable() {
        plugin=this;
        load();
    }

    public void load() {
        arcaneItems = new HashMap<String,ArcaneItem>();

        // Load items from config

        // Load dependencies

    }
}
