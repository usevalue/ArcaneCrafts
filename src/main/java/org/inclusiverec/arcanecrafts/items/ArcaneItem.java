package org.inclusiverec.arcanecrafts.items;

import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ArcaneItem {

    public final String configName;
    private ItemStack item;

    public ArcaneItem(String configName) {
        this.configName=configName;
    }

    public ItemStack getItemStack() {
        return item;
    }

    public void setItemStack(ItemStack item) {
        this.item=item;
    }

}
