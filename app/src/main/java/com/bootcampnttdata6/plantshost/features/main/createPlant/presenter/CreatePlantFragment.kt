package com.bootcampnttdata6.plantshost.features.main.createPlant.presenter
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.databinding.FragmentCreatePlantBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreatePlantFragment : Fragment() {
    private var _binding: FragmentCreatePlantBinding? = null
    private val binding get() = _binding!!
    private var pickImage= false
    private lateinit var requestPermissionLauncher: ActivityResultLauncher<String>
    private lateinit var pickMedia:ActivityResultLauncher<PickVisualMediaRequest>
    private val cpViewModel:CPViewModel by viewModels()
    private lateinit var imageUri: Uri

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentCreatePlantBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        eventClick()
    }

    private fun eventClick() {
        askPermission()
        openImageSelector()
        binding.ivUploadImagePlant.setOnClickListener {uploadImage()}
        binding.btnAdd.setOnClickListener{saveData()}
        binding.btnCancel.setOnClickListener{ findNavController().popBackStack()}
    }

    private fun openImageSelector(){
        pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) {
            if (it != null) {
                imageUri = it
                binding.ivUploadImagePlant.setImageURI(it)
            } else {
                Toast.makeText(context, R.string.cpf_selectImg, Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun askPermission(){
        requestPermissionLauncher = registerForActivityResult(ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            pickImage = isGranted

        }

        when {
            ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED -> {
                //Permission granted
                pickImage = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                requireActivity(), Manifest.permission.READ_EXTERNAL_STORAGE) -> {
                binding.root.showSnackbar(
                    binding.root,
                    getString(R.string.cpf_permission_required),
                    Snackbar.LENGTH_INDEFINITE,
                    getString(R.string.cpf_permission_ok)
                ) {
                    requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
                }
            }

            else -> {
                requestPermissionLauncher.launch(Manifest.permission.READ_EXTERNAL_STORAGE)
            }
        }
    }

    private fun View.showSnackbar(
        view: View,
        msg: String,
        length: Int,
        actionMessage: CharSequence?,
        action: (View) -> Unit
    ) {
        val snackbar = Snackbar.make(view, msg, length)
        if (actionMessage != null) {
            snackbar.setAction(actionMessage) {
                action(this)
            }.show()
        } else {
            snackbar.show()
        }
    }

    private fun uploadImage(){
        if(pickImage){
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }else{
            Toast.makeText(context, R.string.cpf_permission_required, Toast.LENGTH_SHORT).show()
        }
    }


    private fun saveData(){
        with(binding) {
            if (etName.ts() or etDescription.ts() or etPrice.ts() or etStatus.ts()){
                Toast.makeText(context, R.string.cpf_dataError, Toast.LENGTH_SHORT).show()
            } else {
                handleLoading(true)
                cpViewModel.addImageToStorage(etName.txt(), imageUri)
                viewLifecycleOwner.lifecycleScope.launch {
                    cpViewModel.addImgFire.filter { it != null }.collectLatest {
                        val planta = PlantsEntity(0, etDescription.txt(),etName.txt(),it.toString(), "S/. ${etPrice.txt()}",etStatus.txt(),false)
                        cpViewModel.insertNewPlant(planta)
                        handleLoading(false)
                        findNavController().popBackStack()
                    }
                }
            }
        }
    }

    private fun TextInputEditText.ts() = this.text.toString()==""
    private fun TextInputEditText.txt() = this.text.toString()

    private fun handleLoading(isLoading: Boolean) {
        with(binding) {
            if (isLoading) progress.visibility = View.VISIBLE else { progress.visibility = View.GONE }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}