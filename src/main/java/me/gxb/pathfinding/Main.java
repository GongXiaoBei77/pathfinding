package me.gxb.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
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
        Bukkit.getPluginCommand("ph").setExecutor(new startCoordinates(this));
    }

    public void registerLister() {

        getServer().getPluginManager().registerEvents(new onListen(), this);
//        备用 注册监听器
    }

    public Plugin logger() {
        return this;
    }
}

