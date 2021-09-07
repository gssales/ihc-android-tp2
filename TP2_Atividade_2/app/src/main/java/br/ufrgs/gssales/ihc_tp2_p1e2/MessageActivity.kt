package br.ufrgs.gssales.ihc_tp2_p1e2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufrgs.gssales.ihc_tp2_p1e2.databinding.ActivityMessageBinding

const val EXTRA_MESSAGE = "MY_MESSAGE"

class MessageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMessageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMessageBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.txtMessage.text = intent.getStringExtra(EXTRA_MESSAGE)
    }
}