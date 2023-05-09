package lt.paulius.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : ActivityLifecycles() {

    lateinit var clickButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton = findViewById(R.id.btnClick)

        setUpOnClickListener()

    }

    fun setUpOnClickListener() {
        clickButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}