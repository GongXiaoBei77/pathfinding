package me.gxb.pathfinding;

import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    private ProtocolManager protocolManager;
    @Override
    public void onEnable() {
        // Plugin startup logic
        protocolManager = ProtocolLibrary.getProtocolManager();;
        registerCommand();
        registerLister();
        getLogger().info("坐标插件已加载");

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
//        关闭线程
        // Plugin shutdown logic
    }

    //注册命令
    public void registerCommand() {
        Bukkit.getPluginCommand("ph").setExecutor(new onCommand(this));
    }

    public void registerLister() {

        getServer().getPluginManager().registerEvents(new onListen(), this);
//        备用 注册监听器
    }

    public Plugin logger() {
        return this;
    }
}

