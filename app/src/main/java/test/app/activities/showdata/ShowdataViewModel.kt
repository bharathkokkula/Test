package test.app.activities.showdata

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import test.app.base.BaseViewModel
import test.app.doa.ImageData
import test.app.retrofit.ApiClient
import test.app.utils.AppPreferencesHelper


class ShowdataViewModel : BaseViewModel<ShowdataNavigator?>() {
    private lateinit var appPreferencesHelper: AppPreferencesHelper
    fun GetData(appPreferencesHelper: AppPreferencesHelper) {
        this.appPreferencesHelper = appPreferencesHelper
    }

    fun getimage() {
        navigator?.ShowLoading()
   compositeDisposable.add(
           ApiClient.setBaseUrl().getImage().subscribeOn(Schedulers.io())
               .observeOn(AndroidSchedulers.mainThread()).subscribe({responce->
                   navigator?.HideLoading()
                   val res: ImageData? = responce.body()
                   if(res?.status.equals("Success")) {
                       res?.image?.let { navigator?.setImage(it) }
                   }
               },{
                   navigator?.HideLoading()
                   })
           )
    }
}