package com.sleeplessknights.donence.base

import androidx.activity.ComponentActivity
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.createViewModelLazy
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStoreOwner

class BaseFactory<VM : ViewModel>(private val creator: () -> VM) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return creator.invoke() as T
    }
}

inline fun <reified VM : ViewModel> Fragment.viewModel(noinline creator: () -> VM) =
    createViewModelLazy(VM::class, { viewModelStore }, { BaseFactory(creator) })

inline fun <reified VM : ViewModel> Fragment.viewModel(
    noinline owner: () -> ViewModelStoreOwner = { this },
    noinline creator: () -> VM
) =
    createViewModelLazy(VM::class, { owner().viewModelStore }, { BaseFactory(creator) })

inline fun <reified VM : ViewModel> Fragment.activityViewModel(noinline creator: () -> VM) =
    createViewModelLazy(VM::class, { requireActivity().viewModelStore }, { BaseFactory(creator) })

inline fun <reified VM : ViewModel> ComponentActivity.viewModel(noinline creator: () -> VM) =
    viewModels<VM> { BaseFactory(creator) }