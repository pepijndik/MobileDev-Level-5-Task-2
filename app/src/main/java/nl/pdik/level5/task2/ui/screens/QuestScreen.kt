package nl.pdik.level5.task2.ui.screens

import android.annotation.SuppressLint
import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import nl.pdik.level5.task2.R
import nl.pdik.level5.task2.model.Quest
import nl.pdik.level5.task2.viewModel.QuestViewModel
import java.util.ArrayList


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun QuestScreen(viewModel: QuestViewModel, navController: NavController) {
    val context = LocalContext.current
    val selected = remember { mutableStateOf("") }


    viewModel.getQuests()
    val errorMsg by viewModel.errorText.observeAsState()
    val quests by viewModel.quests.observeAsState();
    val current_questNumber = remember {
        mutableStateOf(0)
    };
    val quest = remember {
        mutableStateOf<Quest?>(null)
    };
    if (quests?.get(current_questNumber.value) != null) {
        quest.value = quests!!.get(current_questNumber.value)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = context.getString(R.string.app_name)) },
            )
        },
    ) {
        if (quests?.get(0) != null) {

            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(end = 4.dp),
                    horizontalAlignment = Alignment.End
                ) {

                    Text(
                        text = stringResource(
                            id = R.string.amount_quests,
                            current_questNumber.value + 1,
                            quests!!.size
                        )
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(4.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Choices(quest.value!!, selected = selected)
                    Row(
                        verticalAlignment = Alignment.Bottom
                    ) {
                        Button(onClick = {
                            if (!selected.value.equals(quest.value!!.correctAnswer)) informUser(
                                context,
                                R.string.not_correct
                            )
                            else if
                                         (quests?.size == current_questNumber.value + 1 && selected.value.equals(
                                    quest.value!!.correctAnswer
                                )
                            ) {
                                navController.navigate(Screens.HomeScreen.route);
                            } else if
                                           (quests?.size!! > current_questNumber.value + 1 && selected.value.equals(
                                    quest.value!!.correctAnswer
                                )
                            ) {
                                current_questNumber.value = current_questNumber.value + 1;
                                quest.value = quests?.get(current_questNumber.value);
                            }
                        }) {
                            Text(
                                text = stringResource(id = R.string.submit),
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }


            }
        }

    }
}

private fun informUser(context: Context, msgId: Int) {
    Toast.makeText(context, context.getString(msgId), Toast.LENGTH_SHORT).show()
}


@Composable
fun Choices(
    quest: Quest,
    selected: MutableState<String>
) {
    Column(
        horizontalAlignment = Alignment.Start
    ) {
        Text(text = quest.question)
        LazyColumn(
            modifier = Modifier.padding(top = 6.dp),
            content = {
                items(items = quest.choices) { choice ->
                    Row(
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        RadioButton(
                            selected = choice.contentEquals(selected.value),
                            onClick = {
                                selected.value = choice.toString();
                            })
                        Text(text = choice, modifier = Modifier.padding(bottom = 0.dp))
                    }

                }
            })
    }


}