package lt.paulius.androidtopics

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    lateinit var clickButton: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton = findViewById(R.id.btnClick)

        setUpOnClickListener()
        timber("onCreate")

    }

    override fun onStart() {
        super.onStart()
        timber("onStart")
    }

    override fun onResume() {
        super.onResume()
        timber("onResume")
    }

    override fun onStop() {
        super.onStop()
        timber("onStop")
    }

    override fun onPause() {
        super.onPause()
        timber("onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        timber("onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        timber("onRestart")
    }


    fun setUpOnClickListener() {
        clickButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
    fun timber(text: String) {

        Timber.i(
            """
                *******************
                * ${this.localClassName}
                * $text
                *******************
            """.trimIndent()
        )
    }
}