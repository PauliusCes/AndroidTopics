package lt.paulius.androidtopics.common

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import lt.paulius.androidtopics.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}