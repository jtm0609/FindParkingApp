package com.jtmcompany.parkingapplication.base

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController


abstract class BaseFragment<B: ViewDataBinding, VM: BaseViewModel>(
    @LayoutRes val layoutId : Int
) : Fragment() {

    protected abstract val viewModel: VM
    protected lateinit var binding: B
    protected lateinit var mContext: Context

    private val navController by lazy {
        NavHostFragment.findNavController(this) }

    abstract fun initObserver()
    abstract fun initView()
    abstract fun setBindingVariable(binding: B)
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mContext =context
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layoutId, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initBinding()
        initObserver()
        initView()
    }

    private fun initBinding() {
        setBindingVariable(binding)
        binding.lifecycleOwner = this
        binding.executePendingBindings()
    }

    protected fun showToast(msg: String) =
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show()

    protected fun nextFragment(action : NavDirections) {
        navController.navigate(action)
    }

    protected fun finishFragment() {
        navController.popBackStack()
    }
}