package com.github.curur.mcplayground.plugin.commands.mcp

import com.github.curur.mcplayground.plugin.commands.mcp.sub.SubCPlayer
import com.github.curur.mcplayground.subcommand.MainCommand
import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

class McPlaygroundMainCommand : MainCommand("mcplayground") {

    /*

        /mcp player profile

     */

    override fun onInitialize() {
        attach(SubCPlayer())
    }

    override fun execute(sender: CommandSender, label: String, args: Array<out String>?) {
        if(!sender.isOp) return

        if(args?.get(0) == "help") {
            sender.sendMessage("mc playground 플러그인의 sub command 사용예시 커맨드")
            sender.sendMessage("/mc help")
        }
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        return super.onTabComplete(sender, command, label, args)?.apply {
            when(args?.size) {
                1 -> {
                    add("help")
                }
            }
        }
    }

}