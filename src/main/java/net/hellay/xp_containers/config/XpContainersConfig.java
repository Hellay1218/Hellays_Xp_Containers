package net.hellay.xp_containers.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class XpContainersConfig extends MidnightConfig {
    public enum XpStateEnum {LEVELS, POINTS}

    @Comment(centered = true) public static Comment disclaimer1;
    @Comment(centered = true) public static Comment disclaimer2;
    @Comment() public static Comment header;
    @Entry() public static XpStateEnum xpStateEnum = XpStateEnum.POINTS;
    @Comment() public static Comment xpStateExplanation;
    @Entry(isSlider = true, min = 8, max = 64) public static int xpBottleMaxXp = 16;
    @Entry(isSlider = true, min = 16, max = 128) public static int xpJarMaxXp = 64;


}
