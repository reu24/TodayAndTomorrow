package com.reu_24.tat.effect;

import com.reu_24.tat.init.DamageSourceInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.Effect;
import net.minecraft.potion.EffectType;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.text.TranslationTextComponent;

public class RadioactiveEffect extends Effect {

    public static final int DAMAGE = 100;

    public RadioactiveEffect(EffectType typeIn, int liquidColorIn) {
        super(typeIn, liquidColorIn);
    }

    @Override
    public void performEffect(LivingEntity entityLivingBaseIn, int amplifier) {
        entityLivingBaseIn.attackEntityFrom(DamageSourceInit.RADIOACTIVE, 1.0F);
    }

    @Override
    public boolean isReady(int duration, int amplifier) {
        int j = DAMAGE >> amplifier;
        if (j > 0) {
            return duration % j == 0;
        } else {
            return true;
        }
    }

    @Override
    public String getName() {
        return getDisplayName().getFormattedText();
    }

    @Override
    public ITextComponent getDisplayName() {
        return new TranslationTextComponent("effect.tat.radioactive");
    }
}
