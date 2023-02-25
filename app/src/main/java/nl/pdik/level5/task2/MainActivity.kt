package nl.pdik.level5.task2

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import nl.pdik.level5.task2.ui.screens.Screens
import nl.pdik.level5.task2.ui.theme.Task2Theme
import nl.pdik.level5.task2.viewModel.QuestViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val context: Context = this
            QuestApp(context);
        }
    }
}

@Composable
fun QuestApp(context: Context) {
    val navController = rememberNavController()
    Scaffold { innerPadding ->
        QuestAppNavHost(context, navController, modifier = Modifier.padding(innerPadding))
    }
}

@Composable
fun QuestAppNavHost(
    context: Context,
    navController: NavHostController,
    modifier: Modifier = Modifier
) {

    val viewModel: QuestViewModel = viewModel();
    NavHost(
        navController = navController,
        startDestination = Screens.HomeScreen.route,
        modifier = modifier
    ) {
        composable(Screens.HomeScreen.route) {

        }
        composable(Screens.QuestScreen.route) {

        }

    }
}

