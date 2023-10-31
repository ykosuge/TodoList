package com.sonix.todoapp.presentaiton.edit

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.sonix.todoapp.R
import com.sonix.todoapp.databinding.FragmentEditBinding

/** 登録・更新画面 */
class EditFragment: Fragment() {
    private val args: EditFragmentArgs by navArgs()
    private val binding get() = _binding!!
    private var _binding: FragmentEditBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        // 使用するレイアウトファイルを指定する
        _binding = FragmentEditBinding.bind(inflater.inflate(R.layout.fragment_edit, container, false))
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
