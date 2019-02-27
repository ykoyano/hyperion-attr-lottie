package com.github.ykoyano.hyperion.plugin.attr.lottie

import android.Manifest
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
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

        if (BuildConfig.DEBUG &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CAMERA), 1234)
        }
    }
}
