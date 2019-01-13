package mydeacy.antispam

import cn.nukkit.plugin.PluginBase
import mydeacy.antispam.task.ChatTask

class AntiSpam : PluginBase() {

    val flag: MutableMap<String, Boolean> = mutableMapOf()
    val count: MutableMap<String, Int> = mutableMapOf()

    override fun onEnable() {
        server.pluginManager.registerEvents(EventListener(this), this)
        saveDefaultConfig()
        reloadConfig()
    }

    fun isSpam(name: String): Boolean = count[name]!! >=  config.getInt("count")

    fun muteTask(name: String) {
        server.scheduler.scheduleDelayedTask(ChatTask(name, fun() {
            flag.remove(name)
            val player = server.getPlayer(name)
            if (player != null) player.sendMessage("§a >> Chat restriction canceled!!")
        }), config.getInt("mute_time") * 20)
    }

    fun chatTask(name: String){
        server.scheduler.scheduleDelayedTask(ChatTask(name, fun(){count[name] = 0}), config.getInt("interval") * 20)
    }

}