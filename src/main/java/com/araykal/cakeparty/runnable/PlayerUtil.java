package com.araykal.cakeparty.runnable;


import net.kyori.adventure.text.Component;
import net.kyori.adventure.title.Title;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.time.Duration;

public class PlayerUtil {
    /**
     * 更方便的发送标题
     *
     * @param p        发送title的目标玩家
     * @param title    主标题
     * @param subTitle 副标题
     * @param fadeIn   渐入时间(s)
     * @param stay     保持时间(s)
     * @param fadeOut  渐出时间(s)
     */
    public static void sendTitle(Player p, String title, String subTitle, int fadeIn, int stay, int fadeOut) {
        p.showTitle(Title.title(Component.text(title), Component.text(subTitle), Title.Times.times(Duration.ofSeconds(fadeIn), Duration.ofSeconds(stay), Duration.ofSeconds(fadeOut))));
    }
    public static void sendTitle(Player p, String title, String subTitle, double fadeIn, double stay, double fadeOut) {
        p.showTitle(Title.title(Component.text(title), Component.text(subTitle), Title.Times.times(Duration.ofMillis((int)(1000 * fadeIn)), Duration.ofMillis((int)(1000 * stay)), Duration.ofMillis((int)(1000 * fadeOut)))));
    }
    /**
     * 向玩家发送Actionbar消息
     *
     * @param p         目标玩家
     * @param message   消息内容
     */
    public static void sendActionBar(Player p,String message)
    {
        p.sendActionBar(Component.text(message));
    }

    /**
     * 更方便的发送全服公告
     *
     * @param message 消息
     */
    public static void serverBroadcast(String message) {
        Bukkit.getServer().sendMessage(Component.text(message));
    }
}
