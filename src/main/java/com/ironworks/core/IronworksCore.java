package com.ironworks.core;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.object.builder.v1.block.entity.FabricBlockEntityTypeBuilder;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.FurnaceBlock;
import net.minecraft.block.MapColor;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IronworksCore implements ModInitializer {
	public static final String MODID = "ironworks_core";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	//TODO add config option for copper/gold max transfer rate.
	public static final WireHookBlock COPPER_WIRE_HOOK = new WireHookBlock( AbstractBlock.Settings.of( Material.METAL, MapColor.ORANGE ), 1024 );
	public static final WireHookBlock GOLD_WIRE_HOOK = new WireHookBlock( AbstractBlock.Settings.of( Material.METAL, MapColor.YELLOW ), 4096 );

	public static final BlockEntityType WIRE_HOOK_BLOCK_ENTITY_TYPE = Registry.register(Registry.BLOCK_ENTITY_TYPE,
			new Identifier(MODID, "wire_hook_block_entity"), FabricBlockEntityTypeBuilder.create(WireHookBlockEntity::new, COPPER_WIRE_HOOK, GOLD_WIRE_HOOK).build());

	@Override
	public void onInitialize() {
		//blocks
		Registry.register(Registry.BLOCK, new Identifier(MODID, "wire_hook_copper"), COPPER_WIRE_HOOK);
		Registry.register(Registry.BLOCK, new Identifier(MODID, "wire_hook_gold"), GOLD_WIRE_HOOK);

		//items
		Registry.register(Registry.ITEM, new Identifier(MODID, "wire_hook_copper"), new BlockItem(COPPER_WIRE_HOOK, new FabricItemSettings().group( ItemGroup.MISC )));
		Registry.register(Registry.ITEM, new Identifier(MODID, "wire_hook_gold"), new BlockItem(GOLD_WIRE_HOOK, new FabricItemSettings().group( ItemGroup.MISC )));

		//block entities


		//advancements

		//item group

		//entities
	}
}
