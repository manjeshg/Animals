package mamdevsolutions.com.au.animals.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import kotlinx.android.synthetic.main.fragment_list.*
import mamdevsolutions.com.au.animals.R
import mamdevsolutions.com.au.animals.model.Animal
import mamdevsolutions.com.au.animals.viewmodel.ListViewModel


/**
 * A simple [Fragment] subclass.
 * Use the [ListFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class ListFragment : Fragment() {

    private lateinit var viewModel :ListViewModel
    private val listAdapter =  AnimalListAdapter(arrayListOf())
    private val animalListDataObserver = Observer<List<Animal>> {list ->
        list?.let {
            animalList.visibility = View.VISIBLE
            listAdapter.updateAnimalList(it)
        }
    }

    private val loadingLiveDataObserver = Observer<Boolean> {isLoading ->
        loadingView.visibility = if (isLoading) View.VISIBLE else View.GONE
        if (isLoading) {
            listError.visibility = View.GONE
            animalList.visibility = View.GONE
        }
    }

    private val loadingErrorLiveDataObserver = Observer<Boolean> {isLoadingError ->
        listError.visibility = if(isLoadingError) View.VISIBLE else View.GONE
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ListViewModel::class.java)
        viewModel.animals.observe(this, animalListDataObserver)
        viewModel.loading.observe(this, loadingLiveDataObserver)
        viewModel.loadError.observe(this, loadingErrorLiveDataObserver)
        viewModel.refresh()

        animalList.apply {
            layoutManager = GridLayoutManager(context, 2)
            adapter = listAdapter
        }
    }
}
