package com.ulvijabbarli.pronote.ui.base

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.fragment.findNavController
import androidx.viewbinding.ViewBinding
import com.ulvijabbarli.pronote.util.hideKeyboard
import kotlin.reflect.KClass

abstract class BaseFragment<State, Effect, ViewModel : BaseViewModel<State, Effect>, B : ViewBinding> :
    Fragment(), LifecycleOwner {

    protected abstract val vmClazz: KClass<ViewModel>
    protected abstract val bindingCallback: (LayoutInflater, ViewGroup?, Boolean) -> B
    protected abstract val screenName: String

    val viewModel: ViewModel by viewModels()

    protected lateinit var binding: B

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bindingCallback.invoke(inflater, container, false)
        return binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if (savedInstanceState == null) {
            viewModel.setArguments(arguments)
//            viewModel.loadArguments()
        }
    }

    protected open val bindViews: B.() -> Unit = {}

    protected open fun observeState(state: State) {
        // override when observing
    }

    protected open fun observeEffect(effect: Effect) {
        // override when observing
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        hideKeyboard()
        bindViews(binding)
    }

    @SuppressLint("RestrictedApi")
    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.state.observe(viewLifecycleOwner, ::observeState)
        viewModel.effect.observe(viewLifecycleOwner, ::observeEffect)
        viewModel.commonEffect.observe(viewLifecycleOwner) {
            when (it) {
                is UnknownError -> showBackEndError()
                is MessageError -> showError(getString(it.messageId))
                else -> error("Unknown BaseEffect: $it")
            }
        }

        viewModel.navigationCommands.observe(viewLifecycleOwner) { command ->
            when (command) {
                is NavigationCommand.To -> {
                    command.extras?.let { extras ->
                        findNavController().navigate(
                            command.directions,
                            extras
                        )
                    } ?: run {
                        findNavController().navigate(
                            command.directions
                        )
                    }
                }
                is NavigationCommand.BackTo -> findNavController().getBackStackEntry(command.destinationId)
                is NavigationCommand.Back -> findNavController().popBackStack()
                is NavigationCommand.ToRoot -> findNavController().popBackStack(
                    findNavController().backStack.first.destination.id,
                    false
                )
            }
        }
    }

}