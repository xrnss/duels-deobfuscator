package com.rnss.duelsdeobf.commands;

import com.rnss.duelsdeobf.Deobfuscator;
import net.minecraft.client.Minecraft;
import net.minecraft.command.CommandBase;
import net.minecraft.command.CommandException;
import net.minecraft.command.ICommandSender;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.EnumChatFormatting;

public class DeobfToggleCommand extends CommandBase {
    @Override
    public String getCommandName() {
        return "duelsdeobfuscator";
    }

    @Override
    public String getCommandUsage(ICommandSender sender) {
        return "Toggles Duels name obfuscation";
    }

    @Override
    public int getRequiredPermissionLevel() {
        return 0;
    }

    @Override
    public void processCommand(ICommandSender sender, String[] args) throws CommandException {
        Deobfuscator.getInstance().setEnabled(!Deobfuscator.getInstance().isEnabled());
        Minecraft.getMinecraft().thePlayer.addChatMessage(new ChatComponentText("Duels Deobfuscator set to: " + (Deobfuscator.getInstance().isEnabled() == false ? EnumChatFormatting.DARK_RED : EnumChatFormatting.DARK_GREEN) + Deobfuscator.getInstance().isEnabled()));
    }
}