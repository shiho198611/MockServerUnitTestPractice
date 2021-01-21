package com.practice.mockhttputpractice

import io.reactivex.rxjava3.plugins.RxJavaPlugins
import io.reactivex.rxjava3.schedulers.Schedulers
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class RxJavaTestSchedulerRule : TestRule {

    override fun apply(base: Statement, description: Description): Statement {
        return object : Statement() {
            @Throws(Throwable::class)
            override fun evaluate() {
                RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

                RxJavaPlugins.setNewThreadSchedulerHandler { Schedulers.trampoline() }

                RxJavaPlugins.setComputationSchedulerHandler { Schedulers.trampoline() }

                // RxAndroid use
//                RxAndroidPlugins.setMainThreadSchedulerHandler { Schedulers.trampoline() }

                try {
                    base.evaluate()
                } finally {
                    RxJavaPlugins.reset()
                    // RxAndroid use
//                    RxAndroidPlugins.reset()
                }
            }
        }
    }

}