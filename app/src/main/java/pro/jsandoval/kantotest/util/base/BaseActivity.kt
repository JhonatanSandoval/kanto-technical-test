package pro.jsandoval.kantotest.util.base

import android.os.Bundle
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pro.jsandoval.kantotest.R
import pro.jsandoval.kantotest.util.ext.hideKeyboard

abstract class BaseActivity<VB : ViewDataBinding>(@LayoutRes private val layoutResId: Int) : AppCompatActivity() {

    protected lateinit var binding: VB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, layoutResId)
        hideKeyboard()
        init()
        initViewModel()
    }

    protected abstract fun init()

    protected abstract fun initViewModel()

    fun simpleErrorMessage(message: String?) {
        AlertDialog.Builder(this)
            .setMessage(message)
            .setPositiveButton(R.string.ok) { dialogInterface, _ -> dialogInterface.dismiss() }
            .show()
    }

    fun simpleErrorMessage(@StringRes stringRes: Int) {
        AlertDialog.Builder(this)
            .setPositiveButton(R.string.ok) { dialogInterface, _ -> dialogInterface.dismiss() }
            .setMessage(stringRes)
            .show()
    }

    fun simpleToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    fun simpleToast(@StringRes stringResId: Int) {
        Toast.makeText(this, stringResId, Toast.LENGTH_LONG).show()
    }

    @MainThread
    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T?) -> Unit): Observer<T> {
        val wrappedObserver = Observer<T> { value ->
            onChanged.invoke(value)
        }
        observe(this@BaseActivity, wrappedObserver)
        return wrappedObserver
    }

    @MainThread
    protected inline fun <T> LiveData<T?>.observeNotNull(crossinline onChanged: (T) -> Unit): Observer<T?> {
        val wrappedObserver = Observer<T?> { value ->
            if (value != null) {
                onChanged.invoke(value)
            }
        }
        observe(this@BaseActivity, wrappedObserver)
        return wrappedObserver
    }

}