package com.example.mytestapp.compose.ui.chapter2

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.NavKey
import androidx.navigation3.runtime.entry
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberNavBackStack
import androidx.navigation3.runtime.rememberSavedStateNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import androidx.navigation3.ui.rememberSceneSetupNavEntryDecorator
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.serialization.Serializable
import javax.inject.Inject

@Composable
fun Chapter2NavHost() {
    val backStack = rememberNavBackStack(Chapter2Screens.TestA)

    NavDisplay(
        backStack = backStack,
        onBack = { backStack.removeLastOrNull() },
        entryDecorators = listOf(
            // Add the default decorators for managing scenes and saving state
            rememberSceneSetupNavEntryDecorator(),
            rememberSavedStateNavEntryDecorator(),
            // Then add the view model store decorator
            rememberViewModelStoreNavEntryDecorator()
        ),
        entryProvider = entryProvider {
            entry<Chapter2Screens.TestA> {
                AScreen(backStack)
            }
            entry<Chapter2Screens.TestB> {
                BScreen(backStack, it.data)
            }
        }
    )
}

sealed class Chapter2Screens(): NavKey {
    @Serializable
    object TestA : Chapter2Screens()
    @Serializable
    data class TestB(val data: String) : Chapter2Screens()
}

@Composable
fun AScreen(
    backStack: SnapshotStateList<NavKey>,
    viewmodel: AViewmodel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "A Screen", style = TextStyle(color = Color.White, fontSize = 20.sp))
            Spacer(Modifier.height(20.dp))

            TextField(
                value = viewmodel.textFieldValue.value,
                onValueChange = viewmodel::updateTextFieldValue,
                modifier = Modifier.fillMaxWidth()
            )

            Spacer(Modifier.height(20.dp))
            Button(onClick = { backStack.add(Chapter2Screens.TestB(viewmodel.textFieldValue.value))}) {
                Text(text = "Go to B")
            }
        }
    }
}

@HiltViewModel
class AViewmodel @Inject constructor(): ViewModel() {
    private val _textFieldValue = mutableStateOf("")
    val textFieldValue: State<String> = _textFieldValue

    fun updateTextFieldValue(newValue: String) {
        _textFieldValue.value = newValue
    }
}

@Composable
fun BScreen(
    backStack: SnapshotStateList<NavKey>, data: String,
    viewmodel: BViewmodel = hiltViewModel()
) {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(20.dp)
        ) {
            Text(text = "B Screen : $data", style = TextStyle(color = Color.White, fontSize = 20.sp))
            Spacer(Modifier.height(20.dp))
            Button(onClick = { backStack.removeLastOrNull() }) {
                Text(text = "Back")
            }
        }
    }
}

@HiltViewModel
class BViewmodel @Inject constructor(): ViewModel() {
    init {

        Log.d("BViewmodel", "init")
    }

    override fun onCleared() {
        super.onCleared()
        Log.d("BViewmodel", "onCleared")
    }
}