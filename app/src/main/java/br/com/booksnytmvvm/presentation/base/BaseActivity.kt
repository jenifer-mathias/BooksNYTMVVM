package br.com.booksnytmvvm.presentation.base

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toolbar

open class BaseActivity : AppCompatActivity() {

   protected fun setupToolbar(toolbar: androidx.appcompat.widget.Toolbar, title: Int, showBackButton: Boolean = false) {
       toolbar.title = getString(title)
       setSupportActionBar(toolbar)
       if (showBackButton) {
           supportActionBar?.setDisplayHomeAsUpEnabled(true)
       }
   }
}
