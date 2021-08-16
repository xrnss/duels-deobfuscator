package com.rnss.duelsdeobf;

import com.rnss.duelsdeobf.commands.DeobfToggleCommand;
import com.rnss.duelsdeobf.events.DuelsDeobfEvents;
import net.minecraftforge.client.ClientCommandHandler;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;

@Mod(modid = Deobfuscator.MODID, version = Deobfuscator.VERSION, acceptedMinecraftVersions="[1.8.9]")
public class Deobfuscator {
    public static final String MODID = "duelsdeobf";
    public static final String VERSION = "0.2";

    private boolean hypixel = false;
    private boolean isToggled = true; // Default to on

    @Mod.Instance
    private static Deobfuscator instance;

    @Mod.EventHandler
    public void init(FMLInitializationEvent event) {
        ClientCommandHandler.instance.registerCommand(new DeobfToggleCommand());
        MinecraftForge.EVENT_BUS.register(new DuelsDeobfEvents());
    }

    public boolean isEnabled() {
        return this.isToggled;
    }

    public boolean isHypixel() {
        return this.hypixel;
    }

    public void setEnabled(boolean enabled) {
        this.isToggled = enabled;
    }

    public void setHypixel(boolean enabled) {
        this.hypixel = enabled;
    }

    public static Deobfuscator getInstance() {
        return instance;
    }
}