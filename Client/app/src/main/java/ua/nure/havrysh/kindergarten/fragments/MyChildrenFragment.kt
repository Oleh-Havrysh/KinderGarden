package ua.nure.havrysh.kindergarten.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ListAdapter
import kindergarten.hakito.kindergartenclient.R
import kotlinx.android.synthetic.main.fragment_children.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ua.nure.havrysh.kindergarten.App
import ua.nure.havrysh.kindergarten.activities.ChildActivity
import ua.nure.havrysh.kindergarten.adapters.ChildrenAdapter
import ua.nure.havrysh.kindergarten.model.Child
import ua.nure.havrysh.kindergarten.rest.Rest

class MyChildrenFragment : android.support.v4.app.Fragment() {

    lateinit var adapter: ListAdapter

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_children, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        list_view.onItemClickListener =
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    val intent = Intent(activity, ChildActivity::class.java)
                    intent.putExtra(
                            ChildActivity.ARG_CHILD_ID,
                            (adapter.getItem(position) as Child).id
                    )
                    startActivity(intent)
                }

        loadMyChildren()
    }

    private fun loadMyChildren() {
        App.scope.launch(Dispatchers.Main) {
            val myChildren = withContext(Dispatchers.IO) {
                Rest.get().getMyChildren().await()
            }

            adapter = ChildrenAdapter(context, myChildren)
            list_view.adapter = adapter
            if (myChildren.isEmpty()) {
                empty_view.visibility = View.VISIBLE
            }
        }
    }
}
