package me.vitikc.awdamagemod.listeners;

import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import me.vitikc.awdamagemod.draw.AWDrawText;
import net.minecraftforge.client.event.RenderGameOverlayEvent;

public class AWRenderGuiEvent {
    public static String renderText = "TEST";
    public static int renderColor = 0xffffff;
    public static boolean isNeedRender = false;

    // Rendering text to client
    @SubscribeEvent
    public void onRenderGameOverlay(RenderGameOverlayEvent event){
        if (event.type == RenderGameOverlayEvent.ElementType.TEXT){
            if (isNeedRender)
                AWDrawText.renderToHud(renderText, renderColor);
        }
    }

    public static void setText(String text){
        AWDrawText.reset();
        renderText = text;
        isNeedRender = true;
    }
}
