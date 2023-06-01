package lt.paulius.androidtopics.mainactivity

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import lt.paulius.androidtopics.repository.Item
import lt.paulius.androidtopics.repository.ItemRepository

class MainActivityViewModel : ViewModel() {

    private val _uiState = MutableStateFlow(MainActivityUIState())
    val uiState = _uiState.asStateFlow()

    private val _isDeletedUiState = MutableSharedFlow<MessageDisplayUIState>(0)
    val isDeletedUiState = _isDeletedUiState

    fun fetchItems() {
        viewModelScope.launch(Dispatchers.IO) {
            _uiState.update {
                it.copy(isLoading = true, isListVisible = false)
            }

            if (_uiState.value.items.isEmpty()) {
                ItemRepository.instance.addDummyListOfItems()
            }

            delay((1000..4000).random().toLong())

            _uiState.update {
                it.copy(
                    items = ItemRepository.instance.getItems(),
                    isLoading = false,
                    isListVisible = true
                )
            }
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            _isDeletedUiState.emit(
                MessageDisplayUIState(
                    item = item,
                    isDeleted = ItemRepository.instance.deleteItem(item)
                )
            )

        }
    }
}
