package com.reu_24.tat.init;

import com.reu_24.tat.damagesource.LaserDamageSource;
import com.reu_24.tat.damagesource.RadioactiveDamageSource;
import net.minecraft.util.DamageSource;

public class DamageSourceInit {

    public static DamageSource LASER = new LaserDamageSource();
    public static DamageSource RADIOACTIVE = new RadioactiveDamageSource().setDamageBypassesArmor();

}
