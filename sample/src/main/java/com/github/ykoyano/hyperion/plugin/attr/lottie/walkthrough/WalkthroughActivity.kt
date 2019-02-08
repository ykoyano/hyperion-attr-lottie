package com.github.ykoyano.hyperion.plugin.attr.lottie.walkthrough

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.cleveroad.slidingtutorial.TutorialSupportFragment
import com.github.ykoyano.hyperion.plugin.attr.lottie.R

class WalkthroughActivity : AppCompatActivity() {

    private val mTutorialPageProvider = CustomTutorialPageProvider()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_walkthrough)

        val tutorialOptions = TutorialSupportFragment.newTutorialOptionsBuilder(this)
                .setPagesCount(3)
                .setTutorialPageProvider(mTutorialPageProvider)
                .build()

        val tutorialSupportFragment = TutorialSupportFragment.newInstance(tutorialOptions)
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.content, tutorialSupportFragment)
                .commit()
    }

    companion object {
        fun intent(context: Context): Intent {
            return Intent(context, WalkthroughActivity::class.java)
        }
    }
}
