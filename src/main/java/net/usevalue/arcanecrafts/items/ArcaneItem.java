package net.usevalue.arcanecrafts.items;

import org.bukkit.inventory.ItemStack;
import net.usevalue.arcanecrafts.effects.ArcaneEffect;

import java.util.HashSet;

public class ArcaneItem {

    public final String configName;
    private ItemStack item;
    private HashSet<ArcaneEffect> effects;

    public ArcaneItem(String configName) {
        this.configName=configName;
        effects = new HashSet<>();
    }

    public ItemStack getItemStack() {
        return item;
    }

    public void setItemStack(ItemStack item) {
        this.item=item;
    }

    public void addEffect(ArcaneEffect effect) {
        if(effect!=null&&!effects.contains(effect)) {
            effects.add(effect);
        }
    }

    public HashSet<ArcaneEffect> getEffects() {
        return effects;
    }


}
