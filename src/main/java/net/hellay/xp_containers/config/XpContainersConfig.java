package net.hellay.xp_containers.config;

import eu.midnightdust.lib.config.MidnightConfig;

public class XpContainersConfig extends MidnightConfig {
    @Comment public static Comment xpInfo1;
    @Comment public static Comment xpInfo2;
    @Entry(min = 8, max = 64) public static int xpBottleMaxXp = 16;
    @Entry(min = 16, max = 128) public static int xpJarMaxXp = 64;
}
