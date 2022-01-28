package com.example.android.simplerecipes.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.android.simplerecipes.R
import com.example.android.simplerecipes.databinding.FragmentLoginBinding
import com.example.android.simplerecipes.util.hideKeyboard

class LoginFragment : Fragment() {

    private lateinit var binding: FragmentLoginBinding

    private val viewModel: LoginViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "ViewModel can only be accessed after OnViewCreated() ic called"
        }
        ViewModelProvider(this).get(LoginViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_login,
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        binding.viewModel = viewModel

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonListeners()
        setupEditorActionListeners()
        setupViewModelObservations()
    }

    private fun setupViewModelObservations() {
        viewModel.hasUserLoggedIn.observe(viewLifecycleOwner) { hasLoggedIn ->
            if (hasLoggedIn) {
                findNavController().navigate(R.id.action_loginFragment_to_recipeListFragment)
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.please_enter_your_login_credentials,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private fun setupButtonListeners() {
        binding.loginButton.setOnClickListener {
            viewModel.login()
            hideKeyboard()
        }
        binding.forgotPasswordButton.setOnClickListener {
            Toast.makeText(requireContext(), "Forgot Password Clicked", Toast.LENGTH_SHORT).show()
        }
        binding.createAccountButton.setOnClickListener {
            Toast.makeText(requireContext(), "Create Account Clicked", Toast.LENGTH_SHORT).show()
        }
    }

    private fun setupEditorActionListeners() {
        binding.passwordEditText.setOnEditorActionListener { _, actionId, _ ->
            when (actionId) {
                EditorInfo.IME_ACTION_DONE -> {
                    binding.loginButton.performClick()
                    true
                }
                else -> false
            }
        }
    }
}