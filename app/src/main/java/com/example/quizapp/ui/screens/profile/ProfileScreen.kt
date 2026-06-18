package com.example.quizapp.ui.screens.profile

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.quizapp.data.model.User
import com.example.quizapp.ui.theme.AccentBlue
import com.example.quizapp.ui.theme.AccentOrange
import com.example.quizapp.ui.theme.Background
import com.example.quizapp.ui.theme.BorderColor
import com.example.quizapp.ui.theme.GreenPrimary
import com.example.quizapp.ui.theme.GreenDark
import com.example.quizapp.ui.theme.Surface
import com.example.quizapp.ui.theme.SurfaceVariant
import com.example.quizapp.ui.theme.TextPrimary
import com.example.quizapp.ui.theme.TextSecondary
import com.example.quizapp.ui.theme.TextTertiary
import com.example.quizapp.ui.theme.Typography
import com.example.quizapp.ui.theme.Warning
import com.example.quizapp.ui.theme.Success
import com.example.quizapp.ui.viewModel.ProfileViewModel
import com.example.quizapp.utils.Resource
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier,
    navController: NavController,
    viewModel: ProfileViewModel = hiltViewModel()
) {
    val user by viewModel.loggedUser.collectAsState()

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = Background)
    ) {
        when (val state = user) {
            is Resource.Idle, is Resource.Loading -> {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.Center),
                    color = GreenPrimary
                )
            }

            is Resource.Error -> {
                Text(
                    text = state.message,
                    color = com.example.quizapp.ui.theme.Error,
                    style = Typography.bodyLarge,
                    modifier = Modifier
                        .align(Alignment.Center)
                        .padding(20.dp)
                )
            }

            is Resource.Success -> {
                ProfileContent(
                    user = state.data,
                    onEditProfile = {},
                    onChangeAvatar = {}
                )
            }
        }
    }
}

@Composable
private fun ProfileContent(
    user: User,
    onEditProfile: () -> Unit,
    onChangeAvatar: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 20.dp, vertical = 40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AvatarSection(
            initials = user.initials,
            onChangeAvatar = onChangeAvatar
        )
        Spacer(Modifier.height(16.dp))
        Text(
            text = user.displayName,
            color = TextPrimary,
            style = Typography.headlineLarge,
            textAlign = TextAlign.Center
        )
        if (user.username.isNotBlank()) {
            Spacer(Modifier.height(2.dp))
            Text(
                text = "@${user.username}",
                color = GreenPrimary,
                style = Typography.bodyMedium
            )
        }
        Spacer(Modifier.height(4.dp))
        Text(
            text = "Member since ${formatDate(user.createdAt)}",
            color = TextTertiary,
            style = Typography.bodySmall
        )
        Spacer(Modifier.height(20.dp))
        OutlinedButton(
            onClick = onEditProfile,
            modifier = Modifier
                .fillMaxWidth()
                .height(46.dp),
            shape = RoundedCornerShape(50),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = GreenPrimary
            ),
            border = androidx.compose.foundation.BorderStroke(1.dp, GreenPrimary)
        ) {
            Icon(
                imageVector = Icons.Default.Edit,
                contentDescription = null,
                modifier = Modifier.size(16.dp)
            )
            Spacer(Modifier.width(8.dp))
            Text(text = "Edit Profile", style = Typography.labelLarge)
        }
        Spacer(Modifier.height(32.dp))
        SectionLabel("My Stats")
        Spacer(Modifier.height(12.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                CardComponent(text = "Quizzes Played", number = user.gamesPlayed)
            }
            Box(modifier = Modifier.weight(1f)) {
                CardComponent(text = "Total Points", number = user.totalPoints)
            }
        }
        Spacer(Modifier.height(8.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Box(modifier = Modifier.weight(1f)) {
                CardComponent(text = "Accuracy", number = user.accuracyPercentage, unit = "%")
            }
            Box(modifier = Modifier.weight(1f)) {
                CardComponent(text = "Avg. Points", number = user.averagePointsPerGame)
            }
        }
        Spacer(Modifier.height(32.dp))
        SectionLabel("Your Streak")
        Spacer(Modifier.height(12.dp))
        StreakCard(gamesPlayed = user.gamesPlayed)
        Spacer(Modifier.height(32.dp))
        SectionLabel("Achievements")
        Spacer(Modifier.height(12.dp))
        AchievementsSection(user = user)
    }
}

@Composable
private fun AvatarSection(
    initials: String,
    onChangeAvatar: () -> Unit
) {
    Box(contentAlignment = Alignment.BottomEnd) {
        Box(
            modifier = Modifier
                .size(100.dp)
                .clip(CircleShape)
                .background(GreenDark)
                .border(width = 3.dp, color = GreenPrimary, shape = CircleShape),
            contentAlignment = Alignment.Center
        ) {
            Text(
                text = initials.ifBlank { "?" },
                color = Color.White,
                style = Typography.headlineLarge
            )
        }
        Box(
            modifier = Modifier
                .size(30.dp)
                .clip(CircleShape)
                .background(GreenPrimary),
            contentAlignment = Alignment.Center
        ) {
        }
    }
}

