package com.example.treasure.treasure;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

/**
 * 宝藏仓库，用来强应用宝藏以及区域数据
 * Created by 93432 on 2016/7/19.
 */
public class TreasureRepo {

    private static TreasureRepo treasureRepo;

    private TreasureRepo(){}

    public static TreasureRepo getInstance(){
        if(treasureRepo == null){
            treasureRepo = new TreasureRepo();
        }
        return treasureRepo;
    }

    //用来保存所有已获取过的数据
    private final HashSet<Area> cachedAreas = new HashSet<Area>();
    //用来保存所有已获取到的宝藏（Map模式还有一个List模式）
    private final HashMap<Integer, Treasure> treasureMap = new HashMap<Integer, Treasure>();

    public void cache(Area area){
        cachedAreas.add(area);
    }

    public boolean isCached(Area area){
        //是否已存在（包含）
        //先比hashCode再比equals
        //注意重写
        return cachedAreas.contains(area);
    }

    public void addTreasure(List<Treasure> treasureList){
        for (Treasure treasure : treasureList){
            treasureMap.put(treasure.getId(), treasure);
        }
    }

    /**
     * 根据宝藏id获取宝藏
     * @param id
     * @return
     */
    public Treasure getTreasure(int id){
        return treasureMap.get(id);
    }

    public Collection<Treasure> getTreasure(){
        return treasureMap.values();
    }

    public void clear(){
        cachedAreas.clear();
        treasureMap.clear();
    }

}
