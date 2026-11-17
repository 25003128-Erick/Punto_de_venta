package org.utl.ldsm406_puntoventa.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview


data class BottomItem(
    val title: String,
    val icon: ImageVector
)

@Composable
fun BottomBar(
    selectedItem: Int,
    onItemSelected: (Int) -> Unit
){
    val items = listOf(
        BottomItem(
            "Inicio",
            Icons.Default.Home
        ),
        BottomItem(
            "Productos",
            Icons.Default.ShoppingCart
        ),
        BottomItem(
            "Ventas",
            Icons.Default.Receipt
        ),
        BottomItem(
            "Ajustes",
            Icons.Default.Settings
        )
    )
    NavigationBar() {
        items.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedItem == index,
                onClick = {
                    onItemSelected(index)
                },
                icon = {
                    Icon(
                        imageVector = item.icon,
                        contentDescription = item.title
                        )
                },
                label = {
                    Text(item.title)
                }
            )
        }
    }
}


@Preview
@Composable
fun PreviewBottomBar(){
    BottomBar(0,{})
}