package lt.paulius.androidtopics

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import lt.paulius.androidtopics.databinding.ActivityMainBinding
import timber.log.Timber

class MainActivity : ActivityLifecycles() {

    //    private lateinit var clickButton: Button
    private lateinit var adapter: CustomAdapter

    //    private lateinit var itemListView: ListView
    private var itemIndex = -1
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        clickButton = findViewById(R.id.btnClick)
//        itemListView = findViewById(R.id.itemListView)


        val items = mutableListOf<Item>()
        generateListOfItems(items)

        setUpListView()
        updateAdapter(items)

        setClickOpenItemDetails()
        setUpOnClickListener()

    }


    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX, itemIndex)
        timber("onSaveInstanceState \nindex value is $itemIndex")
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        itemIndex = savedInstanceState.getInt(MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX)
        timber("onRestoreInstanceState \nindex value is $itemIndex")
    }

    private fun generateListOfItems(items: MutableList<Item>) {
        for (item in 1..10) {
            items.add(
                Item(
                    item,
                    "Text01_%04x".format(item),
                    "Text02_%06x".format(item)
                )
            )
        }

    }

    private fun setUpListView() {
        adapter = CustomAdapter(this)
//        itemListView.adapter = adapter
        binding.itemListView.adapter = adapter
    }

    private fun updateAdapter(items: MutableList<Item>) {
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02")
        )
    }

    private fun setUpOnClickListener() {
        binding.btnClick.setOnClickListener {
//            startActivity(Intent(this, SecondActivity::class.java))
            startActivityForResult.launch(Intent(this, SecondActivity::class.java))
        }
    }

    private fun setClickOpenItemDetails() {
        binding.itemListView.setOnItemClickListener { adapterView, view, position, l ->
            val item: Item = adapterView.getItemAtPosition(position) as Item

            itemIndex = position

            val itemIntent = Intent(this, SecondActivity::class.java)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_ID, item.id)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT01, item.text01)
            itemIntent.putExtra(MAIN_ACTIVITY_ITEM_TEXT02, item.text02)

//            startActivity(itemIntent)
            startActivityForResult.launch(itemIntent)
        }
    }

    private val startActivityForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            when (result.resultCode) {
                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_NEW -> {
                    val item = Item(
                        id = 111,
                        text01 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        text02 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT02) ?: "",
                    )

                    adapter.add(item)
                }

                SecondActivity.SECOND_ACTIVITY_ITEM_INTENT_RETURN_UPDATE -> {
                    val item = Item(
                        id = result.data
                            ?.getIntExtra(SecondActivity.SECOND_ACTIVITY_ITEM_ID, 0) ?: 0,
                        text01 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT01) ?: "",
                        text02 = result.data
                            ?.getStringExtra(SecondActivity.SECOND_ACTIVITY_ITEM_TEXT02) ?: "",
                    )

                    if (itemIndex >= 0) {
                        adapter.update(itemIndex, item)
                    }
                }
            }
        }

    companion object {
        const val MAIN_ACTIVITY_ITEM_ID = "lt.paulius.androidtopics_item_id"
        const val MAIN_ACTIVITY_ITEM_TEXT01 = "lt.paulius.androidtopics_item_text01"
        const val MAIN_ACTIVITY_ITEM_TEXT02 = "lt.paulius.androidtopics_item_text02"
        const val MAIN_ACTIVITY_SAVE_INSTANCE_STATE_ITEM_INDEX =
            "lt.paulius.androidtopics_save_instance_state_item_index"
    }
}