@Composable
private fun StreakCard(gamesPlayed: Int) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(size = 20.dp),
        colors = CardDefaults.cardColors(containerColor = Surface)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(text = "🔥", style = Typography.headlineLarge)
                Spacer(Modifier.width(12.dp))
                Column {
                    Text(
                        text = "$gamesPlayed games played",
                        color = TextPrimary,
                        style = Typography.titleMedium
                    )
                    Text(
                        text = "Keep it up! Play daily to build your streak.",
                        color = TextTertiary,
                        style = Typography.bodySmall
                    )
                }
            }
            Spacer(Modifier.height(16.dp))
            val days = listOf("Mon", "Tue", "Wed", "Thu", "Fri", "Sat", "Sun")
            val activeDays = (gamesPlayed % 7).coerceAtLeast(if (gamesPlayed > 0) 1 else 0)
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                days.forEachIndexed { index, day ->
                    val isActive = index < activeDays
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Box(
                            modifier = Modifier
                                .size(36.dp)
                                .clip(CircleShape)
                                .background(
                                    if (isActive) GreenPrimary else SurfaceVariant
                                ),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = if (isActive) "✓" else "",
                                color = Color.White,
                                style = Typography.bodySmall
                            )
                        }
                        Spacer(Modifier.height(4.dp))
                        Text(
                            text = day,
                            color = if (isActive) GreenPrimary else TextTertiary,
                            style = Typography.bodySmall
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AchievementsSection(user: User) {
    val achievements = listOf(
        Achievement("🎮", "First Game",   "Play your first quiz",              user.gamesPlayed >= 1),
        Achievement("💯", "Century",      "Earn 100 total points",             user.totalPoints >= 100),
        Achievement("🎯", "Sharp Shooter","Reach 80% accuracy",               user.accuracyPercentage >= 80),
        Achievement("🏆", "Quiz Master",  "Play 10 or more quizzes",          user.gamesPlayed >= 10),
        Achievement("⚡", "Speed Demon",  "Earn 500 total points",             user.totalPoints >= 500),
        Achievement("🔥", "On Fire",      "Play 7 quizzes in a row",          user.gamesPlayed >= 7)
    )

    Column(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
        achievements.chunked(2).forEach { pair ->
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                pair.forEach { achievement ->
                    AchievementCard(achievement = achievement, modifier = Modifier.weight(1f))
                }
                if (pair.size == 1) Spacer(Modifier.weight(1f))
            }
        }
    }
}

@Composable
private fun AchievementCard(achievement: Achievement, modifier: Modifier = Modifier) {
    Card(
        modifier = modifier,
        shape = RoundedCornerShape(size = 16.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (achievement.unlocked) Surface else SurfaceVariant
        ),
        border = if (achievement.unlocked)
            androidx.compose.foundation.BorderStroke(1.dp, GreenPrimary.copy(alpha = 0.4f))
        else null
    ) {
        Column(
            modifier = Modifier.padding(14.dp)
        ) {
            Text(
                text = achievement.emoji,
                style = Typography.headlineMedium,
                color = if (achievement.unlocked) TextPrimary else TextTertiary
            )
            Spacer(Modifier.height(6.dp))
            Text(
                text = achievement.title,
                color = if (achievement.unlocked) TextPrimary else TextTertiary,
                style = Typography.bodyMedium
            )
            Text(
                text = achievement.description,
                color = TextTertiary,
                style = Typography.bodySmall
            )
            Spacer(Modifier.height(6.dp))
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(50))
                    .background(
                        if (achievement.unlocked) GreenPrimary.copy(alpha = 0.15f)
                        else SurfaceVariant
                    )
                    .padding(horizontal = 8.dp, vertical = 2.dp)
            ) {
                Text(
                    text = if (achievement.unlocked) "Unlocked" else "Locked",
                    color = if (achievement.unlocked) GreenPrimary else TextTertiary,
                    style = Typography.bodySmall
                )
            }
        }
    }
}

private data class Achievement(
    val emoji: String,
    val title: String,
    val description: String,
    val unlocked: Boolean
)

@Composable
private fun SectionLabel(text: String) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = text,
            color = TextPrimary,
            style = Typography.titleLarge
        )
        Spacer(Modifier.weight(1f))
    }
}

private fun formatDate(timestamp: Long): String {
    if (timestamp == 0L) return "N/A"
    val millis = if (timestamp < 1_000_000_000_000L) timestamp * 1000L else timestamp
    return SimpleDateFormat("MMM yyyy", Locale.getDefault()).format(Date(millis))
}

@Preview(showSystemUi = true)
@Composable
private fun ProfileScreenPrev() {
    val sampleUser = User(
        displayName = "Nikola Vujovic",
        username = "nikolav",
        email = "nikola@example.com",
        totalPoints = 1250,
        gamesPlayed = 8,
        correctAnswers = 64,
        totalAnswers = 80,
        createdAt = 1_700_000_000L
    )
    ProfileContent(
        user = sampleUser,
        onEditProfile = {},
        onChangeAvatar = {}
    )
}
