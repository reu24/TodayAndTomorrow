package com.reu_24.tat.damagesource;

import net.minecraft.entity.LivingEntity;
import net.minecraft.util.DamageSource;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class LaserDamageSource extends DamageSource {

    public LaserDamageSource() {
        super("laser");
    }

    @Override
    public ITextComponent getDeathMessage(LivingEntity entityLivingBaseIn) {
        return new TranslationTextComponent("death.attack.laser.message", entityLivingBaseIn.getDisplayName());
    }
}
