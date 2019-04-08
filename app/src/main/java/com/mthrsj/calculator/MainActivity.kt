package com.mthrsj.calculator

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var appToolbar: Toolbar = findViewById(toolbar.id)
        setSupportActionBar(appToolbar)

        basicButton.setOnClickListener {
            navigateBasic()
        }
        complexButton.setOnClickListener {
            navigateComplex()
        }
    }

    private fun navigateComplex() {
        var it = Intent(this, ComplexCalculator::class.java)
        startActivity(it)
    }

    private fun navigateBasic() {
        var it = Intent(this, BasicCalculator::class.java)
        startActivity(it)
    }

    override fun onOptionsItemSelected(item: MenuItem) = when (item.title) {
        "Basic" -> {
            navigateBasic()
            true
        }

        "Complex" -> {
            navigateComplex()
            true
        }

        else -> {
            // If we got here, the user's action was not recognized.
            // Invoke the superclass to handle it.
            super.onOptionsItemSelected(item)
        }
    }
}
