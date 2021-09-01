package me.gxb.pathfinding;

import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {
    @Override
    public void onEnable() {
        // Plugin startup logic
        registerCommand();
        registerLister();
        System.out.println("坐标插件已加载");

    }

    @Override
    public void onDisable() {
        Bukkit.getScheduler().cancelTasks(this);
        // Plugin shutdown logic
    }
//注册命令
    public void registerCommand() {
        Bukkit.getPluginCommand("ph").setExecutor(new startCoordinates(this));
    }
    public void registerLister(){
//        getServer().getPluginManager().registerEvents(new onlister(), this);
    }
}

