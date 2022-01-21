/**
 * Copyright (c) 2019-2022 Nino
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

rootProject.name = "Nino"

// Slash commands implementation for Nino
include(":bot:slash-commands")

// Punishments core + utilities
include(":bot:punishments")

// Automod core + utilities
include(":bot:automod")

// Text-based commands
include(":bot:commands")

// Database models + transaction API
include(":bot:database")

// Kotlin client for timeouts microservice
include(":bot:timeouts")

// Markup language for custom messages
include(":bot:markup")

// Common utilities + extensions
include(":bot:commons")

// Prometheus metrics registry
include(":bot:metrics")

// Core components that ties everything in
include(":bot:core")

// Bot API (+ Slash Commands impl)
include(":bot:api")

// Main bot directory
include(":bot")

// Gateway
include(":gateway")

// Cluster Operator Client for Nino
include(":gateway:cluster-operator")
