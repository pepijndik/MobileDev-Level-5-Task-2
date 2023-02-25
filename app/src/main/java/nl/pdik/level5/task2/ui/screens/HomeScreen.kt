package nl.pdik.level5.task2.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.pdik.level5.task2.R
import nl.pdik.level5.task2.viewModel.QuestViewModel


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun HomeScreen(viewModel: QuestViewModel, navController: NavController) {
    val context = LocalContext.current

    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = context.getString(R.string.app_name)) },
            )
        },
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceAround,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Text(text = stringResource(id = R.string.header), style = MaterialTheme.typography.h5)
            Text(text = stringResource(id = R.string.desc), style = MaterialTheme.typography.body1)
            Button(
                onClick = {navController.navigate(Screens.QuestScreen.route)},
                modifier = Modifier.width(120.dp)) {
                Text(text = stringResource(id = R.string.start))
            }
        }
    }
}