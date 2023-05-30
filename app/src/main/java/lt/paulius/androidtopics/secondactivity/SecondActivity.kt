package lt.paulius.androidtopics.secondactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.paulius.androidtopics.ActivityLifecycles
import lt.paulius.androidtopics.R
import lt.paulius.androidtopics.databinding.ActivitySecondBinding
import lt.paulius.androidtopics.getExtraFromParcelable
import lt.paulius.androidtopics.mainactivity.MainActivity
import lt.paulius.androidtopics.repository.Item

class SecondActivity : ActivityLifecycles() {

    private lateinit var binding: ActivitySecondBinding
    private val activityViewModel: SecondActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_second)
        binding.secondActivity = this

        activityViewModel.itemLiveData.observe(
            this,
            Observer { item ->
                binding.item = item
            }

        )

        activityViewModel.fetchItem(getIntentExtra())

        // comment just for merging purposes: commit02
    }

    private fun getIntentExtra() =
        intent.getIntExtra(MainActivity.MAIN_ACTIVITY_ITEM_INTENT_ID, -1)

    fun onClickCloseButton(view: View) {
        finish()
    }

    fun onClickSaveButton() {
        activityViewModel.saveItem(binding.item as Item)
        finish()
    }
}
