package com.example.anitalkprototype

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ChatBubbleOutline
import androidx.compose.material.icons.filled.Explore
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Forum
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.LocationOn
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Send
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.AssistChip
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ScrollableTabRow
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.anitalkprototype.ui.theme.AniTalkBlue
import com.example.anitalkprototype.ui.theme.AniTalkDark
import com.example.anitalkprototype.ui.theme.AniTalkMuted
import com.example.anitalkprototype.ui.theme.AniTalkNavy
import com.example.anitalkprototype.ui.theme.AniTalkSurface
import com.example.anitalkprototype.ui.theme.AniTalkTeal
import com.example.anitalkprototype.ui.theme.AniTalkText
import com.example.anitalkprototype.ui.theme.AniTalkTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AniTalkTheme {
                Surface(modifier = Modifier.fillMaxSize(), color = AniTalkTeal) {
                    AniTalkApp()
                }
            }
        }
    }
}

private enum class AuthScreen {
    Welcome, Login, Signup, Main
}

private data class BottomDestination(
    val route: String,
    val label: String,
    val icon: androidx.compose.ui.graphics.vector.ImageVector
)

 data class TrendItem(
    val title: String,
    val subtitle: String,
    val emoji: String
)

 data class PostItem(
    val author: String,
    val title: String,
    val body: String,
    val likes: Int,
    val comments: Int
)

private data class MessageItem(
    val name: String,
    val preview: String,
    val time: String
)

private data class EventItem(
    val name: String,
    val description: String,
    val place: String,
    val date: String
)

@Composable
fun AniTalkApp() {
    var screen by rememberSaveable { mutableStateOf(AuthScreen.Welcome) }

    when (screen) {
        AuthScreen.Welcome -> WelcomeScreen(
            onLogin = { screen = AuthScreen.Login },
            onSignup = { screen = AuthScreen.Signup }
        )

        AuthScreen.Login -> LoginScreen(
            onBack = { screen = AuthScreen.Welcome },
            onEnter = { screen = AuthScreen.Main },
            onSignup = { screen = AuthScreen.Signup }
        )

        AuthScreen.Signup -> SignupScreen(
            onBack = { screen = AuthScreen.Welcome },
            onCreateAccount = { screen = AuthScreen.Main },
            onLogin = { screen = AuthScreen.Login }
        )

        AuthScreen.Main -> MainPrototypeScreen(onLogout = { screen = AuthScreen.Welcome })
    }
}

