package com.hd1998.photofetch

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.hd1998.photofetch.databinding.FragmentPhotoGallaryBinding
import kotlinx.coroutines.launch


private const val TAG="PhotoShowFragment"

class PhotoShowFragment : Fragment() {

    private var _binding : FragmentPhotoGallaryBinding? = null
    private val binding get() = checkNotNull(_binding){ }
  private var searchView : SearchView? = null
  private val photoViewModel : PhotoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }
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
      /*  clearToolbarMenu()
        binding.toolbar.inflateMenu(R.menu.fragment_photo_show)
         val searchItem=binding.toolbar.menu.findItem(R.id.menu_item_search)
        searchView=searchItem as SearchView
        binding.toolbar.setOnMenuItemClickListener {
            when(it.itemId){
                R.id.menu_item_search ->{ lifecycleScope.launch{photoViewModel.fetchPhoto()}
                    true}
                R.id.menu_item_clear -> true
                else -> false
            }
        }*/
        viewLifecycleOwner.lifecycleScope.launch{
            lifecycle.repeatOnLifecycle(Lifecycle.State.STARTED){
                photoViewModel.uiState.collect{
                    binding.photoGrid.adapter= updateUI(it)
                }
            }
        }
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.fragment_photo_show , menu)
        val searchItem=menu.findItem(R.id.menu_item_search)
        searchView=searchItem as? SearchView
        searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String ): Boolean {

                Log.d(TAG, "$query")
      return true }

            override fun onQueryTextChange(newText: String?): Boolean {
                Log.d(TAG, "$newText")
                return false
            }})}

            /*  override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
                  val inflater = MenuInflater(context)
                  inflater.inflate(R.menu.fragment_photo_show, menu)
               val searchItem=menu.findItem(R.id.menu_item_search)
                  searchView=searchItem as? SearchView
                  searchView?.setOnQueryTextListener(object:SearchView.OnQueryTextListener{
                      override fun onQueryTextSubmit(query: String ): Boolean {

                         lifecycleScope.launch{
                             photoViewModel.fetchPhoto(query)
                             }
                          return true
                      }
                      override fun onQueryTextChange(newText: String?): Boolean {
                          Log.d(TAG  , "$newText")
                          return false
                      }

                  })

              }*/


    /*override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
           R.id.menu_item_clear-> {lifecycleScope.launch{photoViewModel.fetchPhoto("")}
            return true}

        else -> super.onOptionsItemSelected(item)  }
    }
    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }*/
    private fun updateUI(photoUIState: PhotoUIState) : PhotoAdapter{
        val photoAdapter = PhotoAdapter(photoUIState.photoItems ){
             findNavController().navigate()}
        return photoAdapter }
}