package com.kschltz.unleash

import no.finn.unleash.DefaultUnleash
import no.finn.unleash.event.Log4JSubscriber
import no.finn.unleash.util.UnleashConfig


class UnleashClient {

    companion object {
       private val instance = DefaultUnleash(conf())

        operator fun invoke(feature: String): Boolean {
            return instance.isEnabled(feature)
        }

    }

}


private fun conf(): UnleashConfig = UnleashConfig.builder()
    .appName("feature-testing")
    .sendMetricsInterval(1)
    .instanceId("sbrabbles")
    .fetchTogglesInterval(5)
    .subscriber(Log4JSubscriber())
    .unleashAPI("http://localhost:4242/api")
    .build()








