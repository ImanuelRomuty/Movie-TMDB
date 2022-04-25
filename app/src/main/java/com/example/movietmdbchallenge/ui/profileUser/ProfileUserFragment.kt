package com.example.movietmdbchallenge.ui.profileUser

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentProfileUserBinding
import com.example.movietmdbchallenge.ui.home.UserViewModel


class ProfileUserFragment : Fragment() {
    private var _binding: FragmentProfileUserBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : UserViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentProfileUserBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setData()

        binding.logoutButton.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(ProfileUserFragmentDirections.actionProfileUserFragmentToLoginFragment())
        }
        binding.updateButton.setOnClickListener {
            val user = User(
                username = binding.inputUsernameEditText.text.toString(),
                fullname = binding.inputFullnameEditText.text.toString(),
                ultah = binding.inputDateEditText.text.toString(),
                address = binding.inputAddressEditText.text.toString()
            )
            viewModel.updateData(user)
            setData()
        }




    }
    private fun setData(){
        //Set Data
        viewModel.usernamePatch.observe(viewLifecycleOwner){
            if(it!="null"){
                binding.inputUsernameEditText.setText(it)
            }
        }
        viewModel.fullnamePatch.observe(viewLifecycleOwner){
            if (it!="null"){
                binding.inputFullnameEditText.setText(it)
            }
        }
        viewModel.birthPatch.observe(viewLifecycleOwner){
            if (it!="null"){
                binding.inputDateEditText.setText(it)
            }
        }
        viewModel.addressPatch.observe(viewLifecycleOwner){
            if (it!="null"){
                binding.inputAddressEditText.setText(it)
            }
        }
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}