@Composable
fun WelcomeScreen(onLogin: () -> Unit, onSignup: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(AniTalkNavy, AniTalkTeal, AniTalkDark)
                )
            )
            .padding(24.dp),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Text(text = "WELCOME TO", color = AniTalkText.copy(alpha = 0.85f), fontSize = 14.sp)
            Text(
                text = "ANITALK",
                color = AniTalkText,
                fontSize = 38.sp,
                fontWeight = FontWeight.ExtraBold,
                letterSpacing = 3.sp
            )
            Spacer(Modifier.height(12.dp))
            Text(
                text = "Anime fans. Posts. Events. Messages. One prototype.",
                color = AniTalkText.copy(alpha = 0.88f),
                textAlign = TextAlign.Center
            )
            Spacer(Modifier.height(28.dp))
            Card(
                colors = CardDefaults.cardColors(containerColor = Color.White.copy(alpha = 0.08f)),
                shape = RoundedCornerShape(28.dp)
            ) {
                Column(
                    modifier = Modifier.padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "⚔️  🌀  🔥  👒  🏐", fontSize = 32.sp)
                    Spacer(Modifier.height(12.dp))
                    Button(
                        onClick = onLogin,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AniTalkBlue)
                    ) {
                        Text("Start Your Adventure")
                    }
                    Spacer(Modifier.height(10.dp))
                    Button(
                        onClick = onSignup,
                        modifier = Modifier.fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(containerColor = AniTalkNavy)
                    ) {
                        Text("Sign Up")
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginScreen(onBack: () -> Unit, onEnter: () -> Unit, onSignup: () -> Unit) {
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Welcome Back") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText,
                    navigationIconContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        AuthForm(
            modifier = Modifier.padding(padding),
            title = "Log in to AniTalk",
            fields = listOf(
                "Username" to Pair(username, { username = it }),
                "Password" to Pair(password, { password = it })
            ),
            buttonText = "Enter",
            onButtonClick = onEnter,
            footerText = "Don't have an account?",
            footerAction = "Sign up here",
            onFooterClick = onSignup
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignupScreen(onBack: () -> Unit, onCreateAccount: () -> Unit, onLogin: () -> Unit) {
    var email by rememberSaveable { mutableStateOf("") }
    var username by rememberSaveable { mutableStateOf("") }
    var password by rememberSaveable { mutableStateOf("") }

    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Sign Up Now") },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(Icons.Default.ArrowBack, contentDescription = "Back")
                    }
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText,
                    navigationIconContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        AuthForm(
            modifier = Modifier.padding(padding),
            title = "Create your account",
            fields = listOf(
                "Email" to Pair(email, { email = it }),
                "Username" to Pair(username, { username = it }),
                "Password" to Pair(password, { password = it })
            ),
            buttonText = "Create Account",
            onButtonClick = onCreateAccount,
            footerText = "Already have an account?",
            footerAction = "Log in",
            onFooterClick = onLogin
        )
    }
}

@Composable
fun AuthForm(
    modifier: Modifier = Modifier,
    title: String,
    fields: List<Pair<String, Pair<String, (String) -> Unit>>>,
    buttonText: String,
    onButtonClick: () -> Unit,
    footerText: String,
    footerAction: String,
    onFooterClick: () -> Unit
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Card(
            modifier = Modifier.fillMaxWidth(),
            colors = CardDefaults.cardColors(containerColor = AniTalkDark.copy(alpha = 0.92f)),
            shape = RoundedCornerShape(28.dp)
        ) {
            Column(modifier = Modifier.padding(24.dp)) {
                Text("AniTalk", fontSize = 34.sp, fontWeight = FontWeight.ExtraBold, color = AniTalkText)
                Spacer(Modifier.height(8.dp))
                Text(title, color = AniTalkText.copy(alpha = 0.85f))
                Spacer(Modifier.height(18.dp))
                fields.forEach { (label, statePair) ->
                    OutlinedTextField(
                        value = statePair.first,
                        onValueChange = statePair.second,
                        label = { Text(label) },
                        modifier = Modifier.fillMaxWidth(),
                        shape = RoundedCornerShape(16.dp)
                    )
                    Spacer(Modifier.height(12.dp))
                }
                Spacer(Modifier.height(8.dp))
                Button(
                    onClick = onButtonClick,
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AniTalkBlue)
                ) {
                    Text(buttonText)
                }
                Spacer(Modifier.height(12.dp))
                Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
                    Text(footerText, color = AniTalkText.copy(alpha = 0.8f))
                    Spacer(Modifier.width(4.dp))
                    Text(
                        text = footerAction,
                        color = Color(0xFFE39BFF),
                        modifier = Modifier.clickable { onFooterClick() }
                    )
                }
            }
        }
    }
}

