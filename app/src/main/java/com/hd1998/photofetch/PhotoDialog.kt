package com.hd1998.photofetch

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import coil.load
import com.hd1998.photofetch.databinding.PhotoDialogBinding

class PhotoDialog:DialogFragment() {
    private val args: PhotoDialogArgs by navArgs()
   private var _binding : PhotoDialogBinding?=null
    private val binding : PhotoDialogBinding  get()=
        checkNotNull(_binding){"not null"}
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        _binding=PhotoDialogBinding.inflate(inflater , container , false)
        return(binding.root)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.photoDialog.load(args.toBundle().toString()){R.drawable.ic_launcher_background}
        binding.photoDialog.setOnClickListener {findNavController().navigate(PhotoDialogDirections.returnPhoto()) }
    }

}