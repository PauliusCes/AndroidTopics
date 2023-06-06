package lt.paulius.androidtopics.common

import android.os.Bundle
import lt.paulius.androidtopics.R

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}