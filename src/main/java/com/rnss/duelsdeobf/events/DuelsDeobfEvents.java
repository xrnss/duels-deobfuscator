package com.rnss.duelsdeobf.events;

import com.rnss.duelsdeobf.Deobfuscator;
import net.minecraft.client.Minecraft;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class DuelsDeobfEvents {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        if (event.entity == Minecraft.getMinecraft().thePlayer) {
            if (Minecraft.getMinecraft().getCurrentServerData().serverIP.endsWith(".hypixel.net")) {
                Deobfuscator.getInstance().setHypixel(true);
            }
        }
    }

    @SubscribeEvent
    public void onEntityLeaveServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        Deobfuscator.getInstance().setHypixel(false);
    }

    @SubscribeEvent
    public void onChatMsg(ClientChatReceivedEvent event) {
        if (event.isCanceled() || !Deobfuscator.getInstance().isEnabled() || !Deobfuscator.getInstance().isHypixel()) return;

        String formatted = event.message.getFormattedText();

        if (formatted.contains(" has joined (") || formatted.contains(" has quit!")) {
            String pattern = "\u00a7k";
            formatted = formatted.replaceAll(pattern, "");
            event.setCanceled(true);
            Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText(formatted));
        }
    }
}

