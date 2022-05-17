package com.example.movietmdbchallenge.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentLoginBinding
import com.example.movietmdbchallenge.ui.ViewModelFactory
import com.example.movietmdbchallenge.ui.home.UserViewModel

class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val viewModel : UserViewModel by activityViewModels()
    lateinit var loginViewModel: LoginViewModel

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
        val factory = ViewModelFactory(view.context)
        loginViewModel = ViewModelProvider(requireActivity(),factory)[LoginViewModel::class.java]


        binding.registerButton.setOnClickListener {
            findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToRegisterFragment())
        }
        binding.loginButton.setOnClickListener {
            loginViewModel.login(binding.inputEmailEditText.text.toString(),binding.inputPasswordEditText.text.toString())
        }
        loginViewModel.getCekValidLogin().observe(viewLifecycleOwner){
            if(it == true){
                findNavController().navigate(LoginFragmentDirections.actionLoginFragmentToHomeFragment())
                loginViewModel.reset()
            }
        }
    }
}