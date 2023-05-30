package lt.paulius.androidtopics.mainactivity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import lt.paulius.androidtopics.ActivityLifecycles
import lt.paulius.androidtopics.R
import lt.paulius.androidtopics.secondactivity.SecondActivity
import lt.paulius.androidtopics.databinding.ActivityMainBinding
import lt.paulius.androidtopics.repository.Item

class MainActivity : ActivityLifecycles() {

    private lateinit var adapter: CustomAdapter
    private lateinit var binding: ActivityMainBinding
    private val activityViewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.mainActivity = this
        binding.viewModel = activityViewModel
        binding.lifecycleOwner = this

        setUpListView()

        setupObservables()

        setClickOpenItemDetails()
    }

    override fun onResume() {
        super.onResume()
        activityViewModel.fetchItems()
    }

    fun setClickOpenSecondActivity(view: View) {
        startActivity(Intent(this, SecondActivity::class.java))
    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
        binding.itemListView.adapter = adapter
    }

    private fun setupObservables() {
        activityViewModel.itemsLiveData.observe(
            this,
            Observer { listOfItems ->
                adapter.add(listOfItems)
            }
        )
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            val intent = Intent(this, SecondActivity::class.java)
            intent.putExtra(MAIN_ACTIVITY_ITEM_INTENT_ID, item.id)

            startActivity(intent)
        }
    }

    companion object {
        const val MAIN_ACTIVITY_ITEM_INTENT_ID =
            "package lt.paulius.androidtopics_item_intent_object"
    }

}