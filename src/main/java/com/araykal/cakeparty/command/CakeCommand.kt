package com.araykal.cakeparty.command

import com.araykal.cakeparty.*
import dev.rollczi.litecommands.annotations.command.Command
import dev.rollczi.litecommands.annotations.context.Context
import dev.rollczi.litecommands.annotations.execute.Execute
import dev.rollczi.litecommands.annotations.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.Material
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler

@Command(name = "cakeParty")
@Permission("cakeParty.admin")
class CakeCommand {
    @Execute(name = "spawn")
    fun crateCake(@Context player: Player) {
        val playerLoc: Location = player.location
        for (x in -25..24) {
            for (z in -25..24) {
                playerLoc.clone().add(x.toDouble(), 0.0, z.toDouble()).block.type = Material.CAKE
                CakeParty.instance.getCakeEntities().add(playerLoc.clone().add(x.toDouble(), 0.0, z.toDouble()))
            }
        }
        player.send("§a执行成功")
    }

    @Execute(name = "firework")
    fun spawn(@Context player: Player) {
        spawnFireworkAroundLocation(player.location)
        player.send("§a执行成功")
    }

    @Execute(name = "clear")
    fun clearCake() {
        CakeParty.instance.getCakeEntities().forEach { it.block.type = Material.AIR }
    }
    private fun setNightTime() {
        for (world in Bukkit.getWorlds()) {
            world.time = 13000
        }
    }

}