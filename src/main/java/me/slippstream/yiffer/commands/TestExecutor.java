package me.slippstream.yiffer.commands;

import me.slippstream.yiffer.enchantments.YifferEnchantments;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;

public class TestExecutor implements CommandExecutor {

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) return true;
        Player player = (Player) sender;

        if (command.getName().equalsIgnoreCase("test")) {
            ItemStack item = new ItemStack(Material.DIAMOND_BOOTS);
            ItemMeta meta = item.getItemMeta();
            ArrayList<String> lore = new ArrayList<>();
            lore.add(ChatColor.GRAY + YifferEnchantments.SWIFTNESS.getName() + " III");
            meta.setLore(lore);
            item.setItemMeta(meta);
            item.addUnsafeEnchantment(YifferEnchantments.SWIFTNESS, 3);

            player.getInventory().addItem(item);
        }

        return true;
    }
}
