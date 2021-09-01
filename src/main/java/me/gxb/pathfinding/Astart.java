package me.gxb.pathfinding;

import java.util.ArrayList;

public class Astart {
    private Astart F_node;

    public Astart getF_node() {
        return F_node;
    }

    private ArrayList<Integer> loc;
    private ArrayList<Integer> F_loc;
    private int f;
    private int g;
    private int h;

    public Astart(ArrayList<Integer> loc, int g, int h, ArrayList<Integer> F_loc, Astart F_node) {
        this.loc = loc;
        this.f = g + h;
        this.g = g;
        this.h = h;
        this.F_loc = F_loc;
        this.F_node = F_node;
    }


    public Astart() {

    }


    @Override
    public String toString() {
        return "Astart{" +
                "坐标=" + loc +
                ", 损失=" + f +
                ", 父节点=" + F_loc +
                ", 离起点的距离=" + g +
                ", 离终点的距离=" + h +
                ", 父节点=" + F_node +
                '}';
    }

    public ArrayList<Integer> getLoc() {
        return loc;
    }


    public int getF() {
        return f;
    }

    public int getG() {
        return g;
    }

}

