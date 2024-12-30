package com.araykal.cakeparty.listener

import com.araykal.cakeparty.CakeParty
import com.araykal.cakeparty.addPoints
import com.araykal.cakeparty.send

import org.bukkit.Material
import org.bukkit.Sound
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.block.Action
import org.bukkit.event.player.PlayerInteractEvent
import kotlin.random.Random

class CakeListener : Listener {
    var eatCakeNumber = mutableMapOf<String, Int>()


    @EventHandler
    fun onPlayerInteract(event: PlayerInteractEvent) {
        if (event.action == Action.RIGHT_CLICK_BLOCK && event.clickedBlock!!.type == Material.CAKE) {
            val player = event.player
            val itemInHand = player.inventory.itemInMainHand
            if (itemInHand.type == Material.AIR) {
                if (eatCakeNumber.get(player.name) == null) {
                    eatCakeNumber.put(player.name, 0)
                }
                if (eatCakeNumber.get(player.name)!! >= 11) {
                    player.send("§c您已经吃了11块蛋糕，吃不下了！！！")
                    event.isCancelled = true
                    return
                }
                val randomPoints = Random.nextInt(5, 11)
                event.clickedBlock!!.type = Material.AIR;
                player.player
                player.foodLevel = 20
                player.send("&b你吃掉了一块蛋糕,获得$randomPoints 点券")
                player.addPoints(randomPoints)
                eatCakeNumber.put(player.name, eatCakeNumber.get(player.name)!! + 1)
                player.playSound(player.location, Sound.ENTITY_GENERIC_EAT, 1.0f, 1.0f)
            } else {
                player.send("&c请空手点击蛋糕！！！")
            }
        }
    }

}