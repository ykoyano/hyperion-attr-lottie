package com.github.ykoyano.hyperion.plugin.attr.lottie

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import com.github.ykoyano.hyperion.plugin.attr.lottie.walkthrough.WalkthroughActivity

class MainActivity : AppCompatActivity() {

    private lateinit var listButton: AppCompatButton
    private lateinit var walkthroughButton: AppCompatButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        listButton = findViewById(R.id.list_button)
        walkthroughButton = findViewById(R.id.walkthrough_button)

        listButton.setOnClickListener {
            startActivity(ListActivity.intent(this))
        }

        walkthroughButton.setOnClickListener {
            startActivity(WalkthroughActivity.intent(this))
        }
    }
}
