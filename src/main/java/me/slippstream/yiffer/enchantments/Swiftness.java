package me.slippstream.yiffer.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerVelocityEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;


/** Name: Swiftness
 * Max Level: 4
 * Description: go fast
 */
public class Swiftness extends YifferEnchantmentWrapper {

    public Swiftness(Plugin plugin) {
        super(plugin, "Swiftness", 1, 4);

    }

    @EventHandler
    public void onPlayerVelocityChange(PlayerMoveEvent e) {
        if (e.getFrom().getBlockX() != e.getTo().getBlockX() || e.getFrom().getBlockZ() != e.getTo().getBlockZ()) {
            Player player = e.getPlayer();

            if (player.getEquipment().getArmorContents()[3] == null) {
                return;
            }
            if (player.getEquipment().getBoots().getEnchantments().get(this) != null && !player.isInWater() && !player.isFlying()) {
                if (!player.hasPotionEffect(PotionEffectType.SPEED) || player.getPotionEffect(PotionEffectType.SPEED).getDuration() < 40) {
                    player.addPotionEffect(new PotionEffect(PotionEffectType.SPEED, 60, player.getEquipment().getBoots().getEnchantments().get(this) - 1));
                }
            }
        }
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR_FEET;
    }

    @Override
    public boolean isTreasure() {
        return false;
    }

    @Override
    public boolean isCursed() {
        return false;
    }

    @Override
    public boolean conflictsWith(Enchantment other) {
        return false;
    }

    @Override
    public boolean canEnchantItem(ItemStack item) {
        return item.getType().getEquipmentSlot() == EquipmentSlot.FEET;
    }
}
