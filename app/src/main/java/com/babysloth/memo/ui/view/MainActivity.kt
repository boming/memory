package com.babysloth.memo.ui.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.babysloth.memo.R
import com.babysloth.memo.ui.theme.*
import com.babysloth.memo.ui.view.MainActivity.BottomNavigationItem.*
import com.babysloth.memo.ui.view.bookmark.BookmarkScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { MainActivityTheme { MainScreen() } }
    }

    @Composable
    fun MainScreen() {
        val navController = rememberNavController()

        Scaffold(bottomBar = { BottomNavigationBar(navController = navController) }) {
            Box(Modifier.padding(it)) { NavigationGraph(navController = navController) }
        }
    }

    @Composable
    fun BottomNavigationBar(navController: NavHostController) {
        val items = listOf(NewMemo, MemoList, Bookmarks, Settings)

        BottomNavigation(
            backgroundColor = Blue200,
            contentColor = Purple700
        ) {
            val navBackStackEntry by navController.currentBackStackEntryAsState()

            val currentRoute = navBackStackEntry?.destination?.route

            items.forEach { item ->
                BottomNavigationItem(
                    icon = {
                        Icon(
                            painter = painterResource(id = item.icon),
                            contentDescription = stringResource(id = item.title),
                            modifier = Modifier
                                .width(26.dp)
                                .height(26.dp)
                        )
                    },
                    label = { Text(stringResource(id = item.title), fontSize = 9.sp) },
                    selectedContentColor = Brown400,
                    unselectedContentColor = Brown200,
                    selected = currentRoute == item.screenRoute,
                    onClick = {
                        navController.navigate(item.screenRoute) {
                            navController.graph.startDestinationRoute?.apply {
                                popUpTo(this) { saveState = true }
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                )
            }
        }
    }

    @Composable
    fun NewMemo() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Yellow200)
        ) {
            Text(
                text = stringResource(id = R.string.new_memo),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = Brown800,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    fun MemoList() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Yellow200)
        ) {
            Text(
                text = stringResource(id = R.string.memo_list),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = Brown800,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Composable
    fun Settings() {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(Brown200)
        ) {
            Text(
                text = stringResource(id = R.string.settings),
                style = MaterialTheme.typography.h3,
                textAlign = TextAlign.Center,
                color = Brown800,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun DefaultPreview() {
        MainActivityTheme {
            NewMemo()
        }
    }

    @Composable
    fun NavigationGraph(navController: NavHostController) {
        NavHost(
            navController = navController,
            startDestination = NewMemo.screenRoute
        ) {
            composable(NewMemo.screenRoute) { NewMemo() }
            composable(MemoList.screenRoute) { MemoList() }
            composable(Bookmarks.screenRoute) { BookmarkScreen() }
            composable(Settings.screenRoute) { Settings() }
        }

    }

    sealed class BottomNavigationItem(
        @StringRes val title: Int,
        @DrawableRes val icon: Int,
        val screenRoute: String
    ) {
        object NewMemo : BottomNavigationItem(R.string.new_memo, R.drawable.new_memo_24, NEW_MEMO)
        object MemoList :
            BottomNavigationItem(R.string.memo_list, R.drawable.memo_list_24, MEMO_LIST)

        object Bookmarks :
            BottomNavigationItem(R.string.bookmarks, R.drawable.bookmarks_24, BOOKMARKS)

        object Settings : BottomNavigationItem(R.string.settings, R.drawable.settings_24, SETTINGS)
    }


    companion object {
        const val NEW_MEMO = "NEW_MEO"
        const val MEMO_LIST = "MEMO_LIST"
        const val BOOKMARKS = "BOOKMARKS"
        const val SETTINGS = "SETTINGS"
    }
}
