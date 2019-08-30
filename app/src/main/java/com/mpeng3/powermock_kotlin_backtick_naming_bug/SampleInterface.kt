package com.mpeng3.powermock_kotlin_backtick_naming_bug

import io.reactivex.Observable
import io.reactivex.functions.Consumer

interface SampleInterface {
    fun startDiscovery(consumer: Consumer<Observable<SampleEvent>>)
}
