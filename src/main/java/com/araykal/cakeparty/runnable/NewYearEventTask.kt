package com.araykal.cakeparty.runnable

import com.araykal.cakeparty.spawnFireworkAroundLocation
import com.araykal.cakeparty.startFireworkTask
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.scheduler.BukkitRunnable
import java.awt.Color
import java.util.*

class NewYearEventTask : BukkitRunnable() {

    override fun run() {
        if (isNewYear()){
            startFireworkTask(Location(Bukkit.getWorld("world"),244.0,115.0,160.0))
        }
    }

    fun isNewYear(): Boolean {
        val now = Calendar.getInstance()
        return now.get(Calendar.MONTH) == Calendar.JANUARY &&
                now.get(Calendar.DAY_OF_MONTH) == 1 &&
                now.get(Calendar.HOUR_OF_DAY) == 0 &&
                now.get(Calendar.MINUTE) == 0
    }
}
