package com.romainpiel.readerview.ui.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import butterknife.bindView
import com.romainpiel.readerview.R
import com.romainpiel.readerview.dagger.ActivityModule
import com.romainpiel.readerview.repository.HtmlRepository
import com.romainpiel.readerview.ui.ViewModel
import com.romainpiel.readerview.ui.applicationComponent
import com.romainpiel.readerview.ui.showShortToast
import com.romainpiel.readerview.utils.L
import com.romainpiel.readerview.utils.applySchedulers
import com.romainpiel.readerview.utils.findFirstUrl
import rx.Observer
import rx.Subscription
import uk.co.chrisjenx.calligraphy.CalligraphyContextWrapper
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var htmlRepository: HtmlRepository

    val recyclerView: RecyclerView by bindView(R.id.recyclerView)
    lateinit var adapter: HtmlAdapter
    lateinit var url: String
    lateinit var subscription: Subscription

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        url = ""
        if (intent.action == Intent.ACTION_SEND) {
            val text = intent.getStringExtra(Intent.EXTRA_TEXT)
            L.d(text)
            val firstUrl = text.findFirstUrl()
            if (firstUrl != null) url = firstUrl
        }
        L.d(url)

        DaggerMainActivityComponent.builder()
                .applicationComponent(applicationComponent)
                .activityModule(ActivityModule(this))
                .build()
                .inject(this)

        adapter = HtmlAdapter()
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        subscription = htmlRepository.get(url)
                .applySchedulers()
                .compose(DocumentToViewModels())
                .subscribe(object : Observer<List<ViewModel>> {
                    override fun onCompleted() {
                    }

                    override fun onNext(viewModels: List<ViewModel>) {
                        L.d("number of items: ${viewModels.size}")
                        adapter.setItems(viewModels)
                        if (viewModels.isEmpty()) {
                            showShortToast("Sorry we couldn't find anything to show in this page")
                        }
                    }

                    override fun onError(error: Throwable?) {
                        L.d("boom", error)
                        adapter.setItems(emptyList())
                        showShortToast("Sorry we couldn't fetch the page")
                    }
                })
    }

    override fun onPause() {
        super.onPause()
        subscription.unsubscribe()
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(CalligraphyContextWrapper.wrap(newBase))
    }
}
