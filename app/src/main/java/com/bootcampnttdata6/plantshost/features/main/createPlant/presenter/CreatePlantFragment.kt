package com.bootcampnttdata6.plantshost.features.main.createPlant.presenter
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
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.core.data.local.entity.PlantsEntity
import com.bootcampnttdata6.plantshost.databinding.FragmentCreatePlantBinding
import com.google.android.material.textfield.TextInputEditText
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.launch

@AndroidEntryPoint
class CreatePlantFragment : Fragment() {
    private var _binding: FragmentCreatePlantBinding? = null
    private val binding get() = _binding!!
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
        openImageSelector()
        binding.ivUploadImagePlant.setOnClickListener {uploadImage()}
        binding.btnAdd.setOnClickListener{saveData()}
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
    
    private fun uploadImage(){
        pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
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