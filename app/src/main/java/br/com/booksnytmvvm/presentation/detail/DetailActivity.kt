package br.com.booksnytmvvm.presentation.detail

import android.os.Bundle
import br.com.booksnytmvvm.R
import br.com.booksnytmvvm.presentation.base.BaseActivity
import kotlinx.android.synthetic.main.activity_detail.*
import kotlinx.android.synthetic.main.toolbar.*

class DetailActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)
        setupToolbar(toolbarDetail, R.string.name_toolbar_detail_activity, true)

        textAuthorDetail.text = intent.getStringExtra(STRING_AUTHOR)
        textDescriptionDetail.text = intent.getStringExtra(STRING_DESCRIPTION)
    }

    companion object{
        private const val STRING_AUTHOR = "STRING_AUTHOR"
        private const val STRING_DESCRIPTION = "STRING_DESCRIPTION"
    }
}
