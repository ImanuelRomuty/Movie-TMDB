package com.example.movietmdbchallenge.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentLoginBinding
import com.example.movietmdbchallenge.ui.home.UserViewModel



class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.registerButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }

        binding.loginButton.setOnClickListener {
            viewModel.login(binding.inputEmailEditText.text.toString(),binding.inputPasswordEditText.text.toString())
        }
//        viewModel.loginCek()
        viewModel.getValidationAll().observe(viewLifecycleOwner){
            if(it != 0){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
            }
        }



//        viewModel.getValidationAll().observe(viewLifecycleOwner){
//            if(it==0){
////                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
//            }else if (it==1){
////                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
//            }
//        }
    }

}