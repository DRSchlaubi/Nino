/**
 * Copyright (c) 2019-2021 Nino
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package sh.nino.discord

import com.charleskorn.kaml.Yaml
import dev.kord.cache.map.MapLikeCollection
import dev.kord.cache.map.internal.MapEntryCache
import dev.kord.core.Kord
import dev.kord.gateway.PrivilegedIntent
import kotlinx.coroutines.runBlocking
import org.koin.core.context.GlobalContext
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import sh.nino.discord.commands.commandsModule
import sh.nino.discord.core.ktor.ktorModule
import sh.nino.discord.data.Config
import sh.nino.discord.extensions.inject
import sh.nino.discord.kotlin.logging
import sh.nino.discord.modules.ninoModule
import sh.nino.discord.slash.slashCommandsModule
import java.io.File
import kotlin.system.exitProcess

object Bootstrap {
    private lateinit var bot: NinoBot
    private val logger by logging<Bootstrap>()

    @OptIn(PrivilegedIntent::class)
    @JvmStatic
    fun main(args: Array<String>) {
        Thread.currentThread().name = "Nino-MainThread"
        logger.info("* Initializing Koin...")

        val file = File("./config.yml")
        val config = Yaml.default.decodeFromString(Config.serializer(), file.readText())

        val kord = runBlocking {
            Kord(config.token) {
                enableShutdownHook = false
                cache {
                    // cache members
                    members { cache, description ->
                        MapEntryCache(cache, description, MapLikeCollection.concurrentHashMap())
                    }

                    // cache users
                    users { cache, description ->
                        MapEntryCache(cache, description, MapLikeCollection.concurrentHashMap())
                    }
                }
            }
        }

        startKoin {
            modules(
                globalModule,
                ninoModule,
                *commandsModule.toTypedArray(),
                ktorModule,
                slashCommandsModule,
                module {
                    single {
                        NinoBot()
                    }

                    single {
                        config
                    }

                    single {
                        kord
                    }
                }
            )
        }

        logger.info("* Initialized Koin, now starting Nino...")
        bot = GlobalContext.inject()
        bot.addShutdownHook()

        runBlocking {
            try {
                bot.launch()
            } catch (e: Exception) {
                logger.error("A runtime exception has occurred while bootstrapping Nino:", e)
                exitProcess(1)
            }
        }
    }
}