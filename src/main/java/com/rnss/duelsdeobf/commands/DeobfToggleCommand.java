package com.rnss.duelsdeobf.commands;

import com.rnss.duelsdeobf.Deobfuscator;
import com.rnss.duelsdeobf.Utils;
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
        Deobfuscator deobfuscator = Utils.deobfuscator;
        deobfuscator.setEnabled(!deobfuscator.isEnabled());
        Boolean enabled = deobfuscator.isEnabled();
        EnumChatFormatting formatColour = (enabled ? EnumChatFormatting.DARK_GREEN : EnumChatFormatting.DARK_RED);

        Utils.minecraft.thePlayer.addChatMessage(
                new ChatComponentText(
                        "Duels Deobfuscator set to: " + formatColour + enabled
                )
        );
    }
}