package br.ufrgs.gssales.ihc_tp2_p1e1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.core.widget.doAfterTextChanged
import br.ufrgs.gssales.ihc_tp2_p1e1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val model: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.edtNumber1.doAfterTextChanged {
            model.number1 = it.toString().toFloatOrNull()
        }

        binding.edtNumber2.doAfterTextChanged {
            model.number2 = it.toString().toFloatOrNull()
        }

        binding.btnSum.setOnClickListener {
            model.sumNumbers()
        }

        model.result.observe(this, {
            binding.txtResult.text = resources.getString(R.string.result_template, it?.toString() ?: "")
        })
        binding.txtResult.text = resources.getString(R.string.result_template, "")
    }


}