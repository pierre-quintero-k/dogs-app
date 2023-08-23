package certificacion.td.perritos.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import certificacion.td.perritos.R
import certificacion.td.perritos.databinding.FragmentSecondBinding
import certificacion.td.perritos.viewModel.DogViewModel

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class SecondFragment : Fragment() {

    private var _binding: FragmentSecondBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val viewModel: DogViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSecondBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.action_SecondFragment_to_FirstFragment)
        }

        val adapter= ImageAdapter()
        val rv= binding.rv2
        rv.adapter= adapter
        rv.layoutManager=LinearLayoutManager(context)


        viewModel.getImages().observe(viewLifecycleOwner){
            it?.let {
                adapter.update(it)
            }
        }

        adapter.selectedImage.observe(viewLifecycleOwner){
            it?.let {
                if (it.favourite){
                    it.favourite=false
                    viewModel.updateFavourite(it)
                }else{
                    it.favourite=true
                    viewModel.updateFavourite(it)
                }
            }
        }

        binding.btborrar.setOnClickListener {
            viewModel.deleteAllFavourites()
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}