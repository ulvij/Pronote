package com.ulvijabbarli.pronote.data.tools

import androidx.navigation.NavDirections
import androidx.navigation.Navigator

sealed class NavigationCommand {
    data class To(val directions: NavDirections, val extras: Navigator.Extras? = null) :
        NavigationCommand()

    object Back : NavigationCommand()
    data class BackTo(val destinationId: Int) : NavigationCommand()
    object ToRoot : NavigationCommand()
}