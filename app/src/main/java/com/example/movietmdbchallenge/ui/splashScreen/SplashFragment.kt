package com.example.movietmdbchallenge.ui.splashScreen

import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentSplashBinding

import com.example.movietmdbchallenge.ui.login.LoginViewModel


class SplashFragment : Fragment() {
    private var _binding: FragmentSplashBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
//    private val viewModel : UserViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        Handler().postDelayed({
//            findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
//        },2000)
//        viewModel.loginCek()
//            viewModel.getValidationAll().observe(viewLifecycleOwner){
//                if(it==0){
//                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
//                }else if (it==1){
//                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
//                }
//            }
    }
}