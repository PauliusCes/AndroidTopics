package lt.paulius.androidtopics.common

import android.os.Bundle
import androidx.fragment.app.commit
import lt.paulius.androidtopics.R
import lt.paulius.androidtopics.first_fragment.FirstFragment

class MainActivity : ActivityLifecyclesPresentation() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        openFragment()

    }

    private fun openFragment() {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                FirstFragment.newInstance(),
                "first_fragment"
            )
            setReorderingAllowed(true)
        }

//        supportFragmentManager.beginTransaction()
//            .replace(
//                R.id.fragmentContainerView,
//                FirstFragment.newInstance(),
//                "first_fragment")
//            .commit()
    }
}
