package lt.paulius.androidtopics.second_fragment

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import lt.paulius.androidtopics.R
import lt.paulius.androidtopics.common.FragmentLifecyclesPresentation
import lt.paulius.androidtopics.databinding.FragmentFirstBinding
import lt.paulius.androidtopics.databinding.FragmentSecondBinding

class SecondFragment : FragmentLifecyclesPresentation() {

    private val viewModel: SecondFragmentViewModel by viewModels()

    private var _binding: FragmentSecondBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "second_fragment"
        fun newInstance() = SecondFragment()
    }

}