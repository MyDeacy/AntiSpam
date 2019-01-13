package mydeacy.antispam.task

import cn.nukkit.scheduler.Task

class ChatTask : Task {

    private var name: String
    private var func: ()->Unit
    constructor(_name: String, _func: ()->Unit){
        name = _name
        func = _func
    }

    override fun onRun(p0: Int) {
        func()
    }
}