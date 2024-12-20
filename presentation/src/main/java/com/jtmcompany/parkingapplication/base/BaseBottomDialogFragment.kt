package com.jtmcompany.parkingapplication.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment

abstract class BaseBottomDialogFragment<B : ViewDataBinding, VM : BaseViewModel>
    (@LayoutRes val layoutId: Int) : BottomSheetDialogFragment() {

    abstract val viewModel: VM
    abstract fun initView()
    abstract fun setBindingVariable(binding: B)
    abstract fun initObserver()

    protected lateinit var binding: B
    protected lateinit var mContext: Context

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext = context
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initObserver()
        initBaseObserver()
        initView()

    }

    private fun initBinding() {
        binding.lifecycleOwner = viewLifecycleOwner
        binding.executePendingBindings()
        setBindingVariable(binding)
    }

    private fun initBaseObserver() {
        with(viewModel) {
            showToastEvent.observe(viewLifecycleOwner) { _msg ->
                showToast(_msg)
            }
        }
    }

    private fun showToast(msg: String) {
        Toast.makeText(mContext, msg, Toast.LENGTH_SHORT).show()
    }

}