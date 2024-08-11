package com.example.headlinehub.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.headlinehub.R
import com.example.headlinehub.adapters.NewsAdapter
import com.example.headlinehub.models.Article


class HomeFrament : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
         val view: View =inflater.inflate(R.layout.fragment_home_frament, container, false)
         var itemList:ArrayList<Article> = arguments?.getSerializable("news") as ArrayList<Article>

        val news:ArrayList<Article> = arrayListOf()
        for(item in itemList){
            news.add(item)
        }
        //recycler view
        var recyclerView=view.findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager= LinearLayoutManager(context)
         var adapter=NewsAdapter(requireContext(),news);
        recyclerView.adapter=adapter
        adapter.setOnItemClickListener(object : NewsAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                //on clicking what you want to perform
                val newsDescriptionFragment=webViewFragment()
                val bundle=Bundle()
                bundle.putString("url",news[position].url)
                newsDescriptionFragment.arguments=bundle
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.frameLayout,newsDescriptionFragment)
                    .addToBackStack(null)
                    .commit()
            }
        })
        return view
    }

}