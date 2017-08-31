package me.vitikc.awdamagemod.draw;

import me.vitikc.awdamagemod.config.AWValuesManager;
import me.vitikc.awdamagemod.listeners.AWRenderGuiEvent;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiChat;
import net.minecraft.client.gui.ScaledResolution;

public class AWDrawText {
    private static Minecraft mc = Minecraft.getMinecraft();
    private static float offset = 0;


    // Shows scrolling message to client
    public static void renderToHud(String text, int color) {
        if ((mc.inGameHasFocus || (mc.currentScreen != null && (mc.currentScreen instanceof GuiChat))) && !mc.gameSettings.showDebugInfo) {
            ScaledResolution res = new ScaledResolution(AWDrawText.mc, AWDrawText.mc.displayWidth, AWDrawText.mc.displayHeight);
            int width = res.getScaledWidth();
            int height = res.getScaledHeight();
            int x = width / 2 - 25;
            int y = height / 12 - (int) offset;
            offset += AWValuesManager.TEXT_OFFSET;

            mc.fontRenderer.drawStringWithShadow(text, x, y, color);
            if (offset >= AWValuesManager.MAX_TEXT_OFFSET){
                reset();
            }
        }
    }

    // If no need for showing text - reset
    public static void reset(){
        offset = 0;
        AWRenderGuiEvent.isNeedRender = false;
    }


}
