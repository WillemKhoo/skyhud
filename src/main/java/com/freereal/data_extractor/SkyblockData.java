package com.freereal.data_extractor;

public class SkyblockData {
    // Creating singleton
    private static SkyblockData instance = null;

    // Creating skyblock data
    public int hp, maxHp;
    public int mp, maxMp;
    public int def;
    public String currentAbility;

    private SkyblockData() {
        hp = maxHp = mp = maxMp = 0;
        currentAbility = null;
    }

    public synchronized static SkyblockData getInstance() {
        if (instance == null) {
            instance = new SkyblockData();

            return instance;
        }

        return instance;
    }

    @Override
    public String toString() {
        return "hp: " + hp + "\n" +
                "maxHp: " + maxHp + "\n" +
                "mp: " + mp + "\n" +
                "maxMp: " + maxMp + "\n" +
                "def: " + def + "\n" +
                "currentAbility: " + currentAbility;
    }
}
