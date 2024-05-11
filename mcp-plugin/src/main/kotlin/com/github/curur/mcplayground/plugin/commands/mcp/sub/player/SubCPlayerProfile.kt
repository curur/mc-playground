package com.github.curur.mcplayground.plugin.commands.mcp.sub.player

import com.github.curur.mcplayground.plugin.Main
import com.github.curur.mcplayground.profile.PlayerProfileManager
import com.github.curur.mcplayground.subcommand.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player
import org.bukkit.plugin.java.JavaPlugin

class SubCPlayerProfile : SubCommand() {


    /*
        /mcp player profile[0] name reset|change (value)
                               skin
     */

    init {
        super.name = "profile"
    }

    private val playerProfileManager = JavaPlugin.getPlugin(Main::class.java).playerProfileManager

    override fun execute(sender: CommandSender, args: List<String>) {
        if(playerProfileManager == null) {
            sender.sendMessage("this command can't used on current server version")
            return
        }
        if(sender !is Player) return
        if(args.size == 1) {
            sender.sendMessage("this command needs more arguments to execute.")
            return
        }

        if(args[0] != "name" && args[0] != "skin") return

        if(args.size == 2) {
            if(args[1] != "reset") return
            playerProfileManager.resetName(sender)
        } else if(args.size == 3) {
            if(args[1] != "change") return
            if(args[0] == "name") playerProfileManager.changeName(sender, args[2])
            if(args[0] == "skin") playerProfileManager.changeSkin(sender, args[2])
        }
    }

    override fun tabComplete(sender: CommandSender, args: Array<out String>?): MutableList<String>? {
        return when(args?.size) {
            1 -> mutableListOf("name", "skin")
            2 -> mutableListOf("reset", "change")
            else -> null
        }
    }
}