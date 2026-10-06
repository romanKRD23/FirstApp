package ru.netology.firstapp

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import ru.netology.firstapp.databinding.ActivityMainBinding
import ru.netology.firstapp.dto.Post

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val post = Post(
            id = 1,
            author = "Нетология. Университет интернет-профессий будущего",
            content = "Привет, это новая Нетология! Когда-то Нетология начиналась с интенсивов по онлайн-маркетингу. Затем появились курсы по дизайну, разработке, аналитике и управлению. Мы растём сами и помогаем расти студентам: от новичков до уверенных профессионалов. Но самое важное остаётся с нами: мы верим, что в каждом уже есть сила, которая заставляет хотеть больше, целиться выше, бежать быстрее. Наша миссия — помочь встать на путь роста и начать цепочку перемен → http://netolo.gy/fyb",
            published = "21 мая в 18:36",
            likes = 999,
            share = 999999,
            view = 300,
            likedByMe = false,
            postByShare = false,
        )

        fun formatShortNumber(value: Int): String {
            return when {
                value < 1_000 -> value.toString()
                value < 10_000 -> "%.1fK".format(value / 1_000.0)
                value < 1_000_000 -> "\${value / 1_000}K"
                else -> "%.1fM".format(value / 1_000_000.0)
            }
        }

        with(binding) {
            avatar.setImageResource(R.drawable.ic_netology_48dp)
            author.text = post.author
            published.text = post.published
            content.text = post.content
            viewsCount.text = post.view.toString()
            shareCount.text = post.share.toString()

            if (post.likedByMe) {
                imageLike?.setImageResource(R.drawable.ic_liked_24)
            }
            likeCount?.text = post.likes.toString()

            root.setOnClickListener {
                Log.d("stuff", "stuff")
            }

            avatar.setOnClickListener {
                Log.d("stuff", "avatar")
            }

            imageLike?.setOnClickListener {
                Log.d("stuff", "like")
                post.likedByMe = !post.likedByMe
                imageLike.setImageResource(
                    if (post.likedByMe) R.drawable.ic_liked_24 else R.drawable.ic_like_24
                )
                if (post.likedByMe) post.likes++ else post.likes--
                likeCount?.text = formatShortNumber(post.likes)
            }
            imageShare?.setOnClickListener {
                Log.d("stuff", "Share")
                post.share++
                shareCount?.text = formatShortNumber(post.share)
            }
        }
    }
}