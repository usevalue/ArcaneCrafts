package org.inclusiverec.arcanecrafts;

import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;
import org.inclusiverec.arcanecrafts.items.ArcaneItem;
import org.inclusiverec.arcanecrafts.items.ArcaneWeapon;

import java.util.HashMap;
import java.util.List;
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
        //Find in config
        ConfigurationSection config = getConfig().getConfigurationSection("Items."+configName);
        if(config==null) return null;
        // Create item
        ArcaneItem arcItem;
        String type = config.getString("Type").toLowerCase();
        switch(type) {
            case "weapon":
                arcItem = new ArcaneWeapon(configName);
                break;
            default:
                return null;
        }
        // Generate itemstack
        ItemStack itemStack = new ItemStack(Material.valueOf(config.getString("Material")));
        if(itemStack==null||itemStack.getType()==null) {
            getLogger().log(Level.WARNING,"[ArcaneCrafts] Invalid material for "+configName+".");
            return null;
        }
        // Displayname
        itemStack.getItemMeta().setDisplayName(config.getString("Name",itemStack.getType().name()));
        // Enchantments
        List<String> enchants = config.getStringList("Enchants");
        for(String enchantment:enchants) {
            String[] ench = enchantment.split(" ");
            String eName = ench[0];
            int eLevel;
            if(ench.length>1) {
                eLevel = Integer.getInteger(ench[1]);
            }
            else eLevel=1;
            Enchantment enchanty = Enchantment.getByName(eName);
            if(enchanty!=null) itemStack.addUnsafeEnchantment(enchanty, eLevel);
        }
        List<String> lore = config.getStringList("Lore");
        if(lore!=null) itemStack.getItemMeta().setLore(lore);

        // Set recipe
        List<String> recipeShape = config.getStringList("Recipe");
        List<String> recipeCode = config.getStringList("Recipe-code");
        if(!recipeShape.isEmpty()&&!recipeCode.isEmpty()) {
            Recipe recipe;
            if(config.getBoolean("Recipe-shaped",true)) {
                recipe = new ShapedRecipe(itemStack);
            }
            else recipe = new ShapelessRecipe(itemStack);

        }

        arcItem.setItemStack(itemStack);
        return arcItem;
    }

}
