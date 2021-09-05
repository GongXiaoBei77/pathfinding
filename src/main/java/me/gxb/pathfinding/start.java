package me.gxb.pathfinding;

import org.bukkit.Location;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.ArrayList;
import java.util.HashSet;

public class start extends BukkitRunnable {
    private final CommandSender sender;
    private final String[] args;
    public boolean start_code = true;
    private Astart minf;

    public start(CommandSender sender, String[] args) {
        this.sender = sender;
        this.args = args;
    }

    public start setStart_code(boolean start_code) {
        this.start_code = start_code;
        return this;
    }

    public Astart getMinf() {
        return minf;
    }

    @Override
    public void run() {
        minf = null;
        Location location = ((Player) sender).getLocation();
        int x, y, z;
        x = (int) location.getX();
        y = (int) location.getY() - 1;
        z = (int) location.getZ();
        ArrayList start = new ArrayList<Integer>();
        start.add(x);
        start.add(y);
        start.add(z);
        int x_1, y_2, z_3;
        x_1 = Integer.parseInt(args[0]);
        y_2 = Integer.parseInt(args[1]);
        z_3 = Integer.parseInt(args[2]);
        ArrayList end = new ArrayList<Integer>();
        end.add(x_1);
        end.add(y_2);
        end.add(z_3);

        Astart f_node = null;
//                        父节点
        Astart start_node = new Astart(start, 0, mmp.getF(start, end), start, f_node);
//                        起节点
        HashSet<Astart> openlist = new HashSet<>();
//                        开启列表 待搜索方框
        HashSet<Astart> closelist = new HashSet<>();
//                        关闭列表 已被探索过的方框
        openlist.add(start_node);
//                        把起始节点加入到开启列表中
        int num = 0;
        while (start_code) {
            minf = mmp.Minf(openlist);
//                            取出openlist最小的节点 为minf
            closelist.add(minf);
//                            把这个节点移到 close list
            openlist.remove(minf);


//                遍历起始节点周围的节点 为L

            num++;
//                            num 是每次附近可用的节点数量

            for (ArrayList<Integer> o : mmp.getL(minf.getLoc(), ((Player) sender).getWorld())) {
//                                遍历周围所有的节点 且可用通过


                Astart astart = new Astart(o, minf.getG() + 1, mmp.getF(o, end), minf.getLoc(), minf);
//                                它
//                            如果它是不可抵达的或者它在 close list 中，忽略它。
                if (closelist.contains(astart)) {
                    break;
//                                    否则，做如下操作
                } else {
//                                    如果它不在 open list 中，把它加入 open list ，并且把当前方格设置为它的父亲，记录该方格的 F ， G 和 H 值。
                    if (!closelist.contains(astart)) {
                        openlist.add(astart);
//                                        如果它已经在 open list 中，检查这条路径(即经由当前方格到达它那里) 是否更好，用 G 值作参考。更小的 G 值表示这是更好的路径。如果是这样，
//                                        把它的父亲设置为当前方格，并重新计算它的 G 和 F 值。如果你的 open list 是按 F 值排序的话，改变后你可能需要重新排序。
                    } else {
//暂时为无
                    }

                }

            }
            if (num % 10 == 0) {
                sender.sendMessage("已计算" + num + "次");
            }
//                最小损失节点 minf
            if (minf.getLoc() != null) {
                if (minf.getLoc().equals(end)) {
//                    当前的位置为终点
                    sender.sendMessage("找到路径");


                    break;

                } else if (openlist == null) {
                    break;


                }

            } else {
                sender.sendMessage("位置错误！");
                break;
            }

        }


//            把周围可以遍历的节点遍历一次，并拿出最小的代价的节点


    }
}
