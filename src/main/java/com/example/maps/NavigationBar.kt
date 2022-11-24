package com.example.maps

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.maps.databinding.ActivityNavigationBarBinding

private const val TAG_TRANSLATION = "translation_fragment"
private const val TAG_SUBWAY = "subway_fragment"
private const val TAG_MAP = "map_fragment"
private const val TAG_STAMP = "stamp_fragment"

class NavigationBar : AppCompatActivity() {
    private lateinit var binding : ActivityNavigationBarBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityNavigationBarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.navigationView.setOnItemSelectedListener { item ->
            when(item.itemId) {
                R.id.translationMenu -> setFragment(TAG_TRANSLATION, TranslationFragment())
                R.id.subwayMenu -> setFragment(TAG_SUBWAY, SubwayFragment())
                R.id.mapMenu -> setFragment(TAG_MAP, MapFragment())
                R.id.stampMenu-> setFragment(TAG_STAMP, StampFragment())
            }
            true
        }
    }

    private fun setFragment(tag: String, fragment: Fragment) {
        val manager: FragmentManager = supportFragmentManager
        val fragTransaction = manager.beginTransaction()

        if (manager.findFragmentByTag(tag) == null){
            fragTransaction.add(R.id.mainFrameLayout, fragment, tag)
        }

        val translation = manager.findFragmentByTag(TAG_TRANSLATION)
        val subway = manager.findFragmentByTag(TAG_SUBWAY)
        val map = manager.findFragmentByTag(TAG_MAP)
        val stamp = manager.findFragmentByTag(TAG_STAMP)


        if (translation != null){
            fragTransaction.hide(translation)
        }

        if (subway != null){
            fragTransaction.hide(subway)
        }

        if (map != null) {
            fragTransaction.hide(map)
        }

        if (stamp != null) {
            fragTransaction.hide(stamp)
        }


        if (tag == TAG_TRANSLATION) {
            if (translation!=null){
                fragTransaction.show(translation)
            }
        }
        else if (tag == TAG_SUBWAY) {
            if (subway != null) {
                fragTransaction.show(subway)
            }
        }

        else if (tag == TAG_MAP){
            if (map != null){
                fragTransaction.show(map)
            }
        }

        else if (tag == TAG_STAMP){
            if (stamp != null){
                fragTransaction.show(stamp)
            }
        }
        fragTransaction.commitAllowingStateLoss()
    }
}



