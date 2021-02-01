package test.app.activities.showdata

import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.Target
import test.app.R
import test.app.base.BaseActivity
import test.app.databinding.ActivityLoginBinding
import test.app.utils.AppPreferencesHelper

class ShowdataActivity :
    BaseActivity(),
    ShowdataNavigator {
    private lateinit var binding: ActivityLoginBinding
    private var viewModel: ShowdataViewModel? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this).get<ShowdataViewModel>(ShowdataViewModel::class.java)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        viewModel?.setNavigator(this)
        viewModel?.GetData(AppPreferencesHelper(this))
        binding.viewModel = viewModel
        binding.executePendingBindings()
    }
    override fun ShowLoading() {
        showLoading()
    }

    override fun HideLoading() {
        hideLoading()
    }

    override fun setImage(it: String) {
        Glide.with(this)
            .load(it).into( binding.ivImage)
    }

}

