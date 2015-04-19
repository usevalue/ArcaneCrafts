package org.inclusiverec.arcanecrafts.items;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.inventory.ItemStack;

public abstract class ArcaneItem {

    private ConfigurationSection section;
    private String configName;
    private String displayName;
    private ItemStack item;

    public ArcaneItem(ConfigurationSection section) {
        this.section=section;
        displayName=section.getString("display-name",configName);
        String mat = section.getString("type",null);
        Material material;
        try {
            material = Material.valueOf(mat);
            item = new ItemStack(material);
        }
        catch (NullPointerException e) {
            throw e;
        }
    }
    public String getConfigName() {
        return configName;
    }

    public ItemStack getItemStack() {
        return item;
    }

}
