package me.slippstream.yiffer;

import me.slippstream.yiffer.commands.TestExecutor;
import me.slippstream.yiffer.enchantments.YifferEnchantments;
import org.bukkit.command.PluginCommand;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

import java.lang.reflect.Field;
import java.util.HashMap;

public final class YifferMain extends JavaPlugin implements Listener {

    private static YifferMain plugin;

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
    }

    @Override
    public void onEnable() {
        // Plugin startup logic
        plugin = this;

        TestExecutor test = new TestExecutor();
        getCommand("test").setExecutor(test);

        YifferEnchantments.registerEnchantments();
        this.getServer().getPluginManager().registerEvents(YifferEnchantments.SWIFTNESS, this);
        this.getServer().getPluginManager().registerEvents(this, this);

        getServer().broadcastMessage("Loaded YIFFER!");
    }

    @SuppressWarnings("unchecked")
    @Override
    public void onDisable() {
        // Plugin shutdown logic
        for (Enchantment ench : YifferEnchantments.getAllEnchantments()) {
            try {
                Field byKeyField = Enchantment.class.getDeclaredField("byKey");
                Field byNameField = Enchantment.class.getDeclaredField("byName");

                byKeyField.setAccessible(true);
                byNameField.setAccessible(true);

                HashMap<Integer, Enchantment> byKey = (HashMap<Integer, Enchantment>) byKeyField.get(null);
                HashMap<Integer, Enchantment> byName = (HashMap<Integer, Enchantment>) byNameField.get(null);

                byKey.remove(ench.getKey());
                byName.remove(ench.getName());

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static YifferMain getPlugin() {
        return plugin;
    }
}
