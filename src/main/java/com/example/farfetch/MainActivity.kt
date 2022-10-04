package com.example.farfetch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.farfetch.entity.Hire
import com.example.farfetch.ui.theme.FarfetchTheme
import com.example.farfetch.viewmodel.MainViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            FarfetchTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(), 
                    color = MaterialTheme.colorScheme.background
                ) {
                    Show()
                }
            }
        }
    }
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun Show(vm: MainViewModel = viewModel()) {
    vm.fetchItems()
    val grouped: Map<Int, List<Hire>> by vm.getItemsLiveData().observeAsState(mapOf())
    LazyColumn {
        grouped.forEach {
            stickyHeader {
                Text(
                    "${it.key}",
                    color = Color.Red,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 30.sp
                )
            }
            items(it.value.size) { index ->
                val hire = it.value[index]
                Text(
                    "ID is ${hire.id}, name is ${hire.name}",
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                    fontSize = 20.sp,
                    modifier = Modifier.padding(16.dp)
                )
            }
        }
    }
}