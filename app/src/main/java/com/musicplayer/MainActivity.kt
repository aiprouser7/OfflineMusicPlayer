package com.musicplayer

import android.content.Context
import android.media.MediaPlayer
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.io.File

data class Song(val title: String, val path: String)

@Composable
fun MusicPlayerApp(viewModel: MusicPlayerViewModel) {
      val isDarkTheme by viewModel.isDarkTheme.collectAsState()
          val songs by viewModel.songs.collectAsState()
              val currentSong by viewModel.currentSong.collectAsState()
                  val isPlaying by viewModel.isPlaying.collectAsState()
                      val currentPosition by viewModel.currentPosition.collectAsState()
                          val duration by viewModel.duration.collectAsState()

                              MaterialTheme(
                                        colorScheme = if (isDarkTheme) darkColorScheme() else lightColorScheme()
                                            ) {
                                        Surface(
                                                      modifier = Modifier.fillMaxSize(),
                                                      color = MaterialTheme.colorScheme.background
                                                  ) {
                                                      Column(
                                                                        modifier = Modifier
                                                                            .fillMaxSize()
                                                                                                .padding(16.dp)
                                                                                                            ) {
                                                                        // Header
                                                                        TopAppBar(
                                                                                              title = { Text("Offline Music Player", fontWeight = FontWeight.Bold) },
                                                                                              actions = {
                                                                                                                        IconButton(onClick = { viewModel.toggleTheme() }) {
                                                                                                                                                      Icon(
                                                                                                                                                                                        if (isDarkTheme) Icons.Default.LightMode else Icons.Default.DarkMode,
                                                                                                                                                                                        contentDescription = "Toggle Theme"
                                                                                                                                                                                    )
                                                                                                                        }
                                                                                              },
                                                                                              colors = TopAppBarDefaults.topAppBarColors(
                                                                                                                        containerColor = MaterialTheme.colorScheme.primaryContainer
                                                                                                                    )
                                                                                                              )

                                                                                        Spacer(modifier = Modifier.height(16.dp))

                                                                                                        // Now Playing Card
                                                                                                                        if (currentSong != null) {
                                                                                                                                              NowPlayingCard(currentSong!!, isPlaying, viewModel)
                                                                                                                                                                  Spacer(modifier = Modifier.height(16.dp))
                                                                                                                        }
                                                                                                                        
                                                                                                                                        // Progress Bar
                                                                                                                                                        Column(modifier = Modifier.fillMaxWidth()) {
                                                                                                                                                                              Slider(
                                                                                                                                                                                                        value = currentPosition.toFloat(),
                                                                                                                                                                                                        onValueChange = { viewModel.seekTo(it.toLong()) },
                                                                                                                                                                                                        valueRange = 0f..(duration.coerceAtLeast(1).toFloat()),
                                                                                                                                                                                                        modifier = Modifier.fillMaxWidth()
                                                                                                                                                                                                                            )
                                                                                                                                                                                                  Row(
                                                                                                                                                                                                                            modifier = Modifier
                                                                                                                                                                                                                                .fillMaxWidth()
                                                                                                                                                                                                                                                            .padding(horizontal = 8.dp),
                                                                                                                                                                                                                            horizontalArrangement = Arrangement.SpaceBetween
                                                                                                                                                                                                                        ) {
                                                                                                                                                                                                                            Text(formatTime(currentPosition), fontSize = 12.sp)
                                                                                                                                                                                                                                                    Text(formatTime(duration), fontSize = 12.sp)
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                  }
                                                                                                                                                        
                                                                                                                                                                        Spacer(modifier = Modifier.height(16.dp))
                                                                                                                                                                        
                                                                                                                                                                                        // Player Controls
                                                                                                                                                                                                        PlayerControls(isPlaying, viewModel)
                                                                                                                                                                                                        
                                                                                                                                                                                                                        Spacer(modifier = Modifier.height(16.dp))
                                                                                                                                                                                                                        
                                                                                                                                                                                                                                        // Songs List
                                                                                                                                                                                                                                                        Text("Playlist", fontWeight = FontWeight.Bold, fontSize = 18.sp)
                                                                                                                                                                                                                                                                        Spacer(modifier = Modifier.height(8.dp))
                                                                                                                                                                                                                                                                                        LazyColumn(modifier = Modifier.fillMaxWidth()) {
                                                                                                                                                                                                                                                                                                              items(songs) { song ->
                                                                                                                                                                                                                                                                                                                                        SongItem(song, currentSong == song, viewModel)
                                                                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                                              }
                                                                                                                                                                                                                                                                                                    }
                                        }
                              }
}

@Composable
fun NowPlayingCard(song: Song, isPlaying: Boolean, viewModel: MusicPlayerViewModel) {
      Card(
                modifier = Modifier
                    .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                    ) {
                Column(
                              modifier = Modifier
                                  .fillMaxWidth()
                                                  .background(MaterialTheme.colorScheme.surfaceVariant)
                                                                  .padding(16.dp),
                              horizontalAlignment = Alignment.CenterHorizontally
                          ) {
                              Icon(
                                                Icons.Default.Music,
                                                contentDescription = "Now Playing",
                                                modifier = Modifier.size(64.dp),
                                                tint = MaterialTheme.colorScheme.primary
                                            )
                                          Spacer(modifier = Modifier.height(8.dp))
                                                      Text(
                                                                        song.title,
                                                                        fontWeight = FontWeight.Bold,
                                                                        fontSize = 16.sp,
                                                                        maxLines = 1,
                                                                        overflow = TextOverflow.Ellipsis
                                                                    )
                                                                  Text(
                                                                                    if (isPlaying) "Now Playing" else "Paused",
                                                                                    fontSize = 12.sp,
                                                                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                                                                )
                }
      }
}

@Composable
fun PlayerControls(isPlaying: Boolean, viewModel: MusicPlayerViewModel) {
      Row(
                modifier = Modifier
                    .fillMaxWidth()
                                .clip(RoundedCornerShape(12.dp))
                                            .background(MaterialTheme.colorScheme.primaryContainer)
                                                        .padding(16.dp),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {
                IconButton(onClick = { viewModel.playPrevious() }) {
                              Icon(Icons.Default.SkipPrevious, contentDescription = "Previous")
                }

                        FloatingActionButton(
                                      onClick = { 
                                                        if (isPlaying) viewModel.pause() else viewModel.play() 
                                      },
                                      containerColor = MaterialTheme.colorScheme.primary,
                                      modifier = Modifier.size(56.dp)
                                              ) {
                                      Icon(
                                                        if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                                                        contentDescription = if (isPlaying) "Pause" else "Play",
                                                        tint = Color.White
                                                    )
                        }

                                IconButton(onClick = { viewModel.playNext() }) {
                                              Icon(Icons.Default.SkipNext, contentDescription = "Next")
                                }
      }
}

@Composable
fun SongItem(song: Song, isCurrentSong: Boolean, viewModel: MusicPlayerViewModel) {
      Card(
                modifier = Modifier
                    .fillMaxWidth()
                                .padding(vertical = 4.dp)
                                            .clip(RoundedCornerShape(8.dp)),
                colors = CardDefaults.cardColors(
                              containerColor = if (isCurrentSong) 
                                              MaterialTheme.colorScheme.primaryContainer 
                              else 
                                  MaterialTheme.colorScheme.surface
                          )
                    ) {
                Row(
                              modifier = Modifier
                                  .fillMaxWidth()
                                                  .padding(12.dp),
                              verticalAlignment = Alignment.CenterVertically,
                              horizontalArrangement = Arrangement.SpaceBetween
                          ) {
                              Column(modifier = Modifier.weight(1f)) {
                                                Text(
                                                                      song.title,
                                                                      fontWeight = if (isCurrentSong) FontWeight.Bold else FontWeight.Normal,
                                                                      maxLines = 1,
                                                                      overflow = TextOverflow.Ellipsis
                                                                  )
                                                                Text(
                                                                                      song.path,
                                                                                      fontSize = 12.sp,
                                                                                      color = MaterialTheme.colorScheme.onSurfaceVariant,
                                                                                      maxLines = 1,
                                                                                      overflow = TextOverflow.Ellipsis
                                                                                  )
                              }
                                          IconButton(onClick = { viewModel.playSong(song) }) {
                                                            Icon(
                                                                                  Icons.Default.PlayArrow,
                                                                                  contentDescription = "Play",
                                                                                  tint = MaterialTheme.colorScheme.primary
                                                                              )
                                          }
                }
      }
}

fun formatTime(millis: Long): String {
      val seconds = millis / 1000
      val minutes = seconds / 60
      val remainingSeconds = seconds % 60
      return String.format("%02d:%02d", minutes, remainingSeconds)
}

class MusicPlayerViewModel(context: Context) {
      private var mediaPlayer: MediaPlayer? = null
      private var allSongs: List<Song> = emptyList()
          private var currentSongIndex = 0

      val isDarkTheme = androidx.compose.runtime.mutableStateOf(false)
          val songs = androidx.compose.runtime.mutableStateOf<List<Song>>(emptyList())
              val currentSong = androidx.compose.runtime.mutableStateOf<Song?>(null)
                  val isPlaying = androidx.compose.runtime.mutableStateOf(false)
                      val currentPosition = androidx.compose.runtime.mutableStateOf(0L)
                          val duration = androidx.compose.runtime.mutableStateOf(0L)

                              private val updateThread = Thread {
                                        while (true) {
                                                      try {
                                                                        Thread.sleep(500)
                                                                                        if (mediaPlayer != null && mediaPlayer!!.isPlaying) {
                                                                                                              currentPosition.value = mediaPlayer!!.currentPosition.toLong()
                                                                                        }
                                                      } catch (e: Exception) {
                                                                        e.printStackTrace()
                                                      }
                                        }
                              }.apply { isDaemon = true; start() }

                                  init {
                                            loadSongs(context)
                                  }

                                      private fun loadSongs(context: Context) {
                                                val musicDir = File("/storage/emulated/0/Music")
                                                        val songList = mutableListOf<Song>()

                                                                        if (musicDir.exists() && musicDir.isDirectory) {
                                                                                      musicDir.listFiles()?.filter { 
                                                                                                        it.extension in listOf("mp3", "wav", "flac", "ogg") 
                                                                                      }?.forEach { file ->
                                                                                                        songList.add(Song(file.nameWithoutExtension, file.absolutePath))
                                                                                      }
                                                                        }

                                                                                        allSongs = songList
                                                songs.value = songList
                                      }

                                          fun playSong(song: Song) {
                                                    try {
                                                                  mediaPlayer?.release()
                                                                              mediaPlayer = MediaPlayer().apply {
                                                                                                setDataSource(song.path)
                                                                                                                prepare()
                                                                                                                                start()
                                                                                                                                                duration.value = duration.toLong()
                                                                              }
                                                                                          currentSong.value = song
                                                                  isPlaying.value = true
                                                    } catch (e: Exception) {
                                                                  e.printStackTrace()
                                                    }
                                          }

                                              fun play() {
                                                        if (mediaPlayer == null && currentSong.value != null) {
                                                                      playSong(currentSong.value!!)
                                                        } else {
                                                                      mediaPlayer?.start()
                                                                                  isPlaying.value = true
                                                        }
                                              }

                                                  fun pause() {
                                                            mediaPlayer?.pause()
                                                                    isPlaying.value = false
                                                  }

                                                      fun seekTo(position: Long) {
                                                                mediaPlayer?.seekTo(position.toInt())
                                                                        currentPosition.value = position
                                                      }

                                                          fun playNext() {
                                                                    if (allSongs.isNotEmpty()) {
                                                                                  currentSongIndex = (currentSongIndex + 1) % allSongs.size
                                                                                  playSong(allSongs[currentSongIndex])
                                                                    }
                                                          }

                                                              fun playPrevious() {
                                                                        if (allSongs.isNotEmpty()) {
                                                                                      currentSongIndex = (currentSongIndex - 1 + allSongs.size) % allSongs.size
                                                                                      playSong(allSongs[currentSongIndex])
                                                                        }
                                                              }

                                                                  fun toggleTheme() {
                                                                            isDarkTheme.value = !isDarkTheme.value
                                                                  }

                                                                      fun cleanup() {
                                                                                mediaPlayer?.release()
                                                                                        mediaPlayer = null
                                                                      }
}

class MainActivity : ComponentActivity() {
      private lateinit var viewModel: MusicPlayerViewModel

      override fun onCreate(savedInstanceState: Bundle?) {
                super.onCreate(savedInstanceState)
                        viewModel = MusicPlayerViewModel(this)
                                setContent {
                                              MusicPlayerApp(viewModel)
                                }
      }

          override fun onDestroy() {
                    super.onDestroy()
                            viewModel.cleanup()
          }
}
