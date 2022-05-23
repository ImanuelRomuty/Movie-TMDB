package com.example.movietmdbchallenge.ui.splashScreen

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentTetstingSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel


class TetstingSplashFragment : Fragment() {
//    private val viewModel : UserViewModel by activityViewModels()
    private var _binding: FragmentTetstingSplashBinding? = null
    // This property is only valid between onCreateView and
// onDestroyView.
    private val binding get() = _binding!!

    private val viewModel : SplashViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTetstingSplashBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
//        val factory = ViewModelFactory(view.context)
//        viewModel = ViewModelProvider(requireActivity(), factory)[SplashViewModel::class.java]

        viewModel.loginCheck()
        navigate()
    }
    private fun navigate(){
        viewModel.loginCheck().observe(viewLifecycleOwner){
            if(it == "default"){
//                findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToLoginFragment())
                findNavController().navigate(TetstingSplashFragmentDirections.actionTetstingSplashFragmentToLoginFragment())
            }else{
                findNavController().navigate(TetstingSplashFragmentDirections.actionTetstingSplashFragmentToHomeFragment())
//                    findNavController().navigate(SplashFragmentDirections.actionSplashFragmentToHomeFragment())
            }
        }
    }
}