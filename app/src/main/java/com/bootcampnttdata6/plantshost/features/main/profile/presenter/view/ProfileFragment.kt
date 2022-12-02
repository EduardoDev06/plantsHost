package com.bootcampnttdata6.plantshost.features.main.profile.presenter.view

import android.app.Activity
import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.provider.MediaStore
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import com.bootcampnttdata6.plantshost.util.Result
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentProfileBinding
import com.bootcampnttdata6.plantshost.features.main.profile.domain.model.User
import com.bootcampnttdata6.plantshost.features.main.profile.presenter.viewmodels.ProfileUserViewModel
import com.squareup.picasso.Picasso
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class ProfileFragment : Fragment(R.layout.fragment_profile) {

    private lateinit var binding: FragmentProfileBinding

    var userFullName: String? = ""
    var userEmail: String = ""
    var userPassword: String = ""
    var userAddress: String? = ""
    var userAge: Int? = 0
    var img:String? = null
    private var userImageBitmap: Bitmap? = null

    private val updateUserViewModel: ProfileUserViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding = FragmentProfileBinding.bind(view)

        llenarPerfilUsuario()
        activarElementos(false)
        editarPerfil()
        guardarCambios()

    }

    private fun activarElementos(
        activar: Boolean
    ) {
        binding.userFullName.isEnabled = activar
        binding.userEmail.isEnabled = false
        binding.userPassword.isEnabled = activar
        binding.userAddress.isEnabled = activar
        binding.userAge.isEnabled = activar
        binding.userImage.isEnabled = activar
        binding.btnGuardarCambios.isEnabled = activar
        binding.btnEditarPerfil.isEnabled = activar.not()
    }

    private fun llenarPerfilUsuario() {
        updateUserViewModel.readProfileUser()
        viewLifecycleOwner.lifecycleScope.launch {
            updateUserViewModel.readUser.collect { result ->
                when (result) {
                    is Result.Success -> {
                        binding.progressBar.visibility = View.GONE
                        val userdata = result.data
                        img = userdata?.userImage
                        if(img != ""){
                            Picasso.get()
                                .load(img)
                                .into(binding.userImage)
                        }
                        binding.userEmail.setText(userdata?.userEmail)
                        binding.userFullName.setText(userdata?.userFullName)
                        binding.userAddress.setText(userdata?.userAddress)
                        binding.userAge.setText(userdata?.userAge.toString())
                    }
                    is Result.Loading -> {
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Result.Failure -> {
                        binding.progressBar.visibility = View.GONE
                        Toast.makeText(
                            requireContext(),
                            "Error: ${result.exception}",
                            Toast.LENGTH_SHORT
                        ).show()
                    }
                    is Result.Finish -> {}
                }
            }
        }
    }

    private fun guardarCambios() {

        binding.btnGuardarCambios.setOnClickListener {
            userFullName = binding.userFullName.text?.toString()?.trim()
            userEmail = binding.userEmail.text.toString().trim()
            userAddress = binding.userAddress.text?.toString()?.trim()
            userAge = binding.userAge.text?.toString()?.trim()?.toInt()
            userPassword = binding.userPassword.text.toString().trim()

            if (userImageBitmap == null) {
                val user = User(userFullName, userAge, userEmail, userPassword, userAddress, img)
                updateUserViewModel.updateDataProfileUser(user)
            }else{
                val user = User(userFullName, userAge, userEmail, userPassword, userAddress)
                updateUserViewModel.updatePhotoProfileUser(userImageBitmap)
                updateUserViewModel.updateDataProfileUser(user)
            }

            activarElementos(false)

        }

    }

    private val startForResult =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { resultActivity ->
            if(resultActivity.resultCode == Activity.RESULT_OK){
                val photoPick = resultActivity.data
                userImageBitmap = photoPick?.extras?.get("data") as Bitmap
                binding.userImage.setImageBitmap(userImageBitmap)
            }else{
                Toast.makeText(requireContext(), "No ha seleccionado alguna foto", Toast.LENGTH_SHORT).show()
            }
        }

    private fun editarPerfil() {
        binding.btnEditarPerfil.setOnClickListener {
            activarElementos(true)
        }
        binding.userImage.setOnClickListener {
            startForResult.launch(Intent(MediaStore.ACTION_IMAGE_CAPTURE))
        }
    }
}