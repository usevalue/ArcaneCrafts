package org.inclusiverec.arcanecrafts.items;

import main.java.org.inclusiverec.arcanecrafts.ArcaneCrafts;
import org.bukkit.configuration.ConfigurationSection;

public class ArcaneItemFactory {

    public ArcaneItemFactory() {

    }

    public ArcaneItem getItemFromConfig(String configName) {
        ConfigurationSection section = ArcaneCrafts.plugin.getConfig().getConfigurationSection("Items."+configName);
        if(section==null) return null;
        String type = section.getString("Type");
        ArcaneItem item;
        return null;
    }

    private ArcaneWeapon getArcaneWeapon(ConfigurationSection section) {
        return null;
    }

}
