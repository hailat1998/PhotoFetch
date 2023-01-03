package com.hd1998.photofetch
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.hd1998.photofetch.databinding.FragmentPhotoGallaryBinding
import kotlinx.coroutines.launch
private const val TAG="PhotoShowFragment"

class PhotoShowFragment : Fragment() {

    private var _binding : FragmentPhotoGallaryBinding? = null
    private val binding get() = _binding!!

  private val photoViewModel : PhotoViewModel by viewModels()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoGallaryBinding.inflate(inflater  , container  , false)
        binding.photoGrid.layoutManager = GridLayoutManager(context, 3)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewLifecycleOwner.lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                photoViewModel.uiState.collect{
                    binding.photoGrid.adapter=PhotoAdapter(it.photoItems)
                    Log.d(TAG , "$it")
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}