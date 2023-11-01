package com.sonix.todoapp.presentaiton.list

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sonix.todoapp.R
import com.sonix.todoapp.databinding.FragmentListBinding
import com.sonix.todoapp.extensions.showErrorAlertDialog
import com.sonix.todoapp.model.ApiClient
import com.sonix.todoapp.model.Todo
import com.sonix.todoapp.model.request.TodosAcquisitionRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/** 一覧画面 */
class ListFragment : Fragment() {
    /** 削除モードか */
    private var isDeleteMode = false
    private lateinit var adapter: ListAdapter
    private val binding get() = _binding!!
    private var _binding: FragmentListBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.bind(inflater.inflate(R.layout.fragment_list, container, false))
        // 使用するレイアウトファイルを指定する
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 右上のメニューのボタンを表示する
        setHasOptionsMenu(true)
        adapter = ListAdapter(::onClickTodoItem)
        // RecyclerViewを設定する
        setUpRecyclerView()
        // Todoの一覧を取得する
        CoroutineScope(Dispatchers.IO).launch {
            fetchTodoList()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        // 使用するメニューのレイアウトファイルを指定する
        inflater.inflate(R.menu.menu_list, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            // ペンボタンを押した時の動作
            R.id.menu_edit -> goToTodoEditScreen(null)
            // ゴミ箱ボタンを押した時の動作
            R.id.menu_delete -> onSelectDeleteMenuItem(item)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    /** Todoリストを表示するRecyclerViewを設定する */
    private fun setUpRecyclerView() {
        binding.recyclerView.also {
            it.adapter = adapter
            it.layoutManager = LinearLayoutManager(context)
            it.addItemDecoration(DividerItemDecoration(context, DividerItemDecoration.VERTICAL))
        }
    }

    /** Todoのアイテムをクリックした時 */
    private fun onClickTodoItem(todo: Todo) {
        if (isDeleteMode) {
            // TODO: 削除モードの場合は削除確認ダイアログを表示する
        } else {
            // そうでなければ更新画面に遷移する
            goToTodoEditScreen(todo)
        }
    }

    /** 登録・更新画面に遷移する */
    private fun goToTodoEditScreen(todo: Todo?) {

    }

    /** Todoの一覧をサーバーから取得する */
    private suspend fun fetchTodoList() {
        ApiClient.call(TodosAcquisitionRequest(), { response ->
            // 通信に成功した時に実行される箇所
            // responseからtodoの一覧を取得し、画面に表示する
            response.todos?.let {
                adapter.todoList = it
            }
        }, ::showErrorAlertDialog) // <- 通信に失敗した時に実行される箇所
    }

    /** メニューのゴミ箱ボタンを選択した時 */
    private fun onSelectDeleteMenuItem(item: MenuItem) {
        // 削除モードを切り替える
        isDeleteMode = !isDeleteMode
        // アイコン画像をモードによって切り替える
        val iconResource = if (isDeleteMode) R.drawable.ic_check else R.drawable.ic_delete
        item.setIcon(iconResource)
    }
}
