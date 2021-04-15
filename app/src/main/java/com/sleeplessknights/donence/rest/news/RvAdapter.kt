package com.sleeplessknights.donence.rest.news

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sleeplessknights.donence.R
import com.sleeplessknights.donence.model.NewsResponse
import com.squareup.picasso.Picasso

class RvAdapter(val NewsList: List<NewsResponse>) : RecyclerView.Adapter<RvAdapter.ViewHolder>() {

    private var isCollapsed: Boolean = true

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): ViewHolder {
        val v = LayoutInflater.from(p0?.context).inflate(R.layout.news_adapter_layout, p0, false)
        return ViewHolder(v);
    }
    override fun getItemCount(): Int {
        return NewsList.size
    }

    override fun onBindViewHolder(p0: ViewHolder, p1: Int) {
        Picasso.get().load(NewsList[p1].imageUrl).into(p0.image);

        var cleanHeader = NewsList[p1].accessToken.replace("<p>", "").replace("</p", "")
        p0.header?.text = cleanHeader

        p0.date?.text = NewsList[p1].lat.substring(0, 10)

        p0.content.setOnClickListener(View.OnClickListener {
            if (isCollapsed) {
                p0.content.setMaxLines(Int.MAX_VALUE)
            } else {
                p0.content.setMaxLines(2)
            }
            isCollapsed = !isCollapsed
        })
        var cleanContext = NewsList[p1].email.replace("<p>", "").replace("</p", "")
        p0.content?.text = cleanContext
    }
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val header = itemView.findViewById<TextView>(R.id.heading)
        val content = itemView.findViewById<TextView>(R.id.content)
        val image = itemView.findViewById<ImageView>(R.id.imageContainer)
        val date = itemView.findViewById<TextView>(R.id.date)
    }
}