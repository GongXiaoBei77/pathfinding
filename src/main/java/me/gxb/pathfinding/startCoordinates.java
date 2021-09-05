package me.gxb.pathfinding;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class startCoordinates implements CommandExecutor {

    //基本参数
    private final Plugin main;
    private start start = null;

    public startCoordinates(Plugin main) {
        this.main = main;
    }

    //指令设置
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        判断是否是玩家输入
        if (sender instanceof Player) {
//            3个参数时，为计算模式
            if (args.length == 3) {
                start = new start(sender, args);
                start.runTaskAsynchronously(main);
                main.getLogger().info("完成");
            }
//            1个参数且参数是build时，是建造模式
            if (args.length == 1 && args[0].equals("build")) {
                if (start.getMinf() != null) {
                    Astart minf = start.getMinf();
                    while (minf != null) {
                        mmp.setBolck(minf.getLoc(), Material.IRON_BLOCK, ((Player) sender).getWorld());
                        minf = minf.getF_node();
                    }
                    sender.sendMessage("路径建造完成");
                    return true;

                }
            }
//            1个参数且参数为stop时，停止计算（线程终止，防止输入错误等问题）
            if (args.length == 1 && args[0].equals("stop")) {
                main.getLogger().info("cancel:" + start);
                start.setStart_code(false);
                start.cancel();
                return true;

            }

        } else {
            sender.sendMessage("你必须是一名玩家");
        }
        return true;
    }
}

