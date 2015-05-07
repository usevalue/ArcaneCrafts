package org.inclusiverec.arcanecrafts.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ArcaneItem {

    private ConfigurationSection section;
    private final String configName;
    private final ItemStack item;

    public ArcaneItem(String configName, ItemStack item) {
        this.configName=configName;
        this.item=item;
    }

    public String getConfigName() {
        return configName;
    }

    public ItemStack getItemStack() {
        return item;
    }

}
