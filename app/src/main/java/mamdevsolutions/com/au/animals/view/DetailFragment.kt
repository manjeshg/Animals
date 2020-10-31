package mamdevsolutions.com.au.animals.view

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.palette.graphics.Palette
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.item_animal.view.*
import mamdevsolutions.com.au.animals.R
import mamdevsolutions.com.au.animals.databinding.FragmentDetailBinding
import mamdevsolutions.com.au.animals.model.Animal
import mamdevsolutions.com.au.animals.model.AnimalPalette
import mamdevsolutions.com.au.animals.util.getProgressDrawable
import mamdevsolutions.com.au.animals.util.loadImage


/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : Fragment() {

    var animal: Animal? = null
    private lateinit var dataBinding :FragmentDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dataBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_detail, container, false)
        // Inflate the layout for this fragment
        return dataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            animal = DetailFragmentArgs.fromBundle(it).animal
        }
        animal?.imageUrl?.let {
            setupBackgroundColor(animal?.imageUrl)
        }
        dataBinding.animal = animal
    }

    fun setupBackgroundColor(imageUrl: String?) {

        Glide.with(this)
                .asBitmap()
                .load(imageUrl)
                .into(object: CustomTarget<Bitmap>(){
                    override fun onLoadCleared(placeholder: Drawable?) {

                    }
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        Palette.from(resource)
                                .generate() {palette ->
                                    var intColor = palette?.lightMutedSwatch?.rgb?:0
                                    dataBinding.palette = AnimalPalette(intColor)
                                }
                    }
                })
    }
}