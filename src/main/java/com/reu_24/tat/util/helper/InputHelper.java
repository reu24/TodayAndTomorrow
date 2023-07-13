package com.reu_24.tat.util.helper;

import com.mojang.datafixers.util.Pair;
import com.reu_24.tat.TodayAndTomorrow;
import org.lwjgl.glfw.GLFW;

import net.minecraft.client.Minecraft;
import net.minecraft.client.util.InputMappings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import javax.swing.plaf.basic.BasicListUI;
import java.nio.DoubleBuffer;

public final class InputHelper {

    private InputHelper() { }

    private static final long WINDOW = Minecraft.getInstance().getMainWindow().getHandle();

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingShift() {
        return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_SHIFT) ||
                InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_SHIFT);
    }

    @OnlyIn(Dist.CLIENT)
    public static boolean isHoldingControl() {
        return InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_LEFT_CONTROL) ||
                InputMappings.isKeyDown(WINDOW, GLFW.GLFW_KEY_RIGHT_CONTROL);
    }

    public static boolean isHoldingLeftMouseButton() {
        return GLFW.glfwGetMouseButton(WINDOW, GLFW.GLFW_MOUSE_BUTTON_1) == 1;
    }

    public static Pair<Double, Double> getMousePos() {
        double[] x = new double[1];
        double[] y = new double[1];
        GLFW.glfwGetCursorPos(WINDOW, x, y);
        return new Pair<>(x[0], y[0]);
    }
}
