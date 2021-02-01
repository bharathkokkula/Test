package test.app.base

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import java.lang.ref.WeakReference

abstract class BaseViewModel<N> protected constructor() : ViewModel() {
    val compositeDisposable: CompositeDisposable
    private var mNavigator: WeakReference<N>? = null
    override fun onCleared() {
        compositeDisposable.dispose()
        super.onCleared()
    }
    val navigator: N?
        get() = mNavigator!!.get()

    fun setNavigator(navigator: N) {
        mNavigator = WeakReference(navigator)
    }

    init {
        compositeDisposable = CompositeDisposable()
    }
}