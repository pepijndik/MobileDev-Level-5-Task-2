package nl.pdik.level5.task2.ui.screens

sealed class Screens(
    val route: String
){
    object HomeScreen: Screens("home_screen")
    object QuestScreen: Screens("quest_screen")

}
