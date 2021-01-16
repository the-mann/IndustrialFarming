package net.manncraft.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import lombok.Getter;
import net.milkbowl.vault.chat.Chat;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.permission.Permission;
import org.bukkit.Server;
import org.bukkit.plugin.RegisteredServiceProvider;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.nio.file.Files;
import java.util.function.Supplier;
import java.util.logging.Logger;

public abstract class VaultContextBase<T> implements VaultContextBaseInterface {
    ObjectMapper mapper = new ObjectMapper(new YAMLFactory()).configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

    T config;

    File configFile;

    private final Logger log;

    @Getter
    private Economy econ = null;
    @Getter
    private Permission perms = null;
    @Getter
    private Chat chat = null;

    public VaultContextBase(JavaPlugin plugin, Class<T> configType, Supplier<T> newConfig) {
        log = plugin.getLogger();
        try {
            configFile = new File(plugin.getDataFolder().getCanonicalPath(), "config.yml");
            if (configFile.exists()) {
                config = mapper.readValue(configFile, configType);
            }else {
                config = newConfig.get();
            }

        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        // require economy
        if (!setupEconomy(plugin.getServer()) ) {
            log.severe(String.format("[%s] - Disabled due to no Vault dependency found!", plugin.getDescription().getName()));
            plugin.getServer().getPluginManager().disablePlugin(plugin);
            return;
        }
       // TODO: optional for now
       setupPermissions(plugin.getServer());
       // setupChat(plugin.getServer());

    }

    private boolean setupEconomy(Server server) {
        if (server.getPluginManager().getPlugin("Vault") == null) {
            return false;
        }
        RegisteredServiceProvider<Economy> rsp = server.getServicesManager().getRegistration(Economy.class);
        if (rsp == null) {
            return false;
        }
        econ = rsp.getProvider();
        return econ != null;
    }

    private boolean setupChat(Server server) {
        RegisteredServiceProvider<Chat> rsp = server.getServicesManager().getRegistration(Chat.class);
        chat = rsp.getProvider();
        return chat != null;
    }

    private boolean setupPermissions(Server server) {
        RegisteredServiceProvider<Permission> rsp = server.getServicesManager().getRegistration(Permission.class);
        perms = rsp.getProvider();
        return perms != null;
    }

    public void shutdown() {
        try {
            configFile.getParentFile().mkdirs();
            mapper.writeValue(configFile, config);
        } catch (IOException e) {
            log.severe("UH OH! Something went horribly wrong saving: ");
            e.printStackTrace();
        }
    }
}
