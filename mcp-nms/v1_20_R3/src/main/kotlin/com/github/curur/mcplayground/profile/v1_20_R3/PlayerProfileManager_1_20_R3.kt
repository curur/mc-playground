package com.github.curur.mcplayground.profile.v1_20_R3

import com.github.curur.mcplayground.profile.PlayerProfileManager
import com.mojang.authlib.properties.Property
import org.bukkit.Bukkit
import org.bukkit.craftbukkit.v1_20_R3.entity.CraftPlayer
import org.bukkit.entity.Player
import java.util.*

class PlayerProfileManager_1_20_R3: PlayerProfileManager {

    private val playersOriginalName = HashMap<UUID, String>()
    private val playersOriginalSkin = HashMap<UUID, Pair<String, String>>()

    override fun changeName(player: Player, newName: String): Boolean {
        if(newName == player.name) return false

        if(!playersOriginalName.containsKey(player.uniqueId)) playersOriginalName.put(player.uniqueId, player.name)

        changeProperty(player, "name", Property("name", newName))

        return true
    }

    override fun resetName(player: Player): Boolean {
        if(!playersOriginalName.containsKey(player.uniqueId)) return false

        return true
    }

    override fun changeSkin(player: Player, pair: Pair<String, String>): Boolean {
        TODO("Not yet implemented")
    }

    override fun changeSkin(player: Player, newSkinOwner: String): Boolean {
        TODO("Not yet implemented")
    }

    override fun resetSkin(player: Player): Boolean {
        TODO("Not yet implemented")
    }

    private fun changeProperty(player: Player, key: String, property: Property) {

        val profile = (player as CraftPlayer).handle.getGameProfile()

        profile.properties.removeAll(key)
        profile.properties.put(key, property)

        Bukkit.getOnlinePlayers().forEach {
            it.hidePlayer(player)
            it.showPlayer(player)
        }
        println("dskaljfkljldkfjaljlewjflawejl;f")
    }
}