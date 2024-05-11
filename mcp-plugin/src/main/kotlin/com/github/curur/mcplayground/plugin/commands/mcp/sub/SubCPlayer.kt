package com.github.curur.mcplayground.plugin.commands.mcp.sub

import com.github.curur.mcplayground.plugin.commands.mcp.sub.player.SubCPlayerProfile
import com.github.curur.mcplayground.subcommand.SubCommand
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class SubCPlayer : SubCommand() {

    init {
        super.name = "player"
        attach(SubCPlayerProfile())
    }
    /*
        /mcp player[1]
     */


    override fun execute(sender: CommandSender, args: List<String>) {
        if(args.size == 1) {
            sender.sendMessage("this command needs more arguments to execute.")
        }
    }

    override fun tabComplete(sender: CommandSender, args: Array<out String>?): MutableList<String>? {
        if(args?.size == 1) return mutableListOf("player")
        else return null
    }
}