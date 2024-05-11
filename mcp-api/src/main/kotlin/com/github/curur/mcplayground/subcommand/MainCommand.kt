package com.github.curur.mcplayground.subcommand

import org.bukkit.command.Command
import org.bukkit.command.CommandSender
import org.bukkit.command.TabExecutor
import org.bukkit.plugin.java.JavaPlugin

open class MainCommand (val label: String): TabExecutor {

    private var subCommands: ArrayList<SubCommand>
    protected lateinit var plugin: JavaPlugin

    init {
        subCommands = ArrayList()
        onInitialize()
    }

    fun register(plugin: JavaPlugin) {
        plugin.getCommand(label)?.apply {
            setExecutor(this@MainCommand)
            tabCompleter = this@MainCommand
        }
        this.plugin = plugin
    }

    protected open fun onInitialize() {}

    protected open fun execute(sender: CommandSender, label: String, args: Array<out String>?) {}

    protected fun attach(subCommand: SubCommand) {
        subCommands.add(subCommand)
    }

    override fun onTabComplete(
        sender: CommandSender,
        command: Command,
        label: String,
        args: Array<out String>?
    ): MutableList<String>? {
        val list = ArrayList<String>()

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

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        execute(sender, label, args)
        subCommands.forEach {
            if(args?.isEmpty() != true && args?.get(0) == it.name) {
                val newArgs = ArrayList<String>(args.size - 1)
                args.forEachIndexed { index, s ->
                    if (index != 0) newArgs.add(s)
                }
                it.onCommand(sender, newArgs)
            }
        }
        return true
    }
}