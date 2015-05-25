package net.usevalue.arcanecrafts;

import net.usevalue.arcanecrafts.items.ArcaneItem;
import org.bukkit.Material;
import org.bukkit.configuration.ConfigurationSection;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.Recipe;
import org.bukkit.inventory.ShapedRecipe;
import org.bukkit.inventory.ShapelessRecipe;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;

public class ArcaneCrafts extends JavaPlugin {

    public static ArcaneCrafts plugin;
    public static HashMap<String,ArcaneItem> armoury;
    private boolean debug;

    @Override
    public void onEnable() {
        plugin=this;
        saveDefaultConfig();
        debug = getConfig().getBoolean("debug",true);
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
            ArcaneItem getIt = getItemFromConfig(configName);
            if(getIt!=null) armoury.put(configName,getIt);
        }

        // Load dependencies

        // Register commands and events
        getCommand("arcanecrafts").setExecutor(new ArcaneCommands());
        getServer().getPluginManager().registerEvents(new ArcaneListener(this),this);

    }

    public ArcaneItem getItemByConfigName(String configName) {
        return armoury.get(configName);
    }

    public ArcaneItem getItemFromConfig(String configName) {
        //Find in config
        ConfigurationSection config = getConfig().getConfigurationSection("Items."+configName);
        if(config==null) return null;
        ArcaneItem arcItem = new ArcaneItem(configName);

        // Generate itemstack
        ItemStack itemStack;
        try {itemStack = new ItemStack(Material.valueOf(config.getString("Material")));}
        catch (IllegalArgumentException e) {
            getLogger().log(Level.WARNING,"[ArcaneCrafts] Invalid material for "+configName+".");
            return null;
        }

        // Get data from config
        itemStack.getItemMeta().setDisplayName(config.getString("Name",itemStack.getType().name()));
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
