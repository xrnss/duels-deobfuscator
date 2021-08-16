package com.rnss.duelsdeobf.events;

import com.rnss.duelsdeobf.Deobfuscator;
import com.rnss.duelsdeobf.Utils;
import com.sun.org.apache.xml.internal.dtm.ref.sax2dtm.SAX2DTM;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ServerData;
import net.minecraft.entity.Entity;
import net.minecraft.util.ChatComponentText;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.event.entity.EntityJoinWorldEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;
import org.apache.http.client.utils.CloneUtils;

public class DuelsDeobfEvents {
    @SubscribeEvent
    public void onEntityJoinWorld(EntityJoinWorldEvent event) {
        Minecraft mc = Utils.minecraft;
        Entity entity = event.entity;
        System.out.println(entity.getEntityWorld().isRemote);

        if (entity == mc.thePlayer) {
            ServerData SData = mc.getCurrentServerData();
            if (SData != null && SData.serverIP.contains("hypixel.net")) {
                Utils.deobfuscator.setHypixel(true);
            }
        }
    }

    @SubscribeEvent
    public void onEntityLeaveServer(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        Deobfuscator.getInstance().setHypixel(false);
    }

    @SubscribeEvent
    public void onChatMsg(ClientChatReceivedEvent event) {
        Deobfuscator deobfuscator = Utils.deobfuscator;
        if (event.isCanceled() || !deobfuscator.isEnabled() || !deobfuscator.isHypixel()) return;

        String formatted = event.message.getFormattedText();

        if (formatted.contains(" has joined (") || formatted.contains(" has quit!")) {
            String pattern = "\u00a7k";
            formatted = formatted.replaceAll(pattern, "");
            event.setCanceled(true);
            Utils.minecraft.thePlayer.addChatMessage(new ChatComponentText(formatted));
        }
    }
}

