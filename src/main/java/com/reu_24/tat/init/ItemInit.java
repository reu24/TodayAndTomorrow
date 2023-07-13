package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.itemgroup.TodayAndTomorrowItemGroup;
import com.reu_24.tat.object.item.RadioactiveItem;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;


public class ItemInit {
	public static final DeferredRegister<Item> ITEMS = new DeferredRegister<>(ForgeRegistries.ITEMS, TodayAndTomorrow.MOD_ID);

	public static final RegistryObject<Item> COPPER_INGOT = ITEMS.register("copper_ingot",
			() -> new Item(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE)));
	public static final RegistryObject<Item> STEEL_INGOT = ITEMS.register("steel_ingot",
			() -> new Item(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE)));
	public static final RegistryObject<Item> HYDROGEN_BUCKET = ITEMS.register("hydrogen_bucket",
			() -> new Item(new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(TodayAndTomorrowItemGroup.INSTANCE)));
	public static final RegistryObject<Item> OXYGEN_BUCKET = ITEMS.register("oxygen_bucket",
			() -> new Item(new Item.Properties().containerItem(Items.BUCKET).maxStackSize(1).group(TodayAndTomorrowItemGroup.INSTANCE)));
	public static final RegistryObject<Item> EXPLOSIVE_CONTAINER = ITEMS.register("explosive_container",
			() -> new Item(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE)));
	public static final RegistryObject<Item> URANIUM = ITEMS.register("uranium",
			() -> new RadioactiveItem(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE), 1));
	public static final RegistryObject<Item> ENRICHED_URANIUM = ITEMS.register("enriched_uranium",
			() -> new RadioactiveItem(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE), 20));
	public static final RegistryObject<Item> ENRICHED_URANIUM_SHARD = ITEMS.register("enriched_uranium_shard",
			() -> new RadioactiveItem(new Item.Properties().group(TodayAndTomorrowItemGroup.INSTANCE), 2));
}
