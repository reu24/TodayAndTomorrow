package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.entity.HydrogenTNTEntity;
import com.reu_24.tat.util.ModResourceLocation;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class ModEntityTypes {
    public static final DeferredRegister<EntityType<?>> ENTITY_TYPES = new DeferredRegister<>(ForgeRegistries.ENTITIES, TodayAndTomorrow.MOD_ID);

    public static final RegistryObject<EntityType<HydrogenTNTEntity>> HYDROGEN_TNT = ENTITY_TYPES.register("hydrogen_tnt", () -> EntityType.Builder.<HydrogenTNTEntity>create(HydrogenTNTEntity::new, EntityClassification.MISC).immuneToFire().size(0.98F, 0.98F).setCustomClientFactory((spawnEntity, world) -> new HydrogenTNTEntity(world, spawnEntity.getPosX(), spawnEntity.getPosY(), spawnEntity.getPosZ(), null)).build(new ModResourceLocation("hydrogen_tnt").toString()));
}
