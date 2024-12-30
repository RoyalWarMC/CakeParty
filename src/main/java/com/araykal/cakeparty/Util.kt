package com.araykal.cakeparty

import com.araykal.cakeparty.runnable.PlayerUtil
import org.black_ixx.playerpoints.PlayerPoints
import org.bukkit.*
import org.bukkit.entity.EntityType
import org.bukkit.entity.Firework
import org.bukkit.entity.Player
import org.bukkit.inventory.meta.FireworkMeta
import org.bukkit.scheduler.BukkitRunnable
import org.bukkit.util.Vector
import java.util.*

fun Player.send(str: String) {
    player?.sendMessage("&d&l蛋糕派对 &e>>> $str".replace("&", "§"))
}

fun Player.send2(str: String) {
    player?.sendMessage("&c&l跨年 &e>>> $str".replace("&", "§"))
}
fun Player.addPoints(int: Int): Boolean {
    player?.let { PlayerPoints.getInstance().api.give(it.uniqueId, int) }
    return true;
}

fun Player.takePoints(int: Int): Boolean {
    player?.let { PlayerPoints.getInstance().api.take(it.uniqueId, int) }
    return true
}

fun sendAllMessage(str: String) {
    Bukkit.getOnlinePlayers().forEach {
        it.send2(str)
    }
}

fun startFireworkTask(location: Location) {
    sendAllMessage("§c§l新年快乐")
    sendAllMessage("§e§l给大家拜年了")
    object : BukkitRunnable() {
        var secondsRemaining = 60
        override fun run() {
            if (secondsRemaining > 0) {
                if (secondsRemaining % 2 == 0) {
                    spawnFireworkAroundLocation(location)
                }
                secondsRemaining--
                sendAllMessage("§c烟花晚会,§e还有§b$secondsRemaining 秒 §e结束")
            } else {
                sendAllMessage("§c烟花晚会,结束！！！")
                cancel()
            }
        }
    }.runTaskTimer(CakeParty.instance, 0L, 20L)
}

fun spawnFireworkAroundLocation(centerLocation: Location) {
    val world: World = centerLocation.world ?: return
    val radius = 35
    val gap = 4
    for (x in -radius..radius step gap) {
        for (y in -radius..radius step gap) {
            for (z in -radius..radius step gap) {
                val fireworkLocation = centerLocation.clone().add(x.toDouble(), y.toDouble(), z.toDouble())
                spawnFirework(world, fireworkLocation)
            }
        }
    }
}

fun spawnFirework(world: World, location: Location) {
    val firework = world.spawn(location, Firework::class.java)
    val fireworkMeta = firework.fireworkMeta
    val effect = FireworkEffect.builder()
        .with(FireworkEffect.Type.BALL)
        .withColor(randomColor())
        .withFade(randomColor())
        .build()

    fireworkMeta.addEffect(effect)

    fireworkMeta.power = 1
    firework.fireworkMeta = fireworkMeta
}

private fun randomColor(): Color {
    val colors = listOf(Color.RED, Color.BLUE, Color.YELLOW, Color.PURPLE, Color.ORANGE)
    return colors[Random().nextInt(colors.size)]
}

/*fun spawnFireworkAroundLocation(centerLocation: Location) {
    val world: World = centerLocation.world
    world.time = 13000

    val radius = 20
    val fireworkLocations = mutableListOf<Location>()
    for (x in -radius..radius) {
        for (y in -radius..radius) {
            for (z in -radius..radius) {
                val newLocation = centerLocation.clone().add(x.toDouble(), y.toDouble(), z.toDouble())
                if (newLocation.distance(centerLocation) <= radius) {
                    fireworkLocations.add(newLocation)
                }
            }
        }
    }
    spawnFireworks(fireworkLocations, world)
}
fun spawnFireworks(fireworkLocations: List<Location>, world: World) {
    Bukkit.getScheduler().runTaskLater(CakeParty.instance, Runnable {
        for (location in fireworkLocations) {
            val firework = world.spawnEntity(location.add(0.0, 30.0, 0.0), EntityType.FIREWORK) as Firework
            val meta = firework.fireworkMeta
            val effect = FireworkEffect.builder()
                .flicker(true)
                .trail(true)
                .with(FireworkEffect.Type.BURST)
                .withColor(Color.RED)
                .withFade(Color.ORANGE)
                .build()
            meta.addEffect(effect)
            meta.power = 2
            firework.fireworkMeta = meta
        }
    }, 0L)*/
