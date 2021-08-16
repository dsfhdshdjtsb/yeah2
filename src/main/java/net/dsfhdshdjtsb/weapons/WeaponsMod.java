package net.dsfhdshdjtsb.weapons;

import net.dsfhdshdjtsb.weapons.items.BroadSword;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class WeaponsMod implements ModInitializer {

	public static final String MOD_ID = "weapons";
	public static final ItemGroup ITEM_GROUP = FabricItemGroupBuilder.build(
			new Identifier(MOD_ID, "general"),
			() -> new ItemStack(Items.BELL)
	);

	public static final ToolItem BroadSword = new BroadSword(ToolMaterials.NETHERITE, 8, -2.4f, new Item.Settings().group(ITEM_GROUP));
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		Registry.register(Registry.ITEM, new Identifier(MOD_ID, "broad_sword"), BroadSword);
	}
}
