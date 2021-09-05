package me.gxb.pathfinding;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class onListen implements Listener {
    private static Location startLocation;
    private static Location endLocation;
    private static boolean startCode;
    private static boolean endCode;

    public static Location getStartLocation() {
        return startLocation;
    }

    public static Location getEndLocation() {
        return endLocation;
    }

    public static void setStartCode(boolean startCode) {
        onListen.startCode = startCode;
    }

    public static void setEndCode(boolean endCode) {
        onListen.endCode = endCode;
    }

    @EventHandler(ignoreCancelled = true)
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getHand() == EquipmentSlot.HAND) {
            if (startCode) {

                Block clickedBlock = event.getClickedBlock();
                startLocation = clickedBlock.getLocation();
                mmp.spawnAS(event.getPlayer(), 1, startLocation);
                event.getPlayer().sendMessage("起点设置完成");
                event.getPlayer().sendMessage("输入/ph end设置终点 或 直接点击 ");
                startCode = false;
                endCode = true;

            } else if (endCode) {

                Block clickedBlock = event.getClickedBlock();
                endLocation = clickedBlock.getLocation();
                mmp.spawnAS(event.getPlayer(), 2, endLocation);
                event.getPlayer().sendMessage("终点设置完成");
                event.getPlayer().sendMessage("输入/ph find,开始计算");
                endCode = false;
            }
        }


    }
}
