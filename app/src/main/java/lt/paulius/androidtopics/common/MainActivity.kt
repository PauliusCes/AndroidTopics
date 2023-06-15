package lt.paulius.androidtopics.common

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import lt.paulius.androidtopics.R
import lt.paulius.androidtopics.first_fragment.FirstFragment
import lt.paulius.androidtopics.second_fragment.SecondFragment

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setCurrentFragment()

    }

    fun openSecondFragment() {
        setCurrentFragment(FirstFragment.newInstance(), FirstFragment.TAG)
    }


    fun setCurrentFragment(fragment: Fragment, tag: String, addBackStack: Boolean = false) {
        supportFragmentManager.commit {
            replace(
                R.id.fragmentContainerView,
                fragment,
                tag
            )
            setReorderingAllowed(true)
        }
    }
}