@Composable
fun MainPrototypeScreen(onLogout: () -> Unit) {
    val navController = rememberNavController()
    val destinations = listOf(
        BottomDestination("profile", "Profile", Icons.Default.Person),
        BottomDestination("messenger", "Messenger", Icons.Default.Send),
        BottomDestination("home", "Home", Icons.Default.Home),
        BottomDestination("events", "Events", Icons.Default.Explore),
        BottomDestination("settings", "Settings", Icons.Default.Settings)
    )

    Scaffold(
        containerColor = AniTalkTeal,
        bottomBar = {
            NavigationBar(containerColor = AniTalkDark) {
                val currentRoute = navController.currentBackStackEntryAsState().value?.destination?.route
                destinations.forEach { item ->
                    NavigationBarItem(
                        selected = currentRoute == item.route,
                        onClick = {
                            navController.navigate(item.route) {
                                popUpTo(navController.graph.findStartDestination().id) {
                                    saveState = true
                                }
                                launchSingleTop = true
                                restoreState = true
                            }
                        },
                        icon = { Icon(item.icon, contentDescription = item.label) },
                        label = { Text(item.label) }
                    )
                }
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding(padding)
        ) {
            composable("home") { HomeScreen() }
            composable("profile") { ProfileScreen() }
            composable("messenger") { MessengerScreen() }
            composable("events") { EventsScreen() }
            composable("settings") { SettingsScreen(onLogout = onLogout) }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen() {
    var selectedTab by rememberSaveable { mutableStateOf(0) }
    val tabs = listOf("Trending Now", "Create a Post")

    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            Column {
                CenterAlignedTopAppBar(
                    title = {
                        Text(
                            text = "AniTalk",
                            fontWeight = FontWeight.ExtraBold,
                            letterSpacing = 2.sp
                        )
                    },
                    colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                        containerColor = AniTalkTeal,
                        titleContentColor = AniTalkText
                    )
                )
                ScrollableTabRow(
                    selectedTabIndex = selectedTab,
                    containerColor = AniTalkDark,
                    contentColor = AniTalkText,
                    edgePadding = 16.dp
                ) {
                    tabs.forEachIndexed { index, title ->
                        Tab(
                            selected = selectedTab == index,
                            onClick = { selectedTab = index },
                            text = { Text(title, fontWeight = FontWeight.Bold) }
                        )
                    }
                }
            }
        }
    ) { padding ->
        when (selectedTab) {
            0 -> TrendingTabContent(padding)
            else -> CreatePostTabContent(padding)
        }
    }
}

@Composable
fun TrendingTabContent(padding: PaddingValues) {
    val trends = listOf(
        TrendItem("Solo Leveling", "Most discussed this week", "⚔️"),
        TrendItem("Jujutsu Kaisen", "Top fan theories today", "🌀"),
        TrendItem("One Piece", "Biggest community hype", "👒")
    )
    val posts = listOf(
        PostItem("@GojoFan", "Episode Reactions", "That last episode animation was unreal.", 212, 41),
        PostItem("@OtakuBuilder", "Convention Meetup", "Who is going to the Richmond anime meetup this weekend?", 128, 33),
        PostItem("@MangaMode", "New Chapter", "We need a spoiler-free thread for the latest release.", 89, 20)
    )

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(14.dp),
        contentPadding = PaddingValues(vertical = 16.dp)
    ) {
        item {
            Text("Trending Anime", color = AniTalkText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            Spacer(Modifier.height(12.dp))
            Row(
                modifier = Modifier.horizontalScroll(rememberScrollState()),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                trends.forEach { item ->
                    TrendCard(item)
                }
            }
        }

        item {
            Spacer(Modifier.height(8.dp))
            Text("Trending Posts", color = AniTalkText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
        }

        items(posts) { post ->
            PostCard(post)
        }
    }
}

@Composable
fun TrendCard(item: TrendItem) {
    Card(
        modifier = Modifier.width(220.dp),
        colors = CardDefaults.cardColors(containerColor = AniTalkDark),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(item.emoji, fontSize = 34.sp)
            Spacer(Modifier.height(8.dp))
            Text(item.title, color = AniTalkText, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
            Spacer(Modifier.height(4.dp))
            Text(item.subtitle, color = AniTalkMuted)
            Spacer(Modifier.height(12.dp))
            AssistChip(onClick = {}, label = { Text("View details") })
        }
    }
}

@Composable
fun PostCard(post: PostItem) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AniTalkDark),
        shape = RoundedCornerShape(24.dp)
    ) {
        Column(modifier = Modifier.padding(18.dp)) {
            Text(post.author, color = Color(0xFF9CC2FF), fontWeight = FontWeight.Bold)
            Spacer(Modifier.height(6.dp))
            Text(post.title, color = AniTalkText, fontWeight = FontWeight.ExtraBold, fontSize = 18.sp)
            Spacer(Modifier.height(4.dp))
            Text(post.body, color = AniTalkText.copy(alpha = 0.88f))
            Spacer(Modifier.height(14.dp))
            Row(horizontalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.Favorite, contentDescription = null, tint = Color(0xFFFF6B81))
                    Spacer(Modifier.width(6.dp))
                    Text("${post.likes}", color = AniTalkText)
                }
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(Icons.Default.ChatBubbleOutline, contentDescription = null, tint = AniTalkText)
                    Spacer(Modifier.width(6.dp))
                    Text("${post.comments}", color = AniTalkText)
                }
            }
        }
    }
}

