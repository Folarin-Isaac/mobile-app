package com.example.tsapp.onboarding

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.example.tsapp.LoginActivity
import com.example.tsapp.R

class OnboardingActivity : AppCompatActivity() {
    private lateinit var textView: TextView
    private lateinit var onboardingItemsAdapter: OnboardingItemsAdapter
    private lateinit var indicatorsContainer: LinearLayout
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)

        button = findViewById(R.id.continue_button)
        textView = findViewById(R.id.skip_text)
        indicatorsContainer = findViewById(R.id.onboarding_indicators)

        textView.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }

        setOnboardingItems()
        setUpIndicators()
        setCurrentIndicator(0)
    }

    private fun setOnboardingItems(){
        onboardingItemsAdapter = OnboardingItemsAdapter(
            listOf(
                OnboardingItem(
                    onboardingImage = R.drawable.ic_connection_illustration,
                    title = "Connect",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Commodo aliquam dictum eu in aliquet convallis commodo."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.ic_colearn_illustration,
                    title = "Co-Learn",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Commodo aliquam dictum eu in aliquet convallis commodo."
                ),
                OnboardingItem(
                    onboardingImage = R.drawable.ic_coshare_illustration,
                    title = "Co-Share",
                    description = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Commodo aliquam dictum eu in aliquet convallis commodo."
                )
            )
        )
        val onboardingViewPager = findViewById<ViewPager2>(R.id.view_pager)
        onboardingViewPager.adapter = onboardingItemsAdapter
        onboardingViewPager.registerOnPageChangeCallback(object:
            ViewPager2.OnPageChangeCallback(){
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                setCurrentIndicator(position)
            }
        })
        (onboardingViewPager.getChildAt(0) as RecyclerView).overScrollMode =
            RecyclerView.OVER_SCROLL_NEVER
        findViewById<Button>(R.id.continue_button).setOnClickListener {
            if ( onboardingViewPager.currentItem + 1 < onboardingItemsAdapter.itemCount){
                onboardingViewPager.currentItem += 1
            }
            else {
                onboardingViewPager.currentItem += 2
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)

            }
        }

    }

    private fun setUpIndicators() {
        indicatorsContainer = findViewById(R.id.onboarding_indicators)
        val indicators = arrayOfNulls<ImageView>(onboardingItemsAdapter.itemCount)
        val layoutParams: LinearLayout.LayoutParams =
            LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
        layoutParams.setMargins(8, 0, 8, 0)
        for ( i in indicators.indices){
            indicators[i] = ImageView(applicationContext)
            indicators[i]?.let {
                it.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
                it.layoutParams = layoutParams
                indicatorsContainer.addView(it)
            }
        }
    }
    // current indicator for onboarding screen
    private fun setCurrentIndicator(position: Int){
        val childCount = indicatorsContainer.childCount
        for (i in 0 until childCount){
            val imageView = indicatorsContainer.getChildAt(i) as ImageView
            if ( i == position){
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_active
                    )
                )
            } else {
                imageView.setImageDrawable(
                    ContextCompat.getDrawable(
                        applicationContext,
                        R.drawable.indicator_inactive
                    )
                )
            }
        }

    }
    }
