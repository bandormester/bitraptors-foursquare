package hu.bitraptors.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import hu.bitraptors.R

class ImageAdapter(private val context: Context) : PagerAdapter() {

    private lateinit var images: Array<Bitmap>

    fun addImages(newImages: Array<Bitmap>){
        images = newImages
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view === `object`
    }

    override fun getCount(): Int {
        return images.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(context)
        imageView.scaleType = ImageView.ScaleType.FIT_XY
        imageView.setImageBitmap(images[position])
        (container as ViewPager).addView(imageView,0)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        super.destroyItem(container, position, `object`)
        (container as ViewPager).removeView(`object` as ImageView)
    }

}