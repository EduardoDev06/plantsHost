package com.bootcampnttdata6.plantshost.features.auth.launch

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.bootcampnttdata6.plantshost.R
import com.bootcampnttdata6.plantshost.databinding.FragmentLaunchBinding

class LaunchFragment : Fragment() {
    private var _binding: FragmentLaunchBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentLaunchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.btnGoToSignIn.setOnClickListener { findNavController().navigate(R.id.action_launch_to_sign_in) }
        binding.btnGoToSignUp.setOnClickListener { findNavController().navigate(R.id.action_launch_to_sign_up) }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}