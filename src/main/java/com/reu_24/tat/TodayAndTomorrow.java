package com.reu_24.tat;

import com.reu_24.tat.init.*;
import com.reu_24.tat.util.TatPacketHandler;
import com.reu_24.tat.world.gen.OreGen;
import com.reu_24.tat.world.gen.StructureGen;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLLoadCompleteEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(TodayAndTomorrow.MOD_ID)
@Mod.EventBusSubscriber(modid = TodayAndTomorrow.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
public class TodayAndTomorrow
{
    public static final String MOD_ID = "tat";
    public static TodayAndTomorrow instance;

    public static final Logger LOGGER = LogManager.getLogger();

    public TodayAndTomorrow() {
        instance = this;

        final IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(this::setup);

        ParticleInit.PARTICLES.register(modEventBus);
        SoundInit.SOUNDS.register(modEventBus);
        EffectInit.EFFECTS.register(modEventBus);
        ItemInit.ITEMS.register(modEventBus);
        RecipeSerializerInit.RECIPE_SERIALIZERS.register(modEventBus);
        FluidInit.FLUIDS.register(modEventBus);
        BlockInit.BLOCKS.register(modEventBus);
        BiomeInit.BIOMES.register(modEventBus);
        DimensionInit.MOD_DIMENSIONS.register(modEventBus);
        FeatureInit.FEATURES.register(modEventBus);

        ModTileEntityTypes.TILE_ENTITY_TYPE.register(modEventBus);
        ModContainerTypes.CONTAINER_TYPES.register(modEventBus);
        ModEntityTypes.ENTITY_TYPES.register(modEventBus);
        MinecraftForge.EVENT_BUS.register(this);
        TatPacketHandler.register();
    }

    @SubscribeEvent
    public static void onRegisterBiomes(final RegistryEvent.Register<Biome> event) {
        BiomeInit.registerBiomes();
    }

    @SubscribeEvent
    public static void loadCompleteEvent(FMLLoadCompleteEvent event) {
        StructureGen.generateStructure();
        OreGen.generateOres();
    }

    @SubscribeEvent
    public static void onRegisterEntities(final RegistryEvent.Register<EntityType<?>> event) {

    }

    private void setup(final FMLCommonSetupEvent event) {

    }
}
