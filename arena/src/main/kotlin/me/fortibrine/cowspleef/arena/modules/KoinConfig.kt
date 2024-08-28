package me.fortibrine.cowspleef.arena.modules

import me.fortibrine.cowspleef.api.data.PlayerManager
import me.fortibrine.cowspleef.common.data.mariadb.MariadbPlayerManager
import org.koin.core.annotation.ComponentScan
import org.koin.core.annotation.Module
import org.koin.core.annotation.Single

@Module
@ComponentScan("me.fortibrine.cowspleef.arena")
class KoinConfig {

    @Single
    fun playerManager(): PlayerManager =
        MariadbPlayerManager()

}
