package br.ufrgs.gssales.ihc_tp2_p1e2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import br.ufrgs.gssales.ihc_tp2_p1e2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {
            val i = Intent(this, MessageActivity::class.java).apply {
                putExtra(EXTRA_MESSAGE, binding.edtMessage.text.toString())
            }
            startActivity(i)
        }
    }
}