package pro.jsandoval.kantotest.util.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.annotation.MainThread
import androidx.annotation.StringRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import pro.jsandoval.kantotest.util.ext.hideKeyboard

abstract class BaseFragment<VB : ViewDataBinding>(@LayoutRes val layoutRes: Int) : Fragment() {

    protected lateinit var binding: VB
    protected val parentActivity: BaseActivity<*>?
        get() = activity as BaseActivity<*>

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, layoutRes, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        activity?.hideKeyboard()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
        initViewModel()
    }

    protected fun simpleErrorMessage(message: String?) {
        parentActivity?.simpleErrorMessage(message)
    }

    protected fun simpleErrorMessage(@StringRes stringRes: Int) {
        parentActivity?.simpleErrorMessage(stringRes)
    }

    protected fun simpleToast(message: String) {
        parentActivity?.simpleToast(message)
    }

    protected fun simpleToast(@StringRes stringResId: Int) {
        parentActivity?.simpleToast(stringResId)
    }

    protected fun hideKeyboard() {
        parentActivity?.hideKeyboard()
    }

    abstract fun init()

    abstract fun initViewModel()

    @MainThread
    protected inline fun <T> LiveData<T>.observe(crossinline onChanged: (T?) -> Unit): Observer<T> {
        val wrappedObserver = Observer<T> { value ->
            onChanged.invoke(value)
        }
        observe(viewLifecycleOwner, wrappedObserver)
        return wrappedObserver
    }

    @MainThread
    protected inline fun <T> LiveData<T?>.observeNotNull(crossinline onChanged: (T) -> Unit): Observer<T?> {
        val wrappedObserver = Observer<T?> { value ->
            if (value != null) {
                onChanged.invoke(value)
            }
        }
        observe(viewLifecycleOwner, wrappedObserver)
        return wrappedObserver
    }

}
