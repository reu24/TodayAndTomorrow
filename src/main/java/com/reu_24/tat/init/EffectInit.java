package com.reu_24.tat.init;

import com.reu_24.tat.TodayAndTomorrow;
import com.reu_24.tat.effect.RadioactiveEffect;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;

public class EffectInit {
    public static final DeferredRegister<Effect> EFFECTS = new DeferredRegister<>(ForgeRegistries.POTIONS, TodayAndTomorrow.MOD_ID);

    public static final RegistryObject<Effect> RADIOACTIVE = EFFECTS.register("radioactive", () ->
            new RadioactiveEffect(EffectType.HARMFUL, 0x8FD544));
}
