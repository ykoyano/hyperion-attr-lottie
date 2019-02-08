package com.github.ykoyano.hyperion.plugin.attr.lottie.walkthrough

import androidx.fragment.app.Fragment
import com.cleveroad.slidingtutorial.TutorialPageProvider


class CustomTutorialPageProvider : TutorialPageProvider<Fragment> {

    override fun providePage(position: Int): Fragment {
        var position = position
        position %= ACTUAL_PAGES_COUNT
        when (position) {
            0 -> {
                return CustomPageSupportFirstFragment()
            }
            1 -> {
                return CustomPageSupportSecondFragment()
            }
            2 -> {
                return CustomPageSupportThreeFragment()
            }
            else -> {
                throw IllegalArgumentException("Unknown position: $position")
            }
        }
    }

    companion object {
        private const val ACTUAL_PAGES_COUNT = 3
    }
}