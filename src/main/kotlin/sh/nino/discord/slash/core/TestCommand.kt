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

package sh.nino.discord.slash.core

import dev.kord.common.entity.ApplicationCommandOptionType
import sh.nino.discord.core.slash.builders.slashCommand

val testSubCommand = slashCommand {
    description = "blep fluff"
    name = "blep"

    subcommand("repeat", "bleps x amount of times") {
        required = false
        option {
            description = "How many times we need to blep!"
            required = false
            name = "amount"
            type = ApplicationCommandOptionType.Integer
        }

        run { msg ->
            val times = msg.options["amount"]!!.value as Long
            msg.reply("blep".repeat(times.toInt()))
        }
    }

    onlyIn(743698927039283201L)
    run { msg ->
        msg.reply("owo")
    }
}