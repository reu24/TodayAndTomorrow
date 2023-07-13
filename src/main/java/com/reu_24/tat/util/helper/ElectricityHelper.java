package com.reu_24.tat.util.helper;

import net.minecraft.util.text.TranslationTextComponent;
import net.minecraftforge.energy.IEnergyStorage;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

public final class ElectricityHelper {

    private ElectricityHelper() {}

    public static int scatterEnergy(ArrayList<IEnergyStorage> energiesToScatter, int maxGive, Random random, boolean simulate) {
        int givenAway = 0;

        if (maxGive == Integer.MAX_VALUE) {
            for (IEnergyStorage energyToScatter : energiesToScatter) {
                givenAway += energyToScatter.receiveEnergy(maxGive, false);
            }
            return givenAway;
        }

        List<Float> percentagesNeeded = new ArrayList<>();
        for (IEnergyStorage energyToScatter : energiesToScatter) {
            float percentageNeeded = (float)(energyToScatter.getMaxEnergyStored() - energyToScatter.getEnergyStored()) / energyToScatter.getMaxEnergyStored();
            percentagesNeeded.add(percentageNeeded);
        }
        percentagesNeeded = normalize(percentagesNeeded);

        float remains = 0.0f;
        for (int i = 0; i < energiesToScatter.size(); i++) {
            float toGive = percentagesNeeded.get(i) * maxGive;
            remains += toGive - (int)(toGive);
            givenAway += energiesToScatter.get(i).receiveEnergy((int)toGive, simulate);
        }

        for (int i = 0; i < remains; i++) {
            int randomIndex = random.nextInt(energiesToScatter.size());
            givenAway += energiesToScatter.get(randomIndex).receiveEnergy(1, simulate);
        }
        return givenAway;
    }

    public static List<Float> normalize(List<Float> array) {
        float sum = 0;
        for (Float val : array) {
            sum += val * val;
        }
        final float finalSum = sum;

        return array.stream().map((val) -> val / finalSum).collect(Collectors.toList());
    }

    public static String toFE(int energy) {
        float k = (float)energy / 1000.0f;
        float g = k / 1000.0f;
        float t = g / 1000.0f;

        DecimalFormat dec = new DecimalFormat("0.0");

        if (t >= 1) {
            return dec.format(t) + new TranslationTextComponent("energy.tat.gigafe").getFormattedText();
        }
        if (g >= 1) {
            return dec.format(g) + new TranslationTextComponent("energy.tat.megafe").getFormattedText();
        }
        if (k >= 1) {
            return dec.format(k) + new TranslationTextComponent("energy.tat.kilofe").getFormattedText();
        }
        return energy + new TranslationTextComponent("energy.tat.fe").getFormattedText();
    }
}
