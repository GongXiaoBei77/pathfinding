package me.gxb.pathfinding;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import com.comphenix.protocol.wrappers.WrappedBlockData;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.UUID;


public class mmp {
    public static void spawnAS(CommandSender sender, Integer entityID,Location loc){
        Player player = (Player) sender;

        PacketContainer packet = new PacketContainer(PacketType.Play.Server.SPAWN_ENTITY_LIVING);// 示例一个PacketContainer
        packet.getModifier().writeDefaults(); // 写入默认值
        packet.getIntegers().write(0, entityID); // 对integers的第0个写入实体ID，注意这里，实体ID不要重叠，否则会被覆盖
        packet.getUUIDs().write(0, UUID.randomUUID()) ;// 对UUID的第0个写入UUID值，这里使用了随机生成UUID值
        packet.getIntegers().write(1, (int) EntityType.ARMOR_STAND.getTypeId()) ;
        packet.getDoubles().write(0, loc.getX()+0.5); // 对Double的第0个写入位置的X值，不写的话，你知道它在哪个角落躲着吗
        packet.getDoubles().write(1, loc.getY()) ;// 对Double的第1个写入位置的Y值
        packet.getDoubles().write(2, loc.getZ() +0.5); // 对Double的第2个写入位置的Z值，这些顺序参照格式表（自己回去看格式表，第三个出现的Double就是位置的Z值）

        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet) ;// 发送数据包
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }




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
    public static void setBolck(ArrayList<Integer> start, Material Material, CommandSender sender) {
        Player player = (Player) sender;
        PacketContainer packet = new PacketContainer(PacketType.Play.Server.BLOCK_CHANGE);// 示例一个PacketContainer
        packet.getModifier().writeDefaults(); // 写入默认值
        BlockPosition blockPosition = new BlockPosition(start.get(0), start.get(1), start.get(2));
        WrappedBlockData wrappedBlockData = WrappedBlockData.createData(Material);
        packet.getBlockPositionModifier().write(0, blockPosition);
        packet.getBlockData().write(0, wrappedBlockData);


        try {
            ProtocolLibrary.getProtocolManager().sendServerPacket(player, packet);// 发送数据包
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }


    }

    //返回输入节点的周围节点
    public static ArrayList<ArrayList> getL(ArrayList<Integer> start, World world) {
        ArrayList<ArrayList> trueList = new ArrayList(3);
        ArrayList<ArrayList> Alllist = new ArrayList(3);

        for (int i = 0; i < start.size(); i += 2) {
            ArrayList<Integer> copyList = new ArrayList<>(start);
            copyList.set(i, start.get(i) + 1);
            if (mmp.isBlock(copyList, world)) {
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

}
