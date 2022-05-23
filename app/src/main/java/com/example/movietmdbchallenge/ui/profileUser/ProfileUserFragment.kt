package com.example.movietmdbchallenge.ui.profileUser

import android.Manifest
import android.app.Activity
import android.app.AlertDialog
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.Settings
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.app.ActivityCompat
import androidx.core.net.toUri
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.listmoview.room.User
import com.example.movietmdbchallenge.R
import com.example.movietmdbchallenge.databinding.FragmentProfileUserBinding

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileUserFragment : Fragment() {
    private val REQUEST_CODE_PERMISSION = 100
    var imagePath :String?=null
    private val galleryResult =
        registerForActivityResult(ActivityResultContracts.GetContent()) { result ->
            imagePath = result.toString()
            Glide.with(requireActivity())
                .load(result)
                .apply(RequestOptions.centerCropTransform())
                .error(R.drawable.ic_launcher_background)
                .into(binding.imageProfile)
        }
    private var _binding: FragmentProfileUserBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel : ProfileViewModel by viewModel()
    private var useernameValue = "default"
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
//        val factory = ViewModelFactory(view.context)
//        viewModel = ViewModelProvider(requireActivity(),factory)[ProfileViewModel::class.java]
        username()
        setData()
        binding.imageProfile.setOnClickListener {
            checkingPermissions()
        }
        binding.logoutButton.setOnClickListener {
            viewModel.logOut()
            findNavController().navigate(ProfileUserFragmentDirections.actionProfileUserFragmentToLoginFragment())
        }
        binding.updateButton.setOnClickListener {
            val user = User(
                username = binding.inputUsernameEditText.text.toString(),
                fullname = binding.inputFullnameEditText.text.toString(),
                ultah = binding.inputDateEditText.text.toString(),
                address = binding.inputAddressEditText.text.toString(),
                imagePath = imagePath.toString()
            )
            Log.d("GALERI",imagePath.toString())
            viewModel.getEmail().observe(viewLifecycleOwner){
                viewModel.updateData(user,it)
            }

            viewModel.getUpdateValidation().observe(viewLifecycleOwner){
                if(it==true){
                    viewModel.setUsername(user.username.toString())
                    viewModel.getUserData(user.username.toString())
                }
            }
            setData()
        }
        binding.logoutButton.setOnClickListener {
            logOut()
        }
    }
    private fun checkingPermissions() {
        if (isGranted(
                requireActivity(),
                Manifest.permission.CAMERA,
                arrayOf(
                    Manifest.permission.CAMERA,
                    Manifest.permission.READ_EXTERNAL_STORAGE,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE
                ),
                REQUEST_CODE_PERMISSION,
            )
        ) {
            chooseImageDialog()
        }
    }
    private fun isGranted(
        activity: Activity,
        permission: String,
        permissions: Array<String>,
        request: Int,
    ): Boolean {
        val permissionCheck = ActivityCompat.checkSelfPermission(activity, permission)
        return if (permissionCheck != PackageManager.PERMISSION_GRANTED) {
            if (ActivityCompat.shouldShowRequestPermissionRationale(activity, permission)) {
                showPermissionDeniedDialog()
            } else {
                ActivityCompat.requestPermissions(activity, permissions, request)
            }
            false
        } else {
            true
        }
    }
    private fun chooseImageDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setMessage("Pilih Gambar")
            .setPositiveButton("Gallery") { _, _ -> openGallery() }
            .show()
    }
    private fun openGallery() {
        activity?.intent?.type = "image/*"
        galleryResult.launch("image/*")
    }

    private fun showPermissionDeniedDialog() {
        androidx.appcompat.app.AlertDialog.Builder(requireContext())
            .setTitle("Permission Denied")
            .setMessage("Permission is denied, Please allow permissions from App Settings.")
            .setPositiveButton(
                "App Settings"
            ) { _, _ ->
                val intent = Intent()
                intent.action = Settings.ACTION_APPLICATION_DETAILS_SETTINGS
                val uri = Uri.fromParts("package", activity?.packageName, null)
                intent.data = uri
                startActivity(intent)
            }
            .setNegativeButton("Cancel") { dialog, _ -> dialog.cancel() }
            .show()
    }
    private fun username(){
        viewModel.getUsername().observe(viewLifecycleOwner){result ->
            useernameValue = result
            viewModel.getUserData(result)
        }
    }
    private fun setData(){
        //Set Data
        viewModel.resultUser().observe(viewLifecycleOwner){
            if(it.username!==null){
                binding.inputUsernameEditText.setText(it.username.toString())
            }
            if(it.fullname!="null"){
                binding.inputFullnameEditText.setText(it.fullname.toString())
            }
            if(it.ultah !="null"){
                binding.inputDateEditText.setText(it.ultah.toString())
            }
            if(it.address!="null"){
                binding.inputAddressEditText.setText(it.address.toString())
            }
            if(it.imagePath!=null){
                Log.d("GALER",it.imagePath.toString())
                imagePath = it.imagePath
                Glide.with(requireActivity())
                    .load(it.imagePath)
                    .apply(RequestOptions.centerCropTransform())
                    .error(R.drawable.ic_launcher_background)
                    .into(binding.imageProfile)
            }
        }
    }
    private fun logOut(){
        AlertDialog.Builder(context).setPositiveButton("Yes"){_,_ ->
            viewModel.logOut()
            findNavController().navigate(ProfileUserFragmentDirections.actionProfileUserFragmentToLoginFragment())
        }
            .setNegativeButton(
                "No"
            ){
                p0,_ ->
                p0.dismiss()
            }
            .setMessage("Apakah anda ingin Keluar?").setTitle("Confirm Logout")
            .create().show()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}