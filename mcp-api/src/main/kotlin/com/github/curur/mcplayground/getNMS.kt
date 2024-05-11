package com.github.curur.mcplayground

import com.github.curur.mcplayground.version.server_version

class getNMS {
}

fun <T> getNMS(clazz: Class<T>): Class<T> {
    // com.github.curur.mcplayground.profile.PlayerProfileManager
    // com.github.curur.mcplayground.profile.v1_20_R3.PlayerProfileManager_1_20_R3
    // package + version
    // class + _version - v
    val packageName = clazz.packageName.plus(".$server_version")
    val className = clazz.simpleName.plus("_${server_version.substring(1)}")

    return Class.forName("$packageName.$className") as Class<T>
}