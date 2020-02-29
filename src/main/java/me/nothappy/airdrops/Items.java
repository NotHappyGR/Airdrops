package me.nothappy.airdrops;

import me.nothappy.airdrops.utils.ItemMaker;
import me.nothappy.airdrops.utils.RandomInt;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemStack;

import java.util.ArrayList;
import java.util.List;

public class Items {

    public static List<ItemStack> items = new ArrayList<ItemStack>();

    static {
        items.add(new ItemMaker(Material.DIAMOND_SWORD).addEnchantment(Enchantment.DAMAGE_ALL, 2).build());
        items.add(new ItemMaker(Material.GOLDEN_APPLE).setAmount(RandomInt.getRandomInt(2, 8)).build());
        items.add(new ItemMaker(Material.DIAMOND).setAmount(RandomInt.getRandomInt(2, 12)).build());
        items.add(new ItemMaker(Material.GOLD_INGOT).setAmount(RandomInt.getRandomInt(2, 16)).build());
        items.add(new ItemMaker(Material.IRON_HELMET).addEnchantment(Enchantment.DURABILITY, 2).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        items.add(new ItemMaker(Material.DIAMOND_CHESTPLATE).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 1).build());
        items.add(new ItemMaker(Material.IRON_LEGGINGS).addEnchantment(Enchantment.PROTECTION_ENVIRONMENTAL, 2).addEnchantment(Enchantment.DURABILITY, 1).build());
        items.add(new ItemMaker(Material.DIAMOND_BOOTS).addEnchantment(Enchantment.DURABILITY, 3).build());
    }
}
