package net.manncraft.utils;

import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;

public interface VaultContextBaseInterface extends PluginContextBaseInterface {
    Economy getEcon();
    Permission getPerms();
    Chat getChat();
}
