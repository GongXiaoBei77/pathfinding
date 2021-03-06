package me.gxb.pathfinding;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;

public class onCommand implements CommandExecutor {

    //基本参数
    private final Plugin main;
    private start start = null;

    public onCommand(Plugin main) {
        this.main = main;
    }

    //指令设置
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
//        判断是否是玩家输入
        if (sender instanceof Player) {
//            1个参数且为start时，为起点方块设置
            if (args.length == 1 && args[0].equals("start")) {
                onListen.setStartCode(true);

                sender.sendMessage("点击一个方块设为起点");
                return true;

            }
//            1个参数且为end时，为终点方块设置
            if (args.length == 1 && args[0].equals("end")) {
                onListen.setEndCode(true);
                sender.sendMessage("点击一个方块设为终点");
                return true;

            }
//            1个参数且为find时，开始计算
            if (args.length == 1 && args[0].equals("find")) {
                start = new start(sender, args);
                start.runTaskAsynchronously(main);
                return true;
            }
//            1个参数且参数是build时，是建造模式
            if (args.length == 1 && args[0].equals("build")) {
                if (start.getMinf() != null) {
                    Astart minf = start.getMinf();
                    while (minf != null) {
                        mmp.setBolck(minf.getLoc(), Material.IRON_BLOCK, sender);
                        minf = minf.getF_node();
                    }
                    sender.sendMessage("路径建造完成");
                    return true;

                }
            }
            if (args.length == 1 && args[0].equals("help")) {
                sender.sendMessage("/ph start 点击一个方块设为起点");
                sender.sendMessage("/ph end   点击一个方块设为终点");
                sender.sendMessage("/ph find  开始计算");
                sender.sendMessage("/ph build 建造路径");
                sender.sendMessage("/ph help  查看帮助");
                return true;

            }
//            1个参数且参数为stop时，停止计算（线程终止，防止输入错误等问题）
            if (args.length == 1 && args[0].equals("stop")) {
                sender.sendMessage("停止计算！");
                start.setStart_code(false);
                start.cancel();
                return true;

            }
        return false;
        } else {
            sender.sendMessage("你必须是一名玩家");
        }
        return false;
    }
}

