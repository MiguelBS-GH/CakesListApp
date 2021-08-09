package com.bram3r.cakeslistapp.cakesList

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bram3r.cakeslistapp.databinding.CakesListFragmentBinding

class CakesListFragment : Fragment() {

    private var _binding: CakesListFragmentBinding? = null
    private val binding get() = _binding!!

    companion object {
        fun newInstance() = CakesListFragment()
    }

    private lateinit var viewModel: CakesListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = CakesListFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CakesListViewModel::class.java)
    }

}