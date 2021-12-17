package me.slippstream.yiffer.enchantments;

import org.bukkit.NamespacedKey;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;

public abstract class YifferEnchantmentWrapper extends Enchantment implements Listener {
    private final String name;
    private final int maxLevel;
    private final int startLevel;
    private int currentLevel;
    private static Listener listener;

    public YifferEnchantmentWrapper(Plugin plugin, String name, int startLevel, int maxLevel) {
        super(new NamespacedKey(plugin, name));
        this.name = name;
        this.maxLevel = maxLevel;
        this.startLevel = startLevel;
        listener = this;
    }

    @Override
    public int getMaxLevel() {
        return maxLevel;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getStartLevel() {
        return startLevel;
    }

    public static Listener getListener() {
        return listener;
    };

}
