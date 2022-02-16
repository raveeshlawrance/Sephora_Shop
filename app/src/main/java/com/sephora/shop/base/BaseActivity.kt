package com.sephora.shop.base

import android.app.ProgressDialog
import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StrikethroughSpan
import android.text.style.StyleSpan
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import java.util.*

abstract class BaseActivity<DB : ViewDataBinding> : AppCompatActivity() {
    private lateinit var baseActivity: BaseActivity<DB>
    lateinit var mTextViewScreenTitle: TextView
    lateinit var mImageButtonBack: ImageButton
    lateinit var mProgressDialog: ProgressDialog

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(this.layoutId())
        baseActivity = this
        mProgressDialog = ProgressDialog(this)
    }

    val binding by lazy {
        DataBindingUtil.setContentView(this, layoutId()) as DB
    }

    fun setScreenTitle(resId: Int) {
        mTextViewScreenTitle.text = getString(resId)
    }

    fun setScreenTitle(title: String) {
        mTextViewScreenTitle.text = title
    }

    fun getBackButton(): ImageButton {
        return mImageButtonBack;
    }

    fun showProgressDialog(message : String) {
        mProgressDialog.setMessage(message ?: "Loading..")
        mProgressDialog.setCancelable(false)
        mProgressDialog.isIndeterminate = true
        if(!mProgressDialog.isShowing) {
            mProgressDialog.show()
        }
    }

    fun dismissProgressDialog() {
        if (mProgressDialog != null && mProgressDialog.isShowing) {
            mProgressDialog.dismiss()
        }
    }

    fun showAlert(title: String, body: String?) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(title)
        builder.setMessage(body)
        builder.setPositiveButton(android.R.string.yes) { dialog, which -> }
        builder.setNegativeButton(android.R.string.no) { dialog, which -> }
        builder.show()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    fun checkNetworkConnection(): Boolean {
        val connectivityManager =
            getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        if (connectivityManager != null) {
            val capabilities =
                connectivityManager.getNetworkCapabilities(connectivityManager?.activeNetwork)
            if (capabilities != null) {
                if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_WIFI)) {
                    return true
                } else if (capabilities.hasTransport(NetworkCapabilities.TRANSPORT_ETHERNET)) {
                    return true
                }
            }
        }
        return false
    }

    fun getSpannableText(offerPrice : String?, originalPrice: Int?, discPrice: Int?): Spannable? {
        val spannable = SpannableString(offerPrice)
        spannable.setSpan(
            StrikethroughSpan(),
            0, originalPrice.toString().length + 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            ForegroundColorSpan(Color.RED),
            originalPrice.toString().length + 4 + discPrice.toString().length,
            offerPrice?.length!!,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannable.setSpan(
            StyleSpan(Typeface.BOLD),
            originalPrice.toString().length + 2,
            originalPrice.toString().length + 2 + discPrice.toString().length + 2,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        return spannable
    }

    @LayoutRes
    abstract fun layoutId() : Int
}