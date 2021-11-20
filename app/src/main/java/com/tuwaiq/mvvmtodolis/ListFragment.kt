package com.tuwaiq.mvvmtodolis

import android.database.DatabaseUtils
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tuwaiq.mvvmtodolis.database.Todo
import com.tuwaiq.mvvmtodolis.databinding.FragmentListBinding
import com.tuwaiq.mvvmtodolis.databinding.TodoListItemBinding

class ListFragment : Fragment() {


    private lateinit var todoVM: TodoVM
    private lateinit var binding:FragmentListBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        todoVM = TodoVM()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragmentval
         binding = DataBindingUtil.inflate<FragmentListBinding>(
            inflater,
            R.layout.fragment_list,
            container,
            false
        )

        binding.todoRv.apply {
            layoutManager = LinearLayoutManager(context)
        }



        return binding.root

    }


    fun updateUI(todos: List<Todo>){
        binding.todoRv.adapter = TodoAdapter(todos)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        todoVM.todoLiveData.observe(
            viewLifecycleOwner,
            Observer {
                it?.let {
                    updateUI(it)
                }
            }
        )
    }

    private inner class TodoHolder(val binding : TodoListItemBinding):
            RecyclerView.ViewHolder(binding.root){


                fun bind(todo: Todo){
                    binding.viewModel = todoVM
                    binding.viewModel?.todo = todo
                    binding.executePendingBindings()
                }

            }

    private inner class TodoAdapter(val todos:List<Todo>):
            RecyclerView.Adapter<TodoHolder>() {
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoHolder {

            val binding = DataBindingUtil.inflate<TodoListItemBinding>(
                layoutInflater,
                R.layout.todo_list_item,
                parent,
                false
            )
            return TodoHolder(binding)
        }

        override fun onBindViewHolder(holder: TodoHolder, position: Int) {

            val todo = todos[position]

            holder.bind(todo)



        }

        override fun getItemCount(): Int = todos.size
    }

}