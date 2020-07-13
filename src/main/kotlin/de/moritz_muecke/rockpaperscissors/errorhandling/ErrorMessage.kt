package de.moritz_muecke.rockpaperscissors.errorhandling

import arrow.core.None
import arrow.core.Option

data class ErrorMessage(val message: String, val cause: Option<Exception> = None)
