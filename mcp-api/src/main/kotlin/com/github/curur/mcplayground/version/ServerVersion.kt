package com.github.curur.mcplayground.version

import org.bukkit.plugin.java.JavaPlugin

//v1_20_R3
var server_version: String = ""
    private set

fun JavaPlugin.serverVersion(v: String) {
    server_version = v
}