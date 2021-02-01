package test.app.base

import android.content.Context
import androidx.fragment.app.Fragment

abstract class BaseFragment :
    Fragment() {
    var baseActivity: BaseActivity? = null
    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is BaseActivity) {
            val activity = context
            baseActivity = activity
            activity.onFragmentAttached()
        }
    }


/*    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewDataBinding = DataBindingUtil.inflate<T>(inflater, layoutId, container, false)
        mRootView = viewDataBinding!!.root
        return mRootView
    }*/

    override fun onDetach() {
        baseActivity = null
        super.onDetach()
    }

/*    override fun onViewCreated(
        view: View,
        savedInstanceState: Bundle?
    ) {
        super.onViewCreated(view, savedInstanceState)
        viewDataBinding!!.setVariable(bindingVariable, mViewModel)
        viewDataBinding!!.lifecycleOwner = this
        viewDataBinding!!.executePendingBindings()
    }*/

    fun hideKeyboard() {
        if (baseActivity != null) {
            baseActivity!!.hideKeyboard()
        }
    }
    fun showLoading() {
        if (baseActivity != null) {
            baseActivity!!.showLoading()
        }
    }
    fun hideLoading() {
        if (baseActivity != null) {
            baseActivity!!.hideLoading()
        }
    }


    val isNetworkConnected: Boolean
        get() = baseActivity != null && baseActivity!!.isNetworkConnected

    fun openActivityOnTokenExpire() {
        if (baseActivity != null) {
            baseActivity!!.openActivityOnTokenExpire()
        }
    }

    interface Callback {
        fun onFragmentAttached()
        fun onFragmentDetached(tag: String?)
    }
}