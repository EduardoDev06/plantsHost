package com.bootcampnttdata6.plantshost.features.auth.sign_in.presenter.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentSignInBinding
import com.bootcampnttdata6.plantshost.features.auth.sign_in.presenter.viewmodels.LoginViewModel
import com.bootcampnttdata6.plantshost.features.auth.util.Resource
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : Fragment() {
    private var _binding: FragmentSignInBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSignInBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initObservers()
        initListeners()
    }

    private fun initObservers() {
        viewmodel.loginState.observe(viewLifecycleOwner) { state ->
            when (state) {
                is Resource.Success -> {
                    handleLoading(isLoading = false)
                    findNavController().navigate(R.id.action_sign_in_to_mainActivity)
                    requireActivity().finish()
                }
                is Resource.Error -> {
                    handleLoading(isLoading = false)
                    Toast.makeText(
                        requireContext(),
                        "Login Error",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                is Resource.loading -> handleLoading(isLoading = true)
                else -> Unit
            }
        }
    }

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) {
                btnSignIn.text = ""
                btnSignIn.isEnabled = false
                binding.pbLogin.visibility = View.VISIBLE
            } else {
                binding.pbLogin.visibility = View.GONE
                binding.btnSignIn.text = getString(R.string.login_login_button)
                binding.btnSignIn.isEnabled = true
            }
        }
    }

    private fun initListeners() {
        with(binding) {
            binding.btnSignIn.setOnClickListener { handleLogin() }
            btnSignup.setOnClickListener {
                findNavController().navigate(R.id.action_sign_in_to_sign_up)
            }
        }
    }

    private fun handleLogin() {
        val email: String = binding.etMail.text.toString()
        val password: String = binding.etPassword.text.toString()
        viewmodel.login(email, password)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}