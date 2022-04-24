package com.example.movietmdbchallenge.ui.register

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentRegisterBinding


class RegisterFragment : Fragment() {
    private var _binding: FragmentRegisterBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : RegisterViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.signUpButton.setOnClickListener {
            val user = User(
                email    = binding.inputEmailEditText.text.toString(),
                password = binding.inputPasswordEditText.text.toString(),
                username = binding.inputUsernameEditText.text.toString()
            )
            viewModel.register(user,binding.inputConfirmPasswordEditText.text.toString())
        }
        viewModel.getCekValidRegister().observe(viewLifecycleOwner){
            if(it != 0){
                findNavController().navigate(RegisterFragmentDirections.actionRegisterFragmentToLoginFragment())
            }
        }

    }

}