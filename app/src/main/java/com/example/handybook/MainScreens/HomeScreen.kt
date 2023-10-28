package com.example.handybook.MainScreens

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.handybook.API.APIClient
import com.example.handybook.API.APIService
import com.example.handybook.API.MySharedPreferences
import com.example.handybook.Adapters.BookAdapter
import com.example.handybook.Adapters.FilterAdapter
import com.example.handybook.Data.BookData
import com.example.handybook.Data.Books
import com.example.handybook.Data.FilterData
import com.example.handybook.Data.UserData
import com.example.handybook.databinding.FragmentHomeScreenBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class HomeScreen : Fragment() {
    lateinit var users: MutableList<UserData>

    lateinit var selectedProducts: MutableList<BookData>
    lateinit var mySharedPreferences: MySharedPreferences
    private val api = APIClient.getInstance().create(APIService::class.java)
    lateinit var binding: FragmentHomeScreenBinding
    lateinit var adapter: FilterAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentHomeScreenBinding.inflate(inflater, container, false)
        var curentcategory = "All"
        mySharedPreferences = MySharedPreferences.newInstance(requireContext())
        users = mySharedPreferences.getLoginData()
        selectedProducts = mySharedPreferences.GetSelectedBooksList()
        //Category Recycler
        val listt = mutableListOf<FilterData>()
        listt.add(FilterData("All", true))
        api.getAllCategories().enqueue(object :Callback<List<String>>{
            override fun onResponse(call: Call<List<String>>, response: Response<List<String>>) {
                if (response.isSuccessful && response.body().isNullOrEmpty()){
                    for (i in 0 until response.body()!!.size){
                        listt.add(FilterData(response.body()!![i]))
                    }
                    binding.filter.adapter = FilterAdapter(listt, object : FilterAdapter.OnPressed{
                        override fun onPressed(filterData: FilterData) {
                            curentcategory = filterData.name
                            if (filterData.name=="All"){
                                api.getAllBooks().enqueue(object : Callback<Books>{
                                    override fun onResponse(
                                        call: Call<Books>,
                                        response: Response<Books>
                                    ) {
                                        if (response.isSuccessful && !response.body()?.books.isNullOrEmpty()){
                                            binding.booksRv.adapter = BookAdapter(response.body()!!.books.toMutableList(),object : BookAdapter.OnSelected{
                                                override fun onSelected(bookData: BookData) {
                                                    selectedProducts.add(bookData)
                                                }

                                            }, object : BookAdapter.OnClick{
                                                override fun onClick(bookData: BookData) {
                                                    TODO("Not yet implemented")
                                                }

                                            }

                                            )
                                        }
                                    }

                                    override fun onFailure(call: Call<Books>, t: Throwable) {
                                        Log.d("TAG3", "onFailure: $t")
                                    }

                                })
                            }
                        }

                    })

                }
            }

            override fun onFailure(call: Call<List<String>>, t: Throwable) {
                Log.d("TAG3", "onFailure: $t")
            }

        })



         return binding.root

        }
}
