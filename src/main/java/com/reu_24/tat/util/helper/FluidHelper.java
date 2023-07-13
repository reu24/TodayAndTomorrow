package com.reu_24.tat.util.helper;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.fluids.capability.IFluidHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Random;

public class FluidHelper {

    public static String toBucket(int fluid) {
        float n = (float)fluid / 1000.0f;
        float k = n / 1000.0f;
        float m = k / 1000.0f;

        DecimalFormat dec = new DecimalFormat("0.0");

        if (m >= 1) {
            return dec.format(m) + " " + new TranslationTextComponent("fluid.tat.megabucket").getFormattedText();
        }
        if (k >= 1) {
            return dec.format(k) + " " + new TranslationTextComponent("fluid.tat.kilobucket").getFormattedText();
        }
        if (n >= 1) {
            return dec.format(n) + " " + new TranslationTextComponent("fluid.tat.bucket").getFormattedText();
        }
        return fluid + " " + new TranslationTextComponent("fluid.tat.millibucket").getFormattedText();
    }


    public static int scatterFluid(ArrayList<IFluidHandler> fluidsToScatter, int maxGive, Random random, boolean simulate) {
        int givenAway = 0;

        if (maxGive == Integer.MAX_VALUE) {
            for (IFluidHandler fluidToScatter : fluidsToScatter) {
                givenAway += fluidToScatter.fill(new FluidStack(fluidToScatter.getFluidInTank(0).getFluid(), maxGive), IFluidHandler.FluidAction.SIMULATE);
            }
            return givenAway;
        }

        float remains = 0.0f;
        for (int i = 0; i < fluidsToScatter.size(); i++) {
            float toGive = (float)maxGive / i;
            remains += toGive - (int)(toGive);
            givenAway += fluidsToScatter.get(i).fill(new FluidStack(fluidsToScatter.get(i).getFluidInTank(0).getFluid(), (int)toGive), IFluidHandler.FluidAction.EXECUTE);
        }

        if (remains > 0.0f) {
            int randomIndex = random.nextInt(fluidsToScatter.size());
            givenAway += fluidsToScatter.get(randomIndex).fill(new FluidStack(fluidsToScatter.get(randomIndex).getFluidInTank(0).getFluid(), 1), IFluidHandler.FluidAction.EXECUTE);
        }
        return givenAway;
    }
}
