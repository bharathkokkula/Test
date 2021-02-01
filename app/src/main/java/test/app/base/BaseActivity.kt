package test.app.base

import android.app.Dialog
import android.content.Context
import android.content.pm.PackageManager
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import test.app.R
import test.app.utils.NetworkUtils.isNetworkConnected
import io.github.inflationx.viewpump.ViewPumpContextWrapper

abstract class BaseActivity :
    AppCompatActivity(), BaseFragment.Callback {
    private var mProgressDialog: Dialog? = null
    override fun onFragmentAttached() {}
    override fun onFragmentDetached(tag: String?) {}
    var alertDialog: AlertDialog? = null
    override fun attachBaseContext(newBase: Context) {
        super.attachBaseContext(ViewPumpContextWrapper.wrap(newBase))
    }

    fun hasPermission(permission: String?): Boolean {
        return Build.VERSION.SDK_INT < Build.VERSION_CODES.M ||
                checkSelfPermission(permission!!) == PackageManager.PERMISSION_GRANTED
    }
    fun requestPermissionsSafely(
        permissions: Array<String?>?,
        requestCode: Int
    ) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            requestPermissions(permissions!!, requestCode)
        }
    }
    fun showLoading() {
        if(isNetworkConnected) {
            if(mProgressDialog!=null ) {
                hideLoading()
                mProgressDialog = null
            }
            mProgressDialog = Dialog(this)
            mProgressDialog?.show()
            if (mProgressDialog?.window != null) {
                mProgressDialog?.window!!
                    .setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            }
            mProgressDialog?.setContentView(R.layout.progress_dialog)
            mProgressDialog?.setCancelable(false)
            mProgressDialog?.setCanceledOnTouchOutside(false)
        } else {
            hideLoading()
            ShowcheckInternetconnection()
        }
    }
    fun ShowcheckInternetconnection() {
        if (alertDialog != null && alertDialog!!.isShowing) {
            alertDialog!!.dismiss()
        }
        val dialogBuilder = AlertDialog.Builder(this)
        val dialogView: View =LayoutInflater.from(this).inflate(R.layout.checkinternet, null)
        dialogBuilder.setView(dialogView)
        val tvHeading = dialogView.findViewById<TextView>(R.id.tvHeading)
        //val tvNo = dialogView.findViewById<TextView>(R.id.tvNo)
        val tvYes = dialogView.findViewById<TextView>(R.id.tvYes)
        val tvInfo = dialogView.findViewById<TextView>(R.id.tvInfo)
        val ivLogo = dialogView.findViewById<ImageView>(R.id.ivLogo)
        alertDialog = dialogBuilder.create()
        //tvHeading.text = header
        tvHeading.textSize = 20f
       // tvInfo.text = message
        if (!this.isFinishing)
            alertDialog!!.show()
        tvInfo.textSize = 18f
       // ivLogo.setBackgroundResource(resourceid)
       // tvNo.visibility = View.GONE
        tvHeading.visibility = View.VISIBLE
        ivLogo.visibility = View.VISIBLE
        tvYes.setOnClickListener { alertDialog!!.dismiss() }
       // tvNo.setOnClickListener { alertDialog!!.dismiss() }
    }

    fun hideLoading() {
        if (mProgressDialog != null && mProgressDialog!!.isShowing) {
            mProgressDialog!!.cancel()
        }
    }
    fun CheckConnection() {
        val cm = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val isConnected: Boolean = cm.isActiveNetworkMetered
    }

    fun hideKeyboard() {
        val view = this.currentFocus
        if (view != null) {
            val imm =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(view.windowToken, 0)
        }
    }

/*    fun getColorWithAlpha(color: Int, ratio: Float): Int {
        var newColor = 0
        val alpha = Math.round(Color.alpha(color) * ratio)
        val r = Color.red(color)
        val g = Color.green(color)
        val b = Color.blue(color)
        newColor = Color.argb(alpha, r, g, b)
        return newColor
    }*/

    /*    public  void Customtoast(String msg)
    {
        LayoutInflater li = getLayoutInflater();
        View layout = li.inflate(R.layout.toast_cell,(ViewGroup) findViewById(R.id.custom_toast_layout));
        TextView textView = layout.findViewById(R.id.tvText);

        textView.setText(msg);

        Toast toast = new Toast(BaseActivity.this);
        toast.setDuration(Toast.LENGTH_SHORT);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.setView(layout);//setting the view of custom toast layout
        toast.show();
    }*/
    val isNetworkConnected: Boolean
        get() = isNetworkConnected(applicationContext)

    fun openActivityOnTokenExpire() { // startActivity(LoginActivity.newIntent(this));
        finish()
    }
}