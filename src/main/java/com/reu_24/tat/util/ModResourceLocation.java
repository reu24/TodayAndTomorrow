package com.reu_24.tat.util;

import com.reu_24.tat.TodayAndTomorrow;
import net.minecraft.util.ResourceLocation;

public class ModResourceLocation extends ResourceLocation {
    public ModResourceLocation(String pathIn) {
        super(TodayAndTomorrow.MOD_ID, pathIn);
    }
}
