package me.gxb.pathfinding;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.ArrayList;
import java.util.HashSet;


public class mmp {


    public mmp() {
    }

    //    测试功能 建造可以

    //  判断是否可以通行
    public static boolean isBlock(ArrayList<Integer> start, World world) {
        boolean Empty_up = false;
        Block block_up_1 = world.getBlockAt(start.get(0), start.get(1) + 1, start.get(2));
        Block block_up_2 = world.getBlockAt(start.get(0), start.get(1) + 2, start.get(2));
        Block block_down_2 = world.getBlockAt(start.get(0), start.get(1), start.get(2));
        if (block_up_1.isEmpty() && block_up_2.isEmpty() && !block_down_2.isEmpty()) {
            Empty_up = true;
        }


        return Empty_up;

    }

    //    返回最小的代价的节点
    public static Astart Minf(HashSet<Astart> openlist) {

        int min = 0;
        Astart astart1 = new Astart();
        for (Astart astart : openlist) {
            min = astart.getF();
            astart1 = astart;
            break;

        }
        for (Astart astart : openlist) {
            if (min >= astart.getF()) {
                min = astart.getF();
                astart1 = astart;

            }

        }

        return astart1;
    }

    //建筑方块
    public static void setBolck(ArrayList<Integer> start, Material Material, World world) {
        Block block = world.getBlockAt(start.get(0), start.get(1), start.get(2));
        block.setType(Material);


    }

    //返回输入节点的周围节点
    public static ArrayList<ArrayList> getL(ArrayList<Integer> start, World world) {
        ArrayList<ArrayList> trueList = new ArrayList(3);
        ArrayList<ArrayList> Alllist = new ArrayList(3);

        for (int i = 0; i < start.size(); i += 2) {
            ArrayList<Integer> copyList = new ArrayList<>(start);
            copyList.set(i, start.get(i) + 1);
            if (mmp.isBlock(copyList,world)) {
                trueList.add(copyList);
            }
            Alllist.add(copyList);


        }
        for (int i = 0; i <= start.size(); i += 2) {
            ArrayList<Integer> copyList = new ArrayList<>(start);
            copyList.set(i, start.get(i) - 1);
            if (mmp.isBlock(copyList, world)) {
                trueList.add(copyList);
            }
            Alllist.add(copyList);


        }
//        +y
        for (int i = 0; i < Alllist.size(); i++) {
            ArrayList<Integer> add_arrayList = new ArrayList<>(Alllist.get(i));
            add_arrayList.set(1, add_arrayList.get(1) + 1);
            if (mmp.isBlock(add_arrayList, world)) {
                trueList.add(add_arrayList);
            }


        }
//        -y

        for (int i = 0; i < Alllist.size(); i++) {
            ArrayList<Integer> add_arrayList = new ArrayList<>(Alllist.get(i));
            add_arrayList.set(1, add_arrayList.get(1) - 1);
            if (mmp.isBlock(add_arrayList, world)) {
                trueList.add(add_arrayList);
            }


        }


        return trueList;

    }

    //    返回坐标代价 G离起点的距离 H离终点的距离
    public static int getF(ArrayList<Integer> start, ArrayList<Integer> end) {
        int H_cost = 0;
        int Max_i = start.size();

        for (int i = 0; i < Max_i; i++) {
            H_cost = H_cost + Math.abs(start.get(i) - end.get(i));

        }


        return H_cost;

    }

    public static int getH(ArrayList<Integer> start, ArrayList<Integer> end, World world) {


        Block startBlock = world.getBlockAt(start.get(0), start.get(1), start.get(2));

        Block endBlock = world.getBlockAt(end.get(0), end.get(1), end.get(2));
        Location startLocation = startBlock.getLocation();
        Location endLocation = endBlock.getLocation();
        int distance = (int) startLocation.distance(endLocation);
        return distance;

    }
}
