package lt.paulius.androidtopics.mainactivity

import lt.paulius.androidtopics.repository.Item

data class MainActivityUIState(
    val items: List<Item> = mutableListOf(),
    val isLoading: Boolean = false,
    val isListVisible: Boolean = true
)
