package com.bootcampnttdata6.plantshost.features.auth.sign_up.presenter.view

import android.os.Bundle
import android.view.View
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentSignUpBinding
import com.bootcampnttdata6.plantshost.features.auth.sign_up.presenter.viewmodels.SignUpViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignUpFragment : Fragment(R.layout.fragment_sign_up) {
    private lateinit var binding: FragmentSignUpBinding
    private val signUpViewModel: SignUpViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentSignUpBinding.bind(view)

        binding.editTextEmail.addTextChangedListener {
            signUpViewModel.setEmail(it.toString())
        }
        binding.editTextPassword.addTextChangedListener {
            signUpViewModel.setPassword(it.toString())
        }
        binding.editTextName.addTextChangedListener {
            signUpViewModel.setName(it.toString())
        }
        binding.editTextAddress.addTextChangedListener {
            signUpViewModel.setAddress(it.toString())
        }
        binding.editTextAge.addTextChangedListener {
            signUpViewModel.setAge(it.toString())
        }

        binding.btnIngresar.setOnClickListener{
            if (validateData()) return@setOnClickListener
            signUpViewModel.createAuthUser(signUpViewModel.email.value,signUpViewModel.password.value)
            signUpViewModel.insertUser(signUpViewModel.email.value,signUpViewModel.name.value,signUpViewModel.address.value,signUpViewModel.age.value)
            findNavController().navigate(R.id.action_sign_up_to_sign_in)
        }
    }

    private fun validateData() : Boolean{
        val email = binding.editTextEmail.text.toString().trim()
        val name = binding.editTextName.text.toString()
        val password = binding.editTextPassword.text.toString().trim()
        val address = binding.editTextAddress.text.toString()
        val age = binding.editTextAge.text.toString().trim()

        if(email.isEmpty()){
            binding.editTextEmail.error="Completar e-mail"
            return true
        }
        if(name.isEmpty()){
            binding.editTextName.error="Completar nombre"
            return true
        }
        if(password.isEmpty()){
            binding.editTextPassword.error="Completar contraseña"
            return true
        }
        if(address.isEmpty()){
            binding.editTextAddress.error="Completar dirección"
            return true
        }
        if(age.isBlank()){
            binding.editTextAge.error="Completar edad"
            return true
        }
        return false
    }
    /*private fun addUser(email: String,password: String, name: String,address: String, age: String){
        signUpViewModel.signUpState.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is Resource.loading ->{
                    binding.progressBar.visibility=View.VISIBLE
                    binding.btnIngresar.isEnabled = false
                }
                is Resource.Success<UserDto> -> {
                    binding.progressBar.visibility=View.GONE
                    signUpViewModel.signUp(user = result.data, password = result.data.userPassword)
                    findNavController().navigate(R.id.action_sign_in_to_mainActivity)
                }
                is Resource.Error ->{
                    binding.progressBar.visibility= View.GONE
                    binding.btnIngresar.isEnabled = true
                    Toast.makeText(requireContext(),"Error $result",Toast.LENGTH_SHORT).show()
                }

                else -> Unit
            }
        })
    }*/



    private fun hideProgressDialog() {
        TODO("Not yet implemented")
    }
}