package com.github.ykoyano.hyperion.plugin.attr.lottie.walkthrough

import com.cleveroad.slidingtutorial.TransformItem
import com.cleveroad.slidingtutorial.Direction
import com.cleveroad.slidingtutorial.PageSupportFragment
import com.github.ykoyano.hyperion.plugin.attr.lottie.R

class CustomPageSupportThreeFragment : PageSupportFragment() {

    override fun getLayoutResId(): Int {
        return R.layout.fragment_waklthrough_three
    }

    override fun getTransformItems(): Array<TransformItem> {
        return arrayOf(TransformItem.create(R.id.animationView, Direction.LEFT_TO_RIGHT, 0.2f))
    }
}
