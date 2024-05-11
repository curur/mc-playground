package com.github.curur.mcplayground.profile

import com.github.curur.mcplayground.getNMS
import org.bukkit.entity.Player

interface PlayerProfileManager {

    companion object {
        fun newInstance(): PlayerProfileManager? {
            return getNMS(PlayerProfileManager::class.java).getDeclaredConstructor().newInstance()
        }
    }

    fun changeName(player: Player, newName: String): Boolean
    fun resetName(player: Player): Boolean

    fun changeSkin(player: Player, pair: Pair<String, String>): Boolean
    fun changeSkin(player: Player, newSkinOwner: String): Boolean
    fun resetSkin(player: Player): Boolean

}