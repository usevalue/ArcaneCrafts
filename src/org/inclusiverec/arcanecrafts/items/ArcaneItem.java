package org.inclusiverec.arcanecrafts.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ArcaneItem {

    private ConfigurationSection section;
    private String configName;
    private String displayName;
    private ItemStack item;

    public ArcaneItem(String configName) {
        this.configName=configName;
    }

    public String getConfigName() {
        return configName;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public boolean setItemStack(ItemStack itemStack) {
        if(itemStack==null) return false;
        item=itemStack;
        return true;
    }

}