@Composable
fun CreatePostTabContent(padding: PaddingValues) {
    var title by rememberSaveable { mutableStateOf("") }
    var body by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding)
            .padding(16.dp)
    ) {
        Card(
            colors = CardDefaults.cardColors(containerColor = AniTalkDark),
            shape = RoundedCornerShape(28.dp)
        ) {
            Column(modifier = Modifier.padding(18.dp)) {
                Text("Create a Post", color = AniTalkText, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                Spacer(Modifier.height(8.dp))
                Text("Prototype demo: make a post layout your team can show tomorrow.", color = AniTalkMuted)
                Spacer(Modifier.height(16.dp))
                OutlinedTextField(
                    value = title,
                    onValueChange = { title = it },
                    modifier = Modifier.fillMaxWidth(),
                    label = { Text("Post title") },
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(Modifier.height(12.dp))
                OutlinedTextField(
                    value = body,
                    onValueChange = { body = it },
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(160.dp),
                    label = { Text("What's happening in the anime world?") },
                    shape = RoundedCornerShape(16.dp)
                )
                Spacer(Modifier.height(16.dp))
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    AssistChip(onClick = {}, label = { Text("#anime") })
                    AssistChip(onClick = {}, label = { Text("#events") })
                    AssistChip(onClick = {}, label = { Text("#friends") })
                }
                Spacer(Modifier.height(18.dp))
                Button(
                    onClick = {},
                    modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(containerColor = AniTalkBlue)
                ) {
                    Icon(Icons.Default.Add, contentDescription = null)
                    Spacer(Modifier.width(8.dp))
                    Text("Post to AniTalk")
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileScreen() {
    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Profile") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(14.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                    shape = RoundedCornerShape(28.dp)
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(78.dp)
                                    .clip(CircleShape)
                                    .background(AniTalkBlue),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(Icons.Default.Person, contentDescription = null, tint = AniTalkText, modifier = Modifier.size(40.dp))
                            }
                            Spacer(Modifier.width(16.dp))
                            Column {
                                Text("Custom Name", color = AniTalkText, fontWeight = FontWeight.ExtraBold, fontSize = 22.sp)
                                Text("@anime_leveler", color = AniTalkMuted)
                                Spacer(Modifier.height(6.dp))
                                Text("Bio: Events, manga nights, and action anime.", color = AniTalkText.copy(alpha = 0.9f))
                            }
                        }
                        Spacer(Modifier.height(18.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            StatChip("Post", "24")
                            StatChip("Followers", "310")
                            StatChip("Following", "180")
                        }
                    }
                }
            }

            item {
                Text("Favorite Anime", color = AniTalkText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            item {
                Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                    FavoriteTile("JJK")
                    FavoriteTile("Blue Lock")
                    FavoriteTile("One Piece")
                }
            }

            item {
                Text("Otaku Agenda", color = AniTalkText, fontWeight = FontWeight.Bold, fontSize = 20.sp)
            }

            items(listOf("Going to Anime Night", "Interested in Manga Meetup", "Cosplay planning for Saturday")) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                    shape = RoundedCornerShape(20.dp)
                ) {
                    Text(item, modifier = Modifier.padding(18.dp), color = AniTalkText)
                }
            }
        }
    }
}

@Composable
fun StatChip(label: String, value: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AniTalkSurface),
        shape = RoundedCornerShape(18.dp)
    ) {
        Column(modifier = Modifier.padding(horizontal = 18.dp, vertical = 10.dp), horizontalAlignment = Alignment.CenterHorizontally) {
            Text(value, color = AniTalkText, fontWeight = FontWeight.ExtraBold)
            Text(label, color = AniTalkMuted, fontSize = 12.sp)
        }
    }
}

