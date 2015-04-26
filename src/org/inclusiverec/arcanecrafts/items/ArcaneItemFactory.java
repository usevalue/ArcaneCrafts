package org.inclusiverec.arcanecrafts.items;

import org.bukkit.configuration.ConfigurationSection;
import org.inclusiverec.arcanecrafts.ArcaneCrafts;

public class ArcaneItemFactory {

    public ArcaneItemFactory() {

    }

    public ArcaneItem getItemFromConfig(String configName) {
        ConfigurationSection section = ArcaneCrafts.plugin.getConfig().getConfigurationSection("Items."+configName);
        if(section==null) return null;
        String type = section.getString("Type");
        ArcaneItem item;
        switch(type) {
            case "weapon":
                item=getArcaneWeapon(section);
                break;
            default:
                break;
        }
        return null;
    }

    private ArcaneWeapon getArcaneWeapon(ConfigurationSection section) {
        return null;
    }

}
