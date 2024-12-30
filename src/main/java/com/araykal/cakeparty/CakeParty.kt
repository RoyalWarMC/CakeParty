package com.araykal.cakeparty

import com.araykal.cakeparty.command.CakeCommand
import com.araykal.cakeparty.listener.CakeListener
import com.araykal.cakeparty.runnable.NewYearEventTask
import dev.rollczi.litecommands.bukkit.LiteBukkitFactory
import dev.rollczi.litecommands.bukkit.LiteBukkitMessages
import dev.rollczi.litecommands.meta.Meta
import dev.rollczi.litecommands.validator.ValidatorScope
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Entity
import org.bukkit.plugin.java.JavaPlugin

class CakeParty : JavaPlugin() {
    private var allCake = mutableListOf<Location>()

    companion object {
        lateinit var instance: CakeParty
            private set
    }

    override fun onEnable() {
        instance = this;
        loadCommands()
        NewYearEventTask().runTaskTimer(this, 0L, 20L)

        Bukkit.getPluginManager().registerEvents(CakeListener(), this)
        Bukkit.getScheduler().runTaskTimer(this, Runnable {
            setNightTime()
        }, 0L, 20L)
    }

    override fun onDisable() {

    }

    private fun setNightTime() {
        for (world in Bukkit.getWorlds()) {
            world.time = 13000
        }
    }

    private fun loadCommands() {
        LiteBukkitFactory.builder()
            .commands(
                CakeCommand()
            )
            .settings {
                it.nativePermissions(true)
            }
            .message(LiteBukkitMessages.INVALID_USAGE) { inv, ctx ->
                return@message "§c用法: ".plus(buildString {
                    if (ctx.schematic.isOnlyFirst) {
                        append(ctx.schematic.first())
                    } else {
                        appendLine()
                        ctx.schematic.all().forEach {
                            appendLine(" §c$it")
                        }
                    }
                })
            }
            .message(LiteBukkitMessages.INVALID_NUMBER) { input ->
                "§c错误的数字: $input"
            }
            .message(LiteBukkitMessages.MISSING_PERMISSIONS, "Unknown command. Type \"/help\" for help.")
            .message(LiteBukkitMessages.PLAYER_NOT_FOUND) { input ->
                "§c未找到名为 $input 的玩家"
            }
            .message(LiteBukkitMessages.PLAYER_ONLY, "§cOnly Player Use").build()
    }

    fun getCakeEntities() = allCake
}