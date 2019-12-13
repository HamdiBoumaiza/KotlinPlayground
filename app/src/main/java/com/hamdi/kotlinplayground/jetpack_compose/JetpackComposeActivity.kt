package com.hamdi.kotlinplayground.jetpack_compose

import android.os.Bundle
import android.view.MenuItem
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.Composable
import androidx.ui.core.Text
import androidx.ui.core.dp
import androidx.ui.core.setContent
import androidx.ui.foundation.VerticalScroller
import androidx.ui.graphics.Color
import androidx.ui.layout.*
import androidx.ui.material.*

class JetpackComposeActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { displayButtons() }

    }

    @Composable
    fun hello(name: String) = MaterialTheme {
        FlexColumn {
            inflexible {
                // Item height will be equal content height
                TopAppBar<MenuItem>( // App Bar with title
                    title = { Text("Jetpack Compose Sample") }
                )
            }
            expanded(1F) {
                // occupy whole empty space in the Column
                Center {
                    // Center content
                    Text("Hello $name!") // Text label
                }
            }
        }
    }


    @Composable
    private fun displayButtons(){
        Column {
            Button(
                text = "This is Button 1",
                onClick = {
                    //the click listeners
                },
                style = ContainedButtonStyle()
            )
            HeightSpacer(32.dp)
            Button(
                text = "This is Button 2",
                onClick = {
                    //the click listeners
                },
                style = OutlinedButtonStyle()
            )
        }
    }
    @Composable
    private fun createListView() {
        MaterialTheme {
            VerticalScroller {
                Column {
                    (0..10).forEachIndexed { index, _ ->
                        createListItem(index)
                        Divider(color = Color.Blue, height = 1.dp)
                    }
                }
            }

        }
    }

    @Composable
    private fun createListItem(itemIndex: Int) {
        Padding(left = 8.dp, right = 8.dp, top = 8.dp, bottom = 8.dp) {
            FlexRow(crossAxisAlignment = CrossAxisAlignment.Center) {
                expanded(1.0f) {
                    Text("Item $itemIndex")
                }
                inflexible {
                    Button(
                        "Button $itemIndex",
                        style = ContainedButtonStyle(),
                        onClick = {
                            Toast.makeText(
                                this@JetpackComposeActivity,
                                "Item name $itemIndex",
                                Toast.LENGTH_SHORT
                            ).show()
                        })

                }
            }
        }
    }
}
