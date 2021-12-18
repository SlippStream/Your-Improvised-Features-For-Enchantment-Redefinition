package me.slippstream.yiffer.enchantments;

import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


/** Name: Vampirism
 * Max Level: 2
 * Description: steal life
 */
public class Vampirism extends YifferEnchantmentWrapper {

    public Vampirism(Plugin plugin) {
        super(plugin, "Vampirism", 1, 2);

    }

    @EventHandler
    public void onHit(EntityDamageByEntityEvent e) {
        Entity rawAttacker = e.getDamager();
        double damageDealt = e.getFinalDamage();
        if(rawAttacker instanceof Player) {
            Player player = (Player)rawAttacker;
            if(player.getItemInUse().getEnchantments().get(this) != null) {
                double health = player.getHealth() + damageDealt * 0.25 * player.getItemInUse().getEnchantments().get(this);
                player.setHealth(health);
            }
        }
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.BREAKABLE;
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
        switch(item.getType()) {
            case WOODEN_AXE:
            case STONE_AXE:
            case IRON_AXE:
            case DIAMOND_AXE:
            case NETHERITE_AXE:
            case WOODEN_SWORD:
            case STONE_SWORD:
            case IRON_SWORD:
            case DIAMOND_SWORD:
            case NETHERITE_SWORD:
            case BOOK:
                return true;
            default:
                return false;
        }
    }
}
