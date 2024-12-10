package com.example.rickandmorty.base

import BaseProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Rect
import android.os.Bundle
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

abstract class BaseFragment<B : ViewDataBinding, VM : BaseViewModel>(
    @LayoutRes private val layoutId: Int
) : Fragment(), BaseFragmentHelper {

    lateinit var binding: B
    abstract fun onInitDataBinding()
    private var isCreatedOnce = false
    private var viewModelStoreOwner: ViewModelStoreOwner? = null
    lateinit var viewModel: VM
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val vmClass = this.javaClass.findGenericWithType<VM>(ViewModel::class.java)
        if (vmClass != null) {
            viewModel = ViewModelProvider(this)[vmClass]
        }

        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        binding.lifecycleOwner = viewLifecycleOwner
        onInitDataBinding()
        observeViewModel()
        return binding.root
    }

    override fun onRecreatedView() {
        (binding.root.parent as? ViewGroup)?.removeView(binding.root)
    }

    override fun onBeforeDispatchTouchEvent(ev: MotionEvent?) {
        hideKeyboardOutsideOfEditText(ev)
    }

    private fun hideKeyboardOutsideOfEditText(ev: MotionEvent?) {
        if (ev?.action == MotionEvent.ACTION_DOWN) {
            if (isVisible) {
                val v: View? = activity?.currentFocus
                if (v is EditText) {
                    val outRect = Rect()
                    v.getGlobalVisibleRect(outRect)
                    if (!outRect.contains(ev.rawX.toInt(), ev.rawY.toInt())) {
                        v.clearFocus()
                        val imm: InputMethodManager =
                            activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(v.getWindowToken(), 0)
                    }
                }
            }
        }
    }



    fun showToastAndAction(message: String, destination: Int) {
        showToast(message)
        activityNavController().navigate(destination)
    }

    fun goAction(destination: Int) {
        activityNavController().navigate(destination)
    }

    open fun showToast(body: String?) {
        Toast.makeText(context, body, Toast.LENGTH_LONG).show()
    }



    fun setViewModelStoreOwner(owner: ViewModelStoreOwner) {
        viewModelStoreOwner = owner
    }

    fun NavDirections.navigate() {
        fragmentNavigate()
    }

    fun NavDirections.fragmentNavigate() {
        // (activity as BaseActivity<*, *>).findNavController((activity as BaseActivity<*, *>).layoutId).navigate(this)
        findNavController().navigate(this)
    }

    fun activityNavController() = findNavController()
    fun backPressedForActivity(current: Context, destionation: Class<*>) {
        view?.let {
            it.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action === KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if (id != null) {
                                val intent = Intent(current, destionation)
                                startActivity(intent)
                            }
                            return true
                        }
                    }
                    return false
                }
            })
        }
    }

    val backPressedLiveData: MutableLiveData<Boolean>? = null
    open fun backPressedWithoutId(): MutableLiveData<Boolean>? {
        view?.let {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            it.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action === KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            backPressedLiveData?.value = true
                            return true
                        }
                    }
                    backPressedLiveData?.value = false
                    return false
                }
            })
        }
        return backPressedLiveData
    }

    open fun backPressed(id: Int?) {
        view?.let {
            it.isFocusableInTouchMode = true
            it.requestFocus()
            it.setOnKeyListener(object : View.OnKeyListener {
                override fun onKey(v: View?, keyCode: Int, event: KeyEvent): Boolean {
                    if (event.action === KeyEvent.ACTION_DOWN) {
                        if (keyCode == KeyEvent.KEYCODE_BACK) {
                            if (id != null) {
                                findNavController().navigate(id)
                            }
                            return true
                        }
                    }
                    return false
                }
            })
        }
    }

    open fun showBottomTabs(): Boolean {
        return false
    }

    open fun observeViewModel() {

    }

    private var progressDialog: BaseProgressDialog? = null

    open fun showProgress() {
         progressDialog = BaseProgressDialog()
          progressDialog?.show(childFragmentManager, "PROGRESS")
    }

    open fun showProgressWithReference(): BaseProgressDialog? {
        progressDialog = BaseProgressDialog()
        progressDialog?.show(childFragmentManager, "PROGRESS")
        return progressDialog
    }

    open fun hideProgressWithReference(baseProgressDialog: BaseProgressDialog) {
        baseProgressDialog.dismiss()
    }

    open fun hideProgress() {
        progressDialog?.dismiss()
    }





}