@Composable
fun FavoriteTile(name: String) {
    Card(
        modifier = Modifier.width(110.dp),
        colors = CardDefaults.cardColors(containerColor = AniTalkDark),
        shape = RoundedCornerShape(20.dp)
    ) {
        Column(
            modifier = Modifier.padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(Icons.Default.Star, contentDescription = null, tint = Color(0xFFFFD166), modifier = Modifier.size(28.dp))
            Spacer(Modifier.height(8.dp))
            Text(name, color = AniTalkText, textAlign = TextAlign.Center)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MessengerScreen() {
    val messages = listOf(
        MessageItem("Harmony", "Let’s show the home tab first during the demo.", "9:41 AM"),
        MessageItem("Travis", "I can talk about the event feature.", "9:18 AM"),
        MessageItem("New Friend", "Are y'all going to the anime meetup?", "Yesterday"),
        MessageItem("Group Chat", "Prototype is looking clean.", "Yesterday")
    )

    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Messenger") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Card(
                    colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(18.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text("Keep messages between friends", color = AniTalkText, fontWeight = FontWeight.Bold)
                        Icon(Icons.Default.Forum, contentDescription = null, tint = AniTalkText)
                    }
                }
            }

            items(messages) { item ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                    shape = RoundedCornerShape(22.dp)
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .size(54.dp)
                                .clip(CircleShape)
                                .background(AniTalkBlue),
                            contentAlignment = Alignment.Center
                        ) {
                            Icon(Icons.Default.Person, contentDescription = null, tint = AniTalkText)
                        }
                        Spacer(Modifier.width(14.dp))
                        Column(modifier = Modifier.weight(1f)) {
                            Text(item.name, color = AniTalkText, fontWeight = FontWeight.ExtraBold)
                            Text(item.preview, color = AniTalkMuted)
                        }
                        Text(item.time, color = AniTalkMuted, fontSize = 12.sp)
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EventsScreen() {
    val events = listOf(
        EventItem("Richmond Anime Meetup", "Meet local fans and swap recommendations.", "Richmond, VA", "Fri 7:00 PM"),
        EventItem("Manga Night", "Bring your current read and favorite panels.", "Student Center", "Sat 5:30 PM"),
        EventItem("Cosplay Walk", "Casual meetup before the main event.", "Campus Lawn", "Sun 2:00 PM")
    )

    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Events") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            item {
                Text(
                    "Just events, info, location, and attendance interest.",
                    color = AniTalkText.copy(alpha = 0.9f)
                )
            }
            items(events) { event ->
                Card(
                    colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                    shape = RoundedCornerShape(24.dp)
                ) {
                    Column(modifier = Modifier.padding(18.dp)) {
                        Text(event.name, color = AniTalkText, fontWeight = FontWeight.ExtraBold, fontSize = 20.sp)
                        Spacer(Modifier.height(8.dp))
                        Text(event.description, color = AniTalkText.copy(alpha = 0.9f))
                        Spacer(Modifier.height(10.dp))
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Icon(Icons.Default.LocationOn, contentDescription = null, tint = AniTalkText)
                            Spacer(Modifier.width(6.dp))
                            Text(event.place, color = AniTalkText)
                        }
                        Spacer(Modifier.height(6.dp))
                        Text(event.date, color = AniTalkMuted)
                        Spacer(Modifier.height(14.dp))
                        Row(horizontalArrangement = Arrangement.spacedBy(10.dp)) {
                            AssistChip(onClick = {}, label = { Text("Going") })
                            AssistChip(onClick = {}, label = { Text("Interested") })
                            AssistChip(onClick = {}, label = { Text("Not Going") })
                        }
                    }
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SettingsScreen(onLogout: () -> Unit) {
    Scaffold(
        containerColor = AniTalkTeal,
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text("Settings") },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = AniTalkTeal,
                    titleContentColor = AniTalkText
                )
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            SettingsItem(icon = Icons.Default.Person, title = "Account Privacy")
            SettingsItem(icon = Icons.Default.Notifications, title = "Notifications")
            SettingsItem(icon = Icons.Default.Settings, title = "App Preferences")
            SettingsItem(icon = Icons.Default.Favorite, title = "Terms & Conditions")
            Card(
                colors = CardDefaults.cardColors(containerColor = AniTalkDark),
                shape = RoundedCornerShape(22.dp)
            ) {
                Column(modifier = Modifier.padding(16.dp)) {
                    Text("Presentation mode tip", color = AniTalkText, fontWeight = FontWeight.ExtraBold)
                    Spacer(Modifier.height(6.dp))
                    Text(
                        "Use this screen to explain future features like privacy controls, notifications, and account management.",
                        color = AniTalkMuted
                    )
                    Spacer(Modifier.height(12.dp))
                    TextButton(onClick = onLogout) {
                        Text("Log Out")
                    }
                }
            }
        }
    }
}

@Composable
fun SettingsItem(icon: androidx.compose.ui.graphics.vector.ImageVector, title: String) {
    Card(
        colors = CardDefaults.cardColors(containerColor = AniTalkDark),
        shape = RoundedCornerShape(22.dp)
    ) {
        ListItem(
            headlineContent = { Text(title, color = AniTalkText) },
            leadingContent = { Icon(icon, contentDescription = null, tint = AniTalkText) }
        )
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun AniTalkPreview() {
    AniTalkTheme {
        AniTalkApp()
    }
}
