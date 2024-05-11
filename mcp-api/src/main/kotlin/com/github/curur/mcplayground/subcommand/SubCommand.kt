package com.github.curur.mcplayground.subcommand

import org.bukkit.command.CommandSender
import org.bukkit.plugin.java.JavaPlugin

abstract class SubCommand {

    lateinit var name: String
        protected set

    private val subCommands = ArrayList<SubCommand>()

    fun onCommand(sender: CommandSender, args: List<String>) {
        execute(sender, args)
        subCommands.forEach {
            if(args.isNotEmpty() && args[0] == it.name) {
                val newArgs = args.toMutableList().apply { removeAt(0) }
                if(args.isNotEmpty()) it.onCommand(sender, newArgs)
            }
        }
    }

    fun onTabComplete(sender: CommandSender, args: Array<out String>?): MutableList<String>? {
        val list = ArrayList<String>()

        if(subCommands.isEmpty()) return tabComplete(sender, args)
//        if(args?.get(0) != name) return null

        if(args?.size == 1) {
            return subCommands.map { it.name }.toMutableList()
        } else {
            val newArgs = args?.clone()?.toMutableList()?.apply { removeAt(0) }
            for (subCommand in subCommands) {
                val tab = subCommand.onTabComplete(sender, newArgs?.toTypedArray()) ?: continue
                list.addAll(tab)
            }
        }
        if(list.isEmpty()) return null
        return list
    }

    abstract fun execute(sender: CommandSender, args: List<String>)

    abstract fun tabComplete(sender: CommandSender, args: Array<out String>?): MutableList<String>?

    protected fun attach(clazz: SubCommand) {
        subCommands.add(clazz)
    }
}