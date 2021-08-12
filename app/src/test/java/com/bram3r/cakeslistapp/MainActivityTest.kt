package com.bram3r.cakeslistapp

import androidx.lifecycle.Observer
import com.bram3r.cakeslistapp.cakesList.CakesListViewModel
import com.bram3r.cakeslistapp.model.RetrofitService
import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.mockito.MockitoAnnotations
import java.net.HttpURLConnection

class MainActivityTest {

    private lateinit var viewModel: CakesListViewModel
    private lateinit var retrofitService: RetrofitService
    private lateinit var mockWebServer: MockWebServer


    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    @Test
    fun `test everything works`() {
        assert(true)
    }
}