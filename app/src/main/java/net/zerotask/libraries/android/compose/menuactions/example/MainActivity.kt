package net.zerotask.libraries.android.compose.menuactions.example

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Create
import androidx.compose.material.icons.filled.Remove
import androidx.compose.material3.*
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import net.zerotask.libraries.android.R
import net.zerotask.libraries.android.compose.menuactions.Action
import net.zerotask.libraries.android.compose.menuactions.ActionMenu

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            MaterialTheme {
                var maxIcons by remember { mutableStateOf(1) }

                Scaffold(
                    topBar = {
                        SmallTopAppBar(title = {
                            Text(text = stringResource(id = R.string.app_name))
                        }, actions = {
                            ActionMenu(maxIcons = maxIcons, actions = listOf(
                                Action(
                                    title = R.string.title1,
                                    icon = Icons.Default.Create,
                                    onClick = { println("title1") }
                                ),

                                Action(
                                    title = R.string.title2,
                                    onClick = { println("title2") }
                                ),
                                Action(
                                    title = R.string.title3,
                                    onClick = { println("title3") }
                                ),
                                Action(
                                    title = R.string.title4,
                                    onClick = { println("title4") }
                                ),
                            ))
                        })
                    }
                ) { innerPadding ->
                    Box(modifier = Modifier
                        .fillMaxSize()
                        .padding(innerPadding)) {
                        Row(modifier = Modifier.align(Alignment.Center)) {
                            IconButton(onClick = { maxIcons-- }) {
                                Icon(Icons.Default.Remove, contentDescription = "Decrement topbar" +
                                        " icon count")
                            }
                            IconButton(onClick = { maxIcons++ }) {
                                Icon(Icons.Default.Add, contentDescription = "Increment topbar " +
                                        "icon count")
                            }
                        }
                    }
                }
            }
        }
    }
}
