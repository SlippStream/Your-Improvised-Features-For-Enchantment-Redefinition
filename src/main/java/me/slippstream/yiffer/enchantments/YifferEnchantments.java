package me.slippstream.yiffer.enchantments;

import me.slippstream.yiffer.YifferMain;
import org.bukkit.enchantments.Enchantment;

import java.lang.reflect.Field;
import java.util.ArrayList;

public class YifferEnchantments {
    private static final ArrayList<YifferEnchantmentWrapper> all_enchantments = new ArrayList<>();
    public static final YifferEnchantmentWrapper SWIFTNESS = new Swiftness(YifferMain.getPlugin());
    public static final YifferEnchantmentWrapper VAMPIRISM = new Vampirism(YifferMain.getPlugin());

    //Only to be called once at the initialization of the plugin
    public static void registerEnchantments() {
        all_enchantments.add(SWIFTNESS);
        all_enchantments.add(VAMPIRISM);

        for (Enchantment e : all_enchantments) {
            registerEnchant(e);
        }
    }

    private static void registerEnchant(Enchantment ench) {
        boolean registered = true;
        try {
            Field f = Enchantment.class.getDeclaredField("acceptingNew");
            f.setAccessible(true);
            f.set(null, true);
            Enchantment.registerEnchantment(ench);
        } catch (Exception e) {
            registered = false;
            e.printStackTrace();
        }

        if (registered) {
            System.out.println("Properly Registered enchantment " + ench.getKey());
        } else {
            System.err.println("Failed to register enchantment " + ench.getKey());
        }
    }

    public static ArrayList<YifferEnchantmentWrapper> getAllEnchantments() {
        return all_enchantments;
    }

}
