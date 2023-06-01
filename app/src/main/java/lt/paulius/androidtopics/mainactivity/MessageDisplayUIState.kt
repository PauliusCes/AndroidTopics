package lt.paulius.androidtopics.mainactivity

import lt.paulius.androidtopics.repository.Item

data class MessageDisplayUIState(
    val item: Item = Item(-1, "", ""),
    val isDeleted: Boolean = false
)
