package lt.paulius.androidtopics

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import timber.log.Timber

class MainActivity : ActivityLifecycles() {

    lateinit var clickButton: Button
    lateinit var adapter: CustomAdapter
    lateinit var itemListView: ListView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        clickButton = findViewById(R.id.btnClick)
        itemListView = findViewById(R.id.itemListView)

        setUpOnClickListener()

        val items = mutableListOf<Item>()
        generateListOfItems(items)

        adapter = CustomAdapter(this)
        adapter.add(items)
        adapter.add(Item(101, "text01", "text02"))
        adapter.add(
            Item(102, "text01", "text02"),
            Item(103, "text01", "text02"),
            Item(104, "text01", "text02"),
            Item(105, "text01", "text02")
        )

        itemListView.adapter = adapter


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

    private fun setUpOnClickListener() {
        clickButton.setOnClickListener {
            startActivity(Intent(this, SecondActivity::class.java))
        }
    }
}