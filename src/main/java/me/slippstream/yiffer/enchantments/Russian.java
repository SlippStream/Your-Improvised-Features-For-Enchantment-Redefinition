package me.slippstream.yiffer.enchantments;

import org.bukkit.Effect;
import org.bukkit.EntityEffect;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.enchantments.EnchantmentTarget;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerBedEnterEvent;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerBedEnterEvent.BedEnterResult;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;


/** Name: russian
 * Max Level: 0
 * Description: haha funny
 */
public class Russian extends YifferEnchantmentWrapper {

    public Russian(Plugin plugin) {
        super(plugin, "reй ceкc", 0, 0);

    }

    @EventHandler
    public void onSleep(PlayerBedEnterEvent e) {
        if(e.getBedEnterResult() == BedEnterResult.OK) {
            int random = (int)Math.floor(Math.random()*(100+1));
            if(0 <= random && random <= 12 ) {
                Player player = e.getPlayer();
                player.playEffect(EntityEffect.FIREWORK_EXPLODE);
                player.playEffect(e.getBed().getLocation(), Effect.ENDERDRAGON_GROWL, null);
            }
        }
    }

    @EventHandler
    public void ondrop(PlayerDropItemEvent e) {
        int random = (int)Math.floor(Math.random()*(100+1));
        if(0 <= random && random <= 2 ) {
            Player player = e.getPlayer();
            player.playEffect(EntityEffect.LOVE_HEARTS);
            player.damage(random);
        }
    }

    @Override
    public EnchantmentTarget getItemTarget() {
        return EnchantmentTarget.ARMOR;
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
        switch(item.getType().getEquipmentSlot()) {
            case HEAD:
            case CHEST:
            case LEGS:
            case FEET:
                return true;
            default:
                return false;
        }
    }